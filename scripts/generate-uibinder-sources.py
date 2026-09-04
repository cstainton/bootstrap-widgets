#!/usr/bin/env python3
"""Generate UiBinder implementations for the TeaVM backend.

GWT satisfies GWT.create(SomeBinder.class) with a compile-time generator reached
through deferred binding. TeaVM has no generator SPI, which is the only reason
UiBinder does not work there: the code GWT's generator emits is ordinary Java
that TeaVM compiles perfectly well.

So this runs the same step earlier. For each .ui.xml it writes a Java class
implementing the owner's UiBinder interface, and a META-INF/services entry so the
compatibility layer's GWT.create finds it. A template written for GWT compiles
unchanged on both backends.

Supported subset, which is what the widget templates in this repository use:

  * widget elements from urn:import: namespaces, nested to any depth
  * attributes applied through setters, with enum, boolean, int and String values
  * @UiConstructor arguments, matched by parameter name
  * ui:field, assigned back to the owner's @UiField
  * @UiHandler methods, wired by field name and handler parameter type
  * text content, applied through setText

Not supported, and rejected loudly rather than mistranslated: ui:style, ui:with,
ui:msg, HTMLPanel with inline HTML, and @UiChild tags.

Usage: generate-uibinder-sources.py <template-root> <output-root> <services-root>
"""
import io
import os
import re
import sys
import xml.etree.ElementTree as ET

UI_NS = 'urn:ui:com.google.gwt.uibinder'
UNSUPPORTED = ('style', 'with', 'msg')


class Unsupported(Exception):
    pass


def parse_namespaces(path):
    prefixes = {}
    with open(path, encoding='utf-8') as handle:
        text = handle.read()
    for prefix, uri in re.findall(r'xmlns:([A-Za-z0-9._-]+)\s*=\s*"([^"]+)"', text):
        prefixes[prefix] = uri
    return prefixes


def java_package(uri):
    if not uri.startswith('urn:import:'):
        return None
    return uri[len('urn:import:'):]


def local(tag):
    return tag.rsplit('}', 1)[-1]


def namespace(tag):
    return tag[1:].split('}', 1)[0] if tag.startswith('{') else ''


class TypeOracle:
    """What GWT's generator gets from its type oracle, read from the widget sources.

    Only three questions are asked of it: does this widget have a @UiConstructor and
    what are its parameters, what type does a setter take, and is that type an enum.
    """

    def __init__(self, roots):
        self.by_simple = {}
        for root in roots:
            for dirpath, _, files in os.walk(root):
                for name in files:
                    if name.endswith('.java') and not name.endswith('package-info.java'):
                        self.by_simple.setdefault(name[:-5], os.path.join(dirpath, name))
        self._cache = {}

    def describe(self, simple):
        if simple in self._cache:
            return self._cache[simple]
        path = self.by_simple.get(simple)
        info = {'ctor': None, 'setters': {}, 'enum': False, 'package': None}
        if path:
            text = io.open(path, encoding='utf-8').read()
            package = re.search(r'^package\s+([A-Za-z0-9_.]+)\s*;', text, re.M)
            info['package'] = package.group(1) if package else None
            info['enum'] = re.search(r'public\s+enum\s+' + re.escape(simple) + r'\b', text) is not None
            match = re.search(r'@UiConstructor\s*\n\s*public\s+' + re.escape(simple)
                              + r'\s*\(([^)]*)\)', text)
            if match:
                params = []
                for part in match.group(1).split(','):
                    part = part.strip()
                    if part:
                        bits = part.replace('final ', '').split()
                        params.append((bits[-2] if len(bits) > 1 else 'String', bits[-1]))
                info['ctor'] = params
            for m in re.finditer(r'public\s+void\s+(set[A-Z][A-Za-z0-9]*)\s*\(([^),]*)\)', text):
                param = m.group(2).replace('final ', '').strip()
                if param:
                    info['setters'][m.group(1)] = param.split()[0]
            # A widget usually inherits most of its setters, so walk the chain the way
            # the type oracle would rather than only reading the class's own file.
            parent = re.search(r'class\s+' + re.escape(simple) + r'\b[^{]*?\bextends\s+([A-Za-z0-9_.]+)', text)
            if parent:
                base = parent.group(1).split('.')[-1]
                if base != simple and base in self.by_simple:
                    self._cache[simple] = info          # guard against a cycle
                    inherited = self.describe(base)
                    for setter, type_name in inherited['setters'].items():
                        info['setters'].setdefault(setter, type_name)
        self._cache[simple] = info
        return info

    def is_enum(self, type_name):
        return self.describe(type_name.split('.')[-1])['enum']

    def qualify(self, type_name):
        """The fully qualified name, so generated code needs no imports."""
        simple = type_name.split('.')[-1]
        info = self.describe(simple)
        return '%s.%s' % (info['package'], simple) if info['package'] else simple


