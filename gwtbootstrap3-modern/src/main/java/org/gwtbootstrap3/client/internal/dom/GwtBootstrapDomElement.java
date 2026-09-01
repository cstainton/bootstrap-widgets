package org.gwtbootstrap3.client.internal.dom;

import com.google.gwt.dom.client.Element;

final class GwtBootstrapDomElement implements BootstrapDomElement {

    private final Element element;

    GwtBootstrapDomElement(Element element) {
        this.element = element;
    }

    @Override
    public void addClass(String className) {
        element.addClassName(className);
    }

    @Override
    public void removeClass(String className) {
        element.removeClassName(className);
    }

    @Override
    public boolean hasClass(String className) {
        return element.hasClassName(className);
    }

    @Override
    public String getAttribute(String name) {
        return element.getAttribute(name);
    }

    @Override
    public void setAttribute(String name, String value) {
        element.setAttribute(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        element.removeAttribute(name);
    }

    @Override
    public Object unwrap() {
        return element;
    }
}
