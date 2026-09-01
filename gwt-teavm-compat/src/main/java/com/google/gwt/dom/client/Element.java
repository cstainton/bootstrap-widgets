package com.google.gwt.dom.client;

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

    public void setPropertyBoolean(final String name, final boolean value) {
        if (value) {
            element.setAttribute(name, name);
        } else {
            element.removeAttribute(name);
        }
    }

    public boolean getPropertyBoolean(final String name) {
        return element.hasAttribute(name);
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
}
