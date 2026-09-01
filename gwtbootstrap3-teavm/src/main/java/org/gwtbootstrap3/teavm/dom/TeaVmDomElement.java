package org.gwtbootstrap3.teavm.dom;

import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.xml.Node;

/**
 * Small TeaVM DOM wrapper matching the shape needed by the future Bootstrap DOM seam.
 */
public final class TeaVmDomElement {

    private final HTMLElement element;

    public TeaVmDomElement(final HTMLElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
        this.element = element;
    }

    public HTMLElement unwrap() {
        return element;
    }

    public String getAttribute(final String name) {
        return element.getAttribute(name);
    }

    public void setAttribute(final String name, final String value) {
        element.setAttribute(name, value);
    }

    public void removeAttribute(final String name) {
        element.removeAttribute(name);
    }

    public void addClass(final String className) {
        element.getClassList().add(className);
    }

    public void removeClass(final String className) {
        element.getClassList().remove(className);
    }

    public boolean hasClass(final String className) {
        return element.getClassList().contains(className);
    }

    public void setText(final String text) {
        element.setTextContent(text);
    }

    public String getText() {
        return element.getTextContent();
    }

    public void appendChild(final TeaVmDomElement child) {
        element.appendChild((Node) child.unwrap());
    }
}
