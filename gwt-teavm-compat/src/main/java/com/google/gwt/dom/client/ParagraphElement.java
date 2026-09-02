package com.google.gwt.dom.client;

import org.teavm.jso.dom.html.HTMLElement;

/** Typed view of a {@code <Paragraph>}-family element. */
public class ParagraphElement extends Element {

    public ParagraphElement(final HTMLElement element) {
        super(element);
    }

    public static ParagraphElement as(final Element element) {
        return element == null ? null : new ParagraphElement(element.unwrap());
    }

    public static final String TAG = "p";

    /** True when the element is a {@code <p>}. */
    public static boolean is(final Element element) {
        if (element == null) {
            return false;
        }
        final String tag = element.getTagName();
        return "p".equalsIgnoreCase(tag);
    }
}