class Generator:
    def __init__(self, owner_pkg, owner_name, root, oracle):
        self.oracle = oracle
        self.owner_pkg = owner_pkg
        self.owner_name = owner_name
        self.root = root
        self.lines = []
        self.counter = 0
        self.fields = {}
        self.root_type = None

    def name_for(self, simple):
        self.counter += 1
        return '%s%d' % (simple[0].lower() + simple[1:], self.counter)

    def emit(self, element, parent=None):
        ns = namespace(element.tag)
        if ns == UI_NS:
            if local(element.tag) in UNSUPPORTED:
                raise Unsupported('ui:%s is not supported yet' % local(element.tag))
            for child in element:
                self.emit(child, parent)
            return None

        pkg = java_package(ns)
        if pkg is None:
            raise Unsupported('element %s is not from a urn:import namespace' % element.tag)

        simple = local(element.tag)
        var = self.name_for(simple)
        ctor_args, setters, field = self.split_attributes(element, simple)
        self.lines.append('        %s.%s %s = new %s.%s(%s);'
                          % (pkg, simple, var, pkg, simple, ', '.join(ctor_args)))
        if self.root_type is None:
            self.root_type = '%s.%s' % (pkg, simple)
        for call in setters:
            self.lines.append('        %s.%s;' % (var, call))
        text = (element.text or '').strip()
        if text:
            self.lines.append('        %s.setText("%s");' % (var, escape(text)))
        if field:
            self.fields[field] = var
        for child in element:
            self.emit(child, var)
        if parent is not None:
            self.lines.append('        %s.add(%s);' % (parent, var))
        return var

    def split_attributes(self, element, simple):
        info = self.oracle.describe(simple)
        attrs, field = {}, None
        for key, value in element.attrib.items():
            if namespace(key) == UI_NS or key.startswith('ui:'):
                if local(key) == 'field':
                    field = value
                continue
            name = local(key)
            if ':' in name:
                name = name.rsplit(':', 1)[-1]
            attrs[name] = value

        ctor_args = []
        if info['ctor']:
            for type_name, param in info['ctor']:
                value = attrs.pop(param, None)
                if value is None:
                    raise Unsupported('%s needs the %s attribute its @UiConstructor takes'
                                      % (simple, param))
                ctor_args.append(self.value(type_name, value))

        setters = []
        for name, value in attrs.items():
            # UiBinder's own pseudo-attributes, which are not setters
            if name == 'addStyleNames':
                for style in value.split():
                    setters.append('addStyleName("%s")' % escape(style))
                continue
            if name == 'id':
                setters.append('getElement().setId("%s")' % escape(value))
                continue
            setter = 'set' + name[0].upper() + name[1:]
            type_name = info['setters'].get(setter, 'String')
            setters.append('%s(%s)' % (setter, self.value(type_name, value)))
        return ctor_args, setters, field

    def value(self, type_name, raw):
        """Renders an attribute value at the type the setter actually declares.

        The declared type decides, not the value's shape: a badge's text may read "12"
        and still be a String, and a type="PRIMARY" is an enum constant rather than a
        word. Only when the type is unknown does the shape get a say.
        """
        if type_name in ('boolean', 'Boolean'):
            return raw
        if type_name in ('int', 'Integer', 'long', 'Long', 'double', 'Double', 'float'):
            return raw
        if type_name in ('String', 'CharSequence'):
            return '"%s"' % escape(raw)
        if self.oracle.is_enum(type_name):
            if not re.fullmatch(r'[A-Za-z][A-Za-z0-9_]*', raw):
                raise Unsupported('%s is not a constant of %s' % (raw, type_name))
            return '%s.%s' % (self.oracle.qualify(type_name), raw)
        # unknown type: fall back to the value's shape
        if raw in ('true', 'false') or re.fullmatch(r'-?\d+', raw):
            return raw
        return '"%s"' % escape(raw)


def _unused_literal(value):
    if value in ('true', 'false'):
        return value
    if re.fullmatch(r'-?\d+', value):
        return value
    if re.fullmatch(r'[A-Z][A-Z0-9_]*', value):
        return value                      # an enum constant; javac resolves the type
    return '"%s"' % escape(value)


def escape(text):
    return text.replace('\\', '\\\\').replace('"', '\\"').replace('\n', ' ').strip()


def main():
    if len(sys.argv) != 4:
        print(__doc__)
        return 2
    template_root, output_root, services_root = sys.argv[1:4]
    oracle = TypeOracle([r for r in os.environ.get('UIBINDER_SOURCE_ROOTS', '').split(os.pathsep)
                         if r] or [template_root])
    written = 0
    for dirpath, _, files in os.walk(template_root):
        for name in sorted(files):
            if not name.endswith('.ui.xml'):
                continue
            path = os.path.join(dirpath, name)
            rel = os.path.relpath(dirpath, template_root)
            owner_pkg = rel.replace(os.sep, '.')
            owner_name = name[:-len('.ui.xml')]
            try:
                written += generate(path, owner_pkg, owner_name, output_root, services_root, oracle)
            except Unsupported as problem:
                print('%s: %s' % (path, problem), file=sys.stderr)
                return 1
    print('generated %d UiBinder implementation%s' % (written, '' if written == 1 else 's'))
    return 0


