package org.gwtbootstrap3.teavm.dom;

/**
 * TeaVM entry point for DOM operations used by the experimental backend.
 */
public final class TeaVmDom {

    private TeaVmDom() {
    }

    public static TeaVmDomElement createElement(final String tagName) {
        return TeaVmDomElement.create(tagName);
    }

    public static TeaVmDomElement getElementById(final String id) {
        return TeaVmDomElement.byId(id);
    }

    public static TeaVmDomElement querySelector(final String selector) {
        return TeaVmDomElement.query(selector);
    }
}
