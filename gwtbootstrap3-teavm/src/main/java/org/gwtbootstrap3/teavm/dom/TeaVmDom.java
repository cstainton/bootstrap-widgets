package org.gwtbootstrap3.teavm.dom;

import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;

/**
 * TeaVM entry point for DOM operations used by the experimental backend.
 */
public final class TeaVmDom {

    private TeaVmDom() {
    }

    public static TeaVmDomElement createElement(final String tagName) {
        return new TeaVmDomElement(HTMLDocument.current().createElement(tagName));
    }

    public static TeaVmDomElement getElementById(final String id) {
        return wrapNullable(HTMLDocument.current().getElementById(id));
    }

    public static TeaVmDomElement querySelector(final String selector) {
        return wrapNullable(HTMLDocument.current().querySelector(selector));
    }

    private static TeaVmDomElement wrapNullable(final HTMLElement element) {
        return element == null ? null : new TeaVmDomElement(element);
    }
}
