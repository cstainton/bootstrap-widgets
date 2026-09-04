#!/usr/bin/env python3
"""Generate the TeaVM equivalent of GWT's ahead-of-time module declarations.

A GWT module descriptor already says everything a module needs:

    <public path="client/resource">          where its assets live
    <stylesheet src="css/nouislider....css"/> which stylesheet to inject
    <entry-point class="...SliderEntryPoint"/> what to run at startup

and its ClientBundle names the scripts. GWT reads that at compile time. TeaVM has
no module system, so the same declarations are read here instead and turned into
ordinary Java: one Resources class per module that injects the same stylesheets
and scripts, plus the assets copied where the page can fetch them.

The point is that nothing new gets declared. The .gwt.xml is maintained anyway
because GWT needs it, and it stays the single source of truth for both backends.

None of this is imposed on an application. A TeaVM application never declares a
module and never writes a template: it constructs a widget, and the widget asks
for whatever it needs. This only removes the hand-written duplication behind
that.

The assets are written twice on purpose: once where this build's own page can
fetch them, and once inside the jar under META-INF/bootstrap5-assets, so an
application depending on this artifact can unpack them next to its own page.
That is what <public path> buys a GWT module -- the assets travel with it -- and
without it the widgets would only work in this repository's showcase.

Usage: generate-teavm-modules.py <extras-source-root> <output-root> <asset-root>
                                 [<packaged-asset-root>]
"""
import io
import os
import re
import shutil
import sys
import xml.etree.ElementTree as ET

TEMPLATE = '''// Generated from {module_file}. Do not edit.
package {package};

import io.instanto.bootstrap5.client.Bootstrap5Resources;

/**
 * Loads what the {name} module declares, on the TeaVM backend.
 *
 * <p>GWT injects these from {module_file} and the module's entry point. TeaVM has no
 * module system, so they are fetched by URL from wherever the module was deployed. An
 * application does not call this: the widgets do, when they are constructed.</p>
 */
public final class {klass} {{

    private static boolean injected;

    private {klass}() {{
    }}

    /** Injects this module's resources once; further calls do nothing. */
    public static void ensureInjected() {{
        if (injected) {{
            return;
        }}
        injected = true;
{body}    }}
}}
'''


def find_bundle_scripts(module_dir):
    """The scripts a module's ClientBundle inlines on GWT."""
    scripts = []
    for dirpath, _, files in os.walk(module_dir):
        for name in files:
            if not name.endswith('ClientBundle.java'):
                continue
            text = io.open(os.path.join(dirpath, name), encoding='utf-8').read()
            for match in re.findall(r'@Source\("([^"]+\.js)"\)', text):
                scripts.append(match.rsplit('/', 1)[-1])
    return scripts


def main():
    if len(sys.argv) not in (4, 5):
        print(__doc__)
        return 2
    source_root, output_root, asset_root = sys.argv[1:4]
    packaged_root = sys.argv[4] if len(sys.argv) > 4 else None
    generated = 0

    for dirpath, _, files in sorted(os.walk(source_root)):
        for name in sorted(files):
            if not name.endswith('.gwt.xml'):
                continue
            path = os.path.join(dirpath, name)
            root = ET.parse(path).getroot()

            stylesheets = [e.get('src').rsplit('/', 1)[-1]
                           for e in root.iter('stylesheet') if e.get('src')]
            scripts = find_bundle_scripts(dirpath)
            if not stylesheets and not scripts:
                continue

            module = name[:-len('.gwt.xml')]
            package = os.path.relpath(dirpath, source_root).replace(os.sep, '.') + '.client'
            klass = module + 'Resources'
            prefix = 'bootstrap5-' + module.lower() + '-'

            lines = []
            for sheet in stylesheets:
                lines.append('        Bootstrap5Resources.stylesheet("%scss-%s",\n'
                             '                Bootstrap5Resources.cssBase() + "%s");\n'
                             % (prefix, sheet.split('-')[0], sheet))
            for script in scripts:
                lines.append('        Bootstrap5Resources.script("%sjs-%s",\n'
                             '                Bootstrap5Resources.jsBase() + "%s");\n'
                             % (prefix, script.split('-')[0], script))

            out_dir = os.path.join(output_root, package.replace('.', os.sep))
            os.makedirs(out_dir, exist_ok=True)
            io.open(os.path.join(out_dir, klass + '.java'), 'w', encoding='utf-8').write(
                TEMPLATE.format(module_file=name, package=package, name=module,
                                klass=klass, body=''.join(lines)))

            # the assets themselves, from wherever <public path> puts them
            for public in root.iter('public'):
                base = os.path.join(dirpath, public.get('path', ''))
                for kind in ('css', 'js'):
                    src = os.path.join(base, kind)
                    if not os.path.isdir(src):
                        continue
                    for target in filter(None, (asset_root, packaged_root)):
                        dest = os.path.join(target, kind)
                        os.makedirs(dest, exist_ok=True)
                        for asset in os.listdir(src):
                            if asset.endswith('.' + kind):
                                shutil.copy2(os.path.join(src, asset),
                                             os.path.join(dest, asset))
            # a module may inline scripts without a public path
            for dirpath2, _, files2 in os.walk(dirpath):
                for asset in files2:
                    if asset in scripts:
                        for target in filter(None, (asset_root, packaged_root)):
                            dest = os.path.join(target, 'js')
                            os.makedirs(dest, exist_ok=True)
                            shutil.copy2(os.path.join(dirpath2, asset),
                                         os.path.join(dest, asset))

            generated += 1
            print('  %s -> %s (%d stylesheet%s, %d script%s)'
                  % (name, klass, len(stylesheets), '' if len(stylesheets) == 1 else 's',
                     len(scripts), '' if len(scripts) == 1 else 's'))

    print('generated %d TeaVM module%s' % (generated, '' if generated == 1 else 's'))
    return 0


if __name__ == '__main__':
    sys.exit(main())
