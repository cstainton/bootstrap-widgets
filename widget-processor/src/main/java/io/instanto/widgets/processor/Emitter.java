/*
 * #%L
 * GWT Bootstrap
 * %%
 * Copyright (C) 2026 Carl Stainton
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.instanto.widgets.processor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Writes the widget tree as Java, asking javac about every type it touches.
 *
 * <p>This is the part that a source-scanning generator can only approximate. Whether a
 * widget has a {@code @UiConstructor}, what type a setter takes, whether that type is an
 * enum, and what a class inherits are all questions {@code Elements} and {@code Types}
 * answer exactly, including through generics and overloads.</p>
 */
final class Emitter {

    private static final String UI_NS = "urn:ui:com.google.gwt.uibinder";
    private static final String IMPORT_PREFIX = "urn:import:";

    private final ProcessingEnvironment env;
    private final Element owner;
    private final StringBuilder body = new StringBuilder();
    private final Map<String, String> fields = new LinkedHashMap<>();
    private final Map<String, javax.lang.model.type.TypeMirror> fieldTypes =
            new LinkedHashMap<>();
    private int counter;
    private String rootType;
    private final java.util.Set<String> domVars = new java.util.HashSet<>();
    private final Map<String, String> styleClasses = new LinkedHashMap<>();
    private String styleCss;

    Emitter(final ProcessingEnvironment env, final Element owner) {
        this.env = env;
        this.owner = owner;
    }

    String body() {
        return body.toString();
    }

    String rootType() {
        return rootType == null ? "com.google.gwt.user.client.ui.Widget" : rootType;
    }

