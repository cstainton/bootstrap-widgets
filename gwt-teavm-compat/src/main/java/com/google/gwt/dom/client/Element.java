/*
 * #%L
 * GWT Bootstrap Modern
 * %%
 * Copyright (C) 2026 Carl Stainton
 * %%
 * Reimplements, over TeaVM's JSO libraries, part of the GWT client API. Class,
 * method and package names follow GWT (https://github.com/gwtproject/gwt),
 * Copyright (C) The GWT Project Authors, licensed under the Apache License,
 * Version 2.0. No GWT source is included.
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
package com.google.gwt.dom.client;

import org.teavm.jso.JSBody;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.xml.Node;

public class Element {
    private final HTMLElement element;
    private final Style style;

    public Element(final HTMLElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
        this.element = element;
        this.style = new Style(element);
    }

    public HTMLElement unwrap() {
        return element;
    }

    @SuppressWarnings("unchecked")
    public <T extends Element> T cast() {
        return (T) this;
    }

    public Style getStyle() {
        return style;
    }

    public void setInnerText(final String text) {
        element.setInnerText(text == null ? "" : text);
    }

    public String getInnerText() {
        return element.getInnerText();
    }

    public void setInnerHTML(final String html) {
        element.setInnerHTML(html == null ? "" : html);
    }

    public String getInnerHTML() {
        return element.getInnerHTML();
    }

    public void setAttribute(final String name, final String value) {
        element.setAttribute(name, value == null ? "" : value);
    }

    public String getAttribute(final String name) {
        return element.getAttribute(name);
    }

    public void removeAttribute(final String name) {
        element.removeAttribute(name);
    }

    /**
     * Sets a live DOM property. Properties such as {@code checked} and {@code value}
     * must not be written as attributes: the attribute only seeds the initial value,
     * so attribute writes are ignored once the user has interacted with the control.
     */
    public void setPropertyBoolean(final String name, final boolean value) {
        setBooleanProperty(element, name, value);
    }

    public boolean getPropertyBoolean(final String name) {
        return getBooleanProperty(element, name);
    }

    public void setPropertyString(final String name, final String value) {
        setStringProperty(element, name, value == null ? "" : value);
    }

    public String getPropertyString(final String name) {
        return getStringProperty(element, name);
    }

    public void setPropertyInt(final String name, final int value) {
        setIntProperty(element, name, value);
    }

    public int getPropertyInt(final String name) {
        return getIntProperty(element, name);
    }

    @JSBody(params = {"el", "name", "value"}, script = "el[name] = value;")
    private static native void setBooleanProperty(HTMLElement el, String name, boolean value);

    @JSBody(params = {"el", "name"}, script = "return !!el[name];")
    private static native boolean getBooleanProperty(HTMLElement el, String name);

    @JSBody(params = {"el", "name", "value"}, script = "el[name] = value;")
    private static native void setStringProperty(HTMLElement el, String name, String value);

    @JSBody(params = {"el", "name"}, script = "var v = el[name]; return v == null ? null : String(v);")
    private static native String getStringProperty(HTMLElement el, String name);

    @JSBody(params = {"el", "name", "value"}, script = "el[name] = value;")
    private static native void setIntProperty(HTMLElement el, String name, int value);

    @JSBody(params = {"el", "name"}, script = "var v = el[name]; return v == null ? 0 : v | 0;")
    private static native int getIntProperty(HTMLElement el, String name);

    public boolean hasAttribute(final String name) {
        return element.hasAttribute(name);
    }

    public Element getFirstChildElement() {
        final org.teavm.jso.dom.html.HTMLElement first = firstElementChild(element);
        return first == null ? null : new Element(first);
    }

    @JSBody(params = {"el"}, script = "return el.firstElementChild;")
    private static native HTMLElement firstElementChild(HTMLElement el);

    public Element getParentElement() {
        final HTMLElement parent = parentElement(element);
        return parent == null ? null : new Element(parent);
    }

    @JSBody(params = {"el"}, script = "return el.parentElement;")
    private static native HTMLElement parentElement(HTMLElement el);

    public void focus() {
        element.focus();
    }

    public void blur() {
        element.blur();
    }

    public String getTagName() {
        return element.getTagName();
    }

    public void setId(final String id) {
        element.setId(id == null ? "" : id);
    }

    public String getId() {
        return element.getId();
    }

    public void setClassName(final String className) {
        element.setClassName(className == null ? "" : className);
    }

    public String getClassName() {
        return element.getClassName();
    }

    public void addClassName(final String className) {
        if (className != null && !className.isEmpty()) {
            element.getClassList().add(className);
        }
    }

    public void removeClassName(final String className) {
        if (className != null && !className.isEmpty()) {
            element.getClassList().remove(className);
        }
    }

    public boolean hasClassName(final String className) {
        return className != null && element.getClassList().contains(className);
    }

    public void appendChild(final Element child) {
        element.appendChild((Node) child.unwrap());
    }

    public void insertBefore(final Element child, final Element before) {
        element.insertBefore((Node) child.unwrap(), before == null ? null : (Node) before.unwrap());
    }

    public void insertFirst(final Element child) {
        final Node firstChild = element.getFirstChild();
        if (firstChild == null) {
            appendChild(child);
        } else {
            element.insertBefore((Node) child.unwrap(), firstChild);
        }
    }

    public void removeFromParent() {
        final Node parent = element.getParentNode();
        if (parent != null) {
            parent.removeChild((Node) element);
        }
    }

    public void setInnerSafeHtml(final com.google.gwt.safehtml.shared.SafeHtml html) {
        setInnerHTML(html == null ? "" : html.asString());
    }

    public boolean hasParentElement() {
        return parentElement(element) != null;
    }

    public boolean isOrHasChild(final Element child) {
        return child != null && contains(element, child.unwrap());
    }

    public void removeChild(final Element child) {
        if (child != null) {
            element.removeChild((org.teavm.jso.dom.xml.Node) child.unwrap());
        }
    }

    public void removeAllChildren() {
        setInnerHTML("");
    }

    public void scrollIntoView() {
        scrollTo(element);
    }

    @JSBody(params = {"el", "child"}, script = "return el === child || el.contains(child);")
    private static native boolean contains(HTMLElement el, HTMLElement child);

    @JSBody(params = {"el"}, script = "el.scrollIntoView();")
    private static native void scrollTo(HTMLElement el);

    public int getTabIndex() {
        return getPropertyInt("tabIndex");
    }

    public void setTabIndex(final int index) {
        setPropertyInt("tabIndex", index);
    }

    public void setAccessKey(final String key) {
        if (key == null || key.isEmpty()) {
            removeAttribute("accesskey");
        } else {
            setAttribute("accesskey", key);
        }
    }

    public String getAccessKey() {
        return getAttribute("accesskey");
    }
}
