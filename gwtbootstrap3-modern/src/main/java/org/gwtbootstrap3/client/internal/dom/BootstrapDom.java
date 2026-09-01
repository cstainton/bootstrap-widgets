package org.gwtbootstrap3.client.internal.dom;

import com.google.gwt.dom.client.Element;

/**
 * Runtime-specific DOM adapter entry point.
 */
public final class BootstrapDom {

    private BootstrapDom() {
    }

    public static BootstrapDomElement from(Element element) {
        return new GwtBootstrapDomElement(element);
    }
}