HANDLER_ADDERS = {
    'ClickEvent': 'addClickHandler',
    'DoubleClickEvent': 'addDoubleClickHandler',
    'ValueChangeEvent': 'addValueChangeHandler',
    'ChangeEvent': 'addChangeHandler',
    'KeyUpEvent': 'addKeyUpHandler',
}

HANDLER_TYPES = {
    'ClickEvent': 'com.google.gwt.event.dom.client.ClickHandler',
    'DoubleClickEvent': 'com.google.gwt.event.dom.client.DoubleClickHandler',
    'ValueChangeEvent': 'com.google.gwt.event.logical.shared.ValueChangeHandler',
    'ChangeEvent': 'com.google.gwt.event.dom.client.ChangeHandler',
    'KeyUpEvent': 'com.google.gwt.event.dom.client.KeyUpHandler',
}

HANDLER_METHODS = {
    'ClickEvent': 'onClick',
    'DoubleClickEvent': 'onDoubleClick',
    'ValueChangeEvent': 'onValueChange',
    'ChangeEvent': 'onChange',
    'KeyUpEvent': 'onKeyUp',
}


def owner_handlers(source_path):
    """The owner's @UiHandler methods: which field, which event, which method."""
    if not os.path.exists(source_path):
        return []
    text = io.open(source_path, encoding='utf-8').read()
    found = []
    for match in re.finditer(
            r'@UiHandler\(\s*(?:\{)?\s*"([^"]+)"\s*(?:\})?\s*\)\s*'
            r'(?:public|protected|private|\s)*\s*void\s+([A-Za-z0-9_]+)\s*\(\s*'
            r'(?:final\s+)?([A-Za-z0-9_.]+)', text):
        found.append((match.group(1), match.group(2), match.group(3).split('.')[-1]))
    return found


def generate(path, owner_pkg, owner_name, output_root, services_root, oracle):
    tree = ET.parse(path)
    gen = Generator(owner_pkg, owner_name, tree.getroot(), oracle)
    root_var = None
    for child in tree.getroot():
        root_var = gen.emit(child) or root_var
    if root_var is None:
        raise Unsupported('template has no widget root')

    handlers = []
    for field, method, event in owner_handlers(os.path.join(os.path.dirname(path),
                                                            owner_name + '.java')):
        var = gen.fields.get(field)
        if var is None:
            raise Unsupported('@UiHandler names %s, which no ui:field declares' % field)
        if event not in HANDLER_ADDERS:
            raise Unsupported('@UiHandler on %s takes %s, which is not wired yet'
                              % (field, event))
        handlers.append(
            '        %s.%s(new %s%s() {\n'
            '            @Override\n'
            '            public void %s(final %s event) {\n'
            '                owner.%s(event);\n'
            '            }\n'
            '        });'
            % (var, HANDLER_ADDERS[event], HANDLER_TYPES[event],
               '<?>' if event == 'ValueChangeEvent' else '',
               HANDLER_METHODS[event],
               'com.google.gwt.event.dom.client.' + event if 'Key' in event or 'Click' in event
               or 'Change' == event[:6] and event == 'ChangeEvent'
               else 'com.google.gwt.event.logical.shared.' + event,
               method))

    binder = '%s.%s.Binder' % (owner_pkg, owner_name)
    impl = '%s_BinderImpl' % owner_name
    out_dir = os.path.join(output_root, owner_pkg.replace('.', os.sep))
    os.makedirs(out_dir, exist_ok=True)
    body = '\n'.join(gen.lines)
    assigns = '\n'.join('        owner.%s = %s;' % (f, v) for f, v in gen.fields.items())
    with open(os.path.join(out_dir, impl + '.java'), 'w', encoding='utf-8') as handle:
        handle.write(TEMPLATE.format(pkg=owner_pkg, impl=impl, owner=owner_name,
                                     body=body, assigns=assigns + ('\n' if assigns else '')
                                     + '\n'.join(handlers), root=root_var,
                                     root_type=gen.root_type))
    os.makedirs(services_root, exist_ok=True)
    with open(os.path.join(services_root, binder), 'w', encoding='utf-8') as handle:
        handle.write('%s.%s\n' % (owner_pkg, impl))
    return 1


TEMPLATE = '''// Generated from {owner}.ui.xml. Do not edit.
package {pkg};

/** UiBinder implementation for {{@link {owner}}}, generated for the TeaVM backend. */
public class {impl} implements {owner}.Binder {{

    @Override
    public {root_type} createAndBindUi(final {owner} owner) {{
{body}
{assigns}
        return {root};
    }}
}}
'''


if __name__ == '__main__':
    sys.exit(main())
