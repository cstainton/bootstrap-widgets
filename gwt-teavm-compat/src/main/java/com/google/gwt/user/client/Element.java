package com.google.gwt.user.client;

import org.teavm.jso.dom.html.HTMLElement;

/**
 * GWT's legacy user-space element type. It exists so that the deprecated
 * {@code insert(Widget, Element, int, boolean)} overloads keep their signature.
 */
@Deprecated
public class Element extends com.google.gwt.dom.client.Element {

    public Element(final HTMLElement element) {
        super(element);
    }

    /** Widens a DOM element to the legacy user-space type. */
    public static Element as(final com.google.gwt.dom.client.Element element) {
        return element == null ? null : new Element(element.unwrap());
    }
}
