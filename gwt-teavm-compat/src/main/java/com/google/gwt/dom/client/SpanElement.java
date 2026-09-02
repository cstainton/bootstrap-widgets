package com.google.gwt.dom.client;

import org.teavm.jso.dom.html.HTMLElement;

/** Typed view of a {@code <Span>}-family element. */
public class SpanElement extends Element {

    public SpanElement(final HTMLElement element) {
        super(element);
    }

    public static SpanElement as(final Element element) {
        return element == null ? null : new SpanElement(element.unwrap());
    }

    public static final String TAG = "span";

    /** True when the element is a {@code <span>}. */
    public static boolean is(final Element element) {
        if (element == null) {
            return false;
        }
        final String tag = element.getTagName();
        return "span".equalsIgnoreCase(tag);
    }
}