    /**
     * Reads a {@code <ui:style>} block, if the template has one.
     *
     * <p>GWT turns these into a CssResource with a method per class and obfuscated names,
     * which is what stops two templates that both declare ".spacing" colliding. Every
     * block in this repository is the plain form with no declared type, so rather than
     * generate an interface the class names are prefixed with the owner's name: unique
     * per template, and still legible in the inspector, which obfuscation is not.</p>
     */
    void readStyles(final Node root) {
        final NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node child = children.item(i);
            if (child.getNodeType() != Node.ELEMENT_NODE
                    || !UI_NS.equals(child.getNamespaceURI())
                    || !"style".equals(child.getLocalName())) {
                continue;
            }
            if (child.getAttributes().getLength() > 0) {
                throw new UiBinderProcessor.Failure(
                        "a typed <ui:style> is not supported yet", owner);
            }
            String css = child.getTextContent();

            // @external tells GWT to leave a class name alone rather than obfuscate it,
            // because something outside the template -- a vendored stylesheet, a plugin
            // -- already owns it. It is a CssResource directive, not CSS, so it also has
            // to come back out before the text reaches the browser.
            final java.util.Set<String> external = new java.util.HashSet<>();
            final java.util.regex.Matcher directive = java.util.regex.Pattern
                    .compile("@external\\s+([^;]*);").matcher(css);
            while (directive.find()) {
                for (final String name : directive.group(1).trim().split("\\s*,\\s*")) {
                    if (!name.isEmpty()) {
                        external.add(name);
                    }
                }
            }
            css = css.replaceAll("@external\\s+[^;]*;", "");

            for (final String selector : selectors(css)) {
                final java.util.regex.Matcher declared = java.util.regex.Pattern
                        .compile("\\.([A-Za-z][\\w-]*)").matcher(selector);
                while (declared.find()) {
                    final String name = declared.group(1);
                    if (!external.contains(name)) {
                        styleClasses.put(name, owner.getSimpleName() + "-" + name);
                    }
                }
            }
            for (final Map.Entry<String, String> entry : styleClasses.entrySet()) {
                css = css.replaceAll("\\." + java.util.regex.Pattern.quote(entry.getKey())
                        + "(?![\\w-])", "." + entry.getValue());
            }
            styleCss = css;
        }
    }

    /**
     * The selector of every rule in a stylesheet.
     *
     * <p>Enough of a CSS parser to find class names, and no more. Selectors are richer
     * than one leading class -- ".buttons > button" and ".labels span" both appear in the
     * showcase -- so the text before each brace is taken whole and every class in it
     * collected. Declaration bodies are skipped, so a colour like "#fcf2f2" or a
     * property value can never be mistaken for a selector.</p>
     */
    private static List<String> selectors(final String css) {
        final List<String> found = new ArrayList<>();
        int depth = 0;
        int start = 0;
        for (int i = 0; i < css.length(); i++) {
            final char c = css.charAt(i);
            if (c == '{') {
                if (depth == 0) {
                    final String selector = css.substring(start, i).trim();
                    if (!selector.startsWith("@")) {
                        found.add(selector);
                    }
                }
                depth++;
            } else if (c == '}') {
                depth = Math.max(0, depth - 1);
                if (depth == 0) {
                    start = i + 1;
                }
            }
        }
        return found;
    }

    /** The CSS this template declares, already prefixed, or null. */
    String styles() {
        return styleCss;
    }

    /** Emits an element and its children, returning the variable holding it. */
    String emit(final Node element, final String parent) {
        final String uri = element.getNamespaceURI();
        if (uri == null || !uri.startsWith(IMPORT_PREFIX)) {
            return emitHtml(element, parent);
        }
        final String pkg = uri.substring(IMPORT_PREFIX.length());
        final String simple = element.getLocalName();
        final TypeElement type = env.getElementUtils().getTypeElement(pkg + "." + simple);
        if (type == null) {
            throw new UiBinderProcessor.Failure(
                    pkg + "." + simple + " is not a widget on the compile path", owner);
        }

        final String var = simple.substring(0, 1).toLowerCase() + simple.substring(1) + (++counter);
        final Map<String, String> attrs = attributes(element);
        final String field = attrs.remove("$field");

        final ExecutableElement uiConstructor = uiConstructor(type);
        final List<String> args = new ArrayList<>();
        if (uiConstructor != null) {
            for (final VariableElement param : uiConstructor.getParameters()) {
                final String name = param.getSimpleName().toString();
                final String value = attrs.remove(name);
                if (value == null) {
                    throw new UiBinderProcessor.Failure(simple + " needs the " + name
                            + " attribute its @UiConstructor takes", owner);
                }
                args.add(render(param.asType(), value));
            }
        }

        body.append("        ").append(pkg).append('.').append(simple).append(' ').append(var)
            .append(" = new ").append(pkg).append('.').append(simple)
            .append('(').append(String.join(", ", args)).append(");\n");
        if (rootType == null) {
            rootType = pkg + "." + simple;
        }

        for (final Map.Entry<String, String> attr : attrs.entrySet()) {
            appendSetter(var, type, attr.getKey(), attr.getValue(), simple);
        }
        final String text = directText(element);
        if (!text.isEmpty() && !hasElementChildren(element)) {
            body.append("        ").append(var).append(".setText(\"")
                .append(escapeTrimmed(text)).append("\");\n");
        }
        if (field != null) {
            fields.put(field, var);
            fieldTypes.put(field, type.asType());
        }

        emitChildren(element, var, hasElementChildren(element));
        if (parent != null) {
            attach(parent, var, false);
        }
        return var;
    }

    /**
     * Emits a plain HTML element.
     *
     * <p>GWT wraps markup in an HTMLPanel and slots widgets into placeholders, which is
     * what makes its implementation large. None of the templates here put a widget inside
     * an HTML element -- the markup is inline leaves like strong, br, code and a -- so the
     * element is simply built and appended, and a widget child would be rejected rather
     * than silently misplaced.</p>
     */
    private String emitHtml(final Node element, final String parent) {
        final String tag = element.getLocalName() == null
                ? element.getNodeName() : element.getLocalName();
        final String var = "element" + (++counter);
        domVars.add(var);
        body.append("        com.google.gwt.dom.client.Element ").append(var)
            .append(" = com.google.gwt.dom.client.Document.get().createElement(\"")
            .append(escape(tag)).append("\");\n");
        if (rootType == null) {
            throw new UiBinderProcessor.Failure(
                    "a template has to start with a widget, not <" + tag + ">", owner);
        }

        final Map<String, String> attrs = attributes(element);
        final String field = attrs.remove("$field");
        for (final Map.Entry<String, String> attr : attrs.entrySet()) {
            body.append("        ").append(var).append(".setAttribute(\"")
                .append(escapeTrimmed(attr.getKey())).append("\", \"")
                .append(escapeTrimmed(attr.getValue())).append("\");\n");
        }
        if (field != null) {
            fields.put(field, var);
        }
        emitChildren(element, var, true);
        if (parent != null) {
            attach(parent, var, true);
        }
        return var;
    }

    /** Walks children in document order so text and markup keep their sequence. */
    private void emitChildren(final Node element, final String var, final boolean keepText) {
        final NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                emit(child, var);
            } else if (keepText && child.getNodeType() == Node.TEXT_NODE) {
                // Collapse runs of whitespace but do not trim: the space between
                // "into" and <strong>ordinary Java</strong> is part of the sentence,
                // and trimming it runs the words together.
                final String text = child.getNodeValue().replaceAll("\\s+", " ");
                if (!text.isBlank() || hasElementSibling(child)) {
                    final String target = domVars.contains(var) ? var : var + ".getElement()";
                    body.append("        ").append(target)
                        .append(".appendChild(com.google.gwt.dom.client.Document.get()")
                        .append(".createTextNode(\"").append(escape(text)).append("\"));\n");
                }
            }
        }
    }

    /** Adds a child, which differs depending on whether the parent is a widget. */
    private void attach(final String parent, final String var, final boolean childIsDom) {
        final boolean parentIsDom = domVars.contains(parent);
        if (parentIsDom && !childIsDom) {
            throw new UiBinderProcessor.Failure(
                    "a widget inside an HTML element is not supported yet", owner);
        }
        if (parentIsDom) {
            body.append("        ").append(parent).append(".appendChild(").append(var).append(");\n");
        } else if (childIsDom) {
            body.append("        ").append(parent).append(".getElement().appendChild(")
                .append(var).append(");\n");
        } else {
            body.append("        ").append(parent).append(".add(").append(var).append(");\n");
        }
    }

    /** Turns {style.foo} into the prefixed class name the block now declares. */
    private String resolveStyles(final String value) {
        String out = value;
        final java.util.regex.Matcher m = java.util.regex.Pattern
                .compile("\\{style\\.([A-Za-z][\\w-]*)\\}").matcher(value);
        while (m.find()) {
            final String mapped = styleClasses.get(m.group(1));
            if (mapped == null) {
                throw new UiBinderProcessor.Failure(
                        "{style." + m.group(1) + "} is not declared by this template's <ui:style>",
                        owner);
            }
            out = out.replace(m.group(0), mapped);
        }
        return out;
    }

    /** Whitespace between two inline elements is content, not indentation. */
    private static boolean hasElementSibling(final Node text) {
        return text.getPreviousSibling() != null && text.getNextSibling() != null
                && text.getPreviousSibling().getNodeType() == Node.ELEMENT_NODE
                && text.getNextSibling().getNodeType() == Node.ELEMENT_NODE;
    }

    private static boolean hasElementChildren(final Node element) {
        final NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
                return true;
            }
        }
        return false;
    }

    private void appendSetter(final String var, final TypeElement type, final String name,
            final String value, final String simple) {
        // UiBinder's own pseudo-attributes, which are not setters at all
        if ("addStyleNames".equals(name)) {
            for (final String style : value.trim().split("\\s+")) {
                body.append("        ").append(var).append(".addStyleName(\"")
                    .append(escape(style)).append("\");\n");
            }
            return;
        }
        if ("id".equals(name)) {
            body.append("        ").append(var).append(".getElement().setId(\"")
                .append(escape(value)).append("\");\n");
            return;
        }
        final String setter = "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
        final ExecutableElement method = findSetter(type, setter);
        if (method == null) {
            if (isDomAttribute(name)) {
                // data-testid, aria-* and role have no setter and are not meant to: they
                // belong to the element. A test locating a widget by data-testid is the
                // main reason a template carries one, so passing them through matters.
                body.append("        ").append(var).append(".getElement().setAttribute(\"")
                    .append(escape(name)).append("\", \"").append(escape(value)).append("\");\n");
                return;
            }
            throw new UiBinderProcessor.Failure(
                    simple + " has no " + setter + " for the " + name + " attribute", owner);
        }
        body.append("        ").append(var).append('.').append(setter).append('(')
            .append(render(method.getParameters().get(0).asType(), value)).append(");\n");
    }

    /**
     * Whether an attribute belongs on the element rather than to a setter.
     *
     * <p>Anything hyphenated is a DOM attribute by construction, since a Java setter
     * cannot be named that way; role is the one common exception that is not.</p>
     */
    private static boolean isDomAttribute(final String name) {
        return name.indexOf('-') >= 0 || "role".equals(name);
    }

    /** Renders a value at the type the setter or constructor actually declares. */
    private String render(final TypeMirror target, final String raw) {
        switch (target.getKind()) {
            case BOOLEAN: return raw;
            case INT: case LONG: case SHORT: case BYTE: case FLOAT: case DOUBLE: return raw;
            default: break;
        }
        final Element declared = env.getTypeUtils().asElement(target);
        if (declared instanceof TypeElement) {
            final TypeElement element = (TypeElement) declared;
            if (element.getKind() == ElementKind.ENUM) {
                return element.getQualifiedName() + "." + raw;
            }
            final String name = element.getQualifiedName().toString();
            if ("java.lang.Boolean".equals(name)) {
                return raw;
            }
            if (name.startsWith("java.lang.") && !"java.lang.String".equals(name)
                    && !"java.lang.CharSequence".equals(name)) {
                return raw;                                   // a boxed number
            }
        }
        return "\"" + escape(raw) + "\"";
    }

    private ExecutableElement uiConstructor(final TypeElement type) {
        for (final ExecutableElement ctor : ElementFilter.constructorsIn(
                type.getEnclosedElements())) {
            for (final AnnotationMirror mirror : ctor.getAnnotationMirrors()) {
                if (mirror.getAnnotationType().toString()
                        .equals("com.google.gwt.uibinder.client.UiConstructor")) {
                    return ctor;
                }
            }
        }
        return null;
    }

    /** Walks the hierarchy, because a widget inherits most of its setters. */
    private ExecutableElement findSetter(final TypeElement type, final String name) {
        TypeElement current = type;
        while (current != null) {
            for (final ExecutableElement method : ElementFilter.methodsIn(
                    current.getEnclosedElements())) {
                if (method.getSimpleName().contentEquals(name)
                        && method.getParameters().size() == 1
                        && method.getModifiers().contains(Modifier.PUBLIC)) {
                    return method;
                }
            }
            final TypeMirror parent = current.getSuperclass();
            final Element element = env.getTypeUtils().asElement(parent);
            current = element instanceof TypeElement ? (TypeElement) element : null;
        }
        return null;
    }

    private Map<String, String> attributes(final Node element) {
        final Map<String, String> attrs = new LinkedHashMap<>();
        final NamedNodeMap map = element.getAttributes();
        for (int i = 0; i < map.getLength(); i++) {
            final Node attr = map.item(i);
            final String uri = attr.getNamespaceURI();
            final String name = attr.getLocalName() == null ? attr.getNodeName()
                    : attr.getLocalName();
            if (UI_NS.equals(uri)) {
                if ("field".equals(name)) {
                    attrs.put("$field", attr.getNodeValue());
                }
                continue;
            }
            if ("xmlns".equals(attr.getPrefix()) || "xmlns".equals(name)) {
                continue;
            }
            attrs.put(name, resolveStyles(attr.getNodeValue()));
        }
        return attrs;
    }

    private String directText(final Node element) {
        final StringBuilder text = new StringBuilder();
        final NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE) {
                text.append(child.getNodeValue());
            }
        }
        return text.toString().trim();
    }

    String fieldAssignments() {
        final StringBuilder out = new StringBuilder();
        for (final Map.Entry<String, String> entry : fields.entrySet()) {
            out.append("        owner.").append(entry.getKey()).append(" = ")
               .append(entry.getValue()).append(";\n");
        }
        return out.toString();
    }

    /** Wires @UiHandler methods to the fields they name. */
    String handlerWiring() {
        final StringBuilder out = new StringBuilder();
        for (final ExecutableElement method : ElementFilter.methodsIn(
                owner.getEnclosedElements())) {
            for (final AnnotationMirror mirror : method.getAnnotationMirrors()) {
                if (!mirror.getAnnotationType().toString()
                        .equals("com.google.gwt.uibinder.client.UiHandler")) {
                    continue;
                }
                if (method.getParameters().size() != 1) {
                    throw new UiBinderProcessor.Failure(
                            "@UiHandler methods take exactly one event", method);
                }
                final javax.lang.model.type.TypeMirror event =
                        method.getParameters().get(0).asType();
                for (final String fieldName : Handlers.namedFields(mirror)) {
                    final String var = fields.get(fieldName);
                    if (var == null) {
                        throw new UiBinderProcessor.Failure("@UiHandler names " + fieldName
                                + ", which no ui:field declares", method);
                    }
                    final Handlers.Binding binding = Handlers.resolve(
                            env, fieldTypes.get(fieldName), event);
                    if (binding == null) {
                        throw new UiBinderProcessor.Failure(fieldName + " has no method that "
                                + "takes a handler for " + env.getTypeUtils().erasure(event),
                                method);
                    }
                    out.append("        ").append(var).append('.').append(binding.adder)
                       .append("(new ").append(binding.handler).append("() {\n")
                       .append("            @Override\n")
                       .append("            public void ").append(binding.method)
                       .append("(final ").append(binding.parameter).append(" event) {\n")
                       .append("                owner.").append(method.getSimpleName())
                       .append("(event);\n")
                       .append("            }\n        });\n");
                }
            }
        }
        return out.toString();
    }

    private static String escape(final String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"")
                   .replace("\n", " ").replace("\r", " ");
    }

    /** For attributes and single-value text, where surrounding space is not content. */
    private static String escapeTrimmed(final String text) {
        return escape(text).trim();
    }
}
