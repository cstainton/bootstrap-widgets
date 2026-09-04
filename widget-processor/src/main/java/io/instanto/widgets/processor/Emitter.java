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
    private int counter;
    private String rootType;

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

    /** Emits an element and its children, returning the variable holding it. */
    String emit(final Node element, final String parent) {
        final String uri = element.getNamespaceURI();
        if (uri == null || !uri.startsWith(IMPORT_PREFIX)) {
            throw new UiBinderProcessor.Failure(
                    "<" + element.getNodeName() + "> is not from a urn:import namespace", owner);
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
        if (!text.isEmpty()) {
            body.append("        ").append(var).append(".setText(\"")
                .append(escape(text)).append("\");\n");
        }
        if (field != null) {
            fields.put(field, var);
        }

        final NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                emit(child, var);
            }
        }
        if (parent != null) {
            body.append("        ").append(parent).append(".add(").append(var).append(");\n");
        }
        return var;
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
            throw new UiBinderProcessor.Failure(
                    simple + " has no " + setter + " for the " + name + " attribute", owner);
        }
        body.append("        ").append(var).append('.').append(setter).append('(')
            .append(render(method.getParameters().get(0).asType(), value)).append(");\n");
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
            attrs.put(name, attr.getNodeValue());
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
                final String event = env.getTypeUtils()
                        .erasure(method.getParameters().get(0).asType()).toString();
                final Handlers.Binding binding = Handlers.forEvent(event);
                if (binding == null) {
                    throw new UiBinderProcessor.Failure(
                            event + " is not an event this generator wires yet", method);
                }
                for (final String fieldName : Handlers.namedFields(mirror)) {
                    final String var = fields.get(fieldName);
                    if (var == null) {
                        throw new UiBinderProcessor.Failure("@UiHandler names " + fieldName
                                + ", which no ui:field declares", method);
                    }
                    out.append("        ").append(var).append('.').append(binding.adder)
                       .append("(new ").append(binding.handler)
                       .append(binding.generic ? "<>" : "").append("() {\n")
                       .append("            @Override\n")
                       .append("            public void ").append(binding.method)
                       .append("(final ").append(event).append(binding.generic ? "<?>" : "")
                       .append(" event) {\n")
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
                   .replace("\n", " ").replace("\r", " ").trim();
    }
}
