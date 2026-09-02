package com.google.gwt.dom.client;

import org.teavm.jso.dom.html.HTMLElement;

/** Typed view of a {@code <Anchor>}-family element. */
public class AnchorElement extends Element {

    public AnchorElement(final HTMLElement element) {
        super(element);
    }

    public static AnchorElement as(final Element element) {
        return element == null ? null : new AnchorElement(element.unwrap());
    }

    public String getHref() {
        return getAttribute("href");
    }

    public void setHref(final String href) {
        setAttribute("href", href);
    }

    public String getTarget() {
        return getAttribute("target");
    }

    public void setTarget(final String target) {
        setAttribute("target", target);
    }

    public void setName(final String name) {
        setPropertyString("name", name);
    }

    public static final String TAG = "a";

    /** True when the element is a {@code <a>}. */
    public static boolean is(final Element element) {
        if (element == null) {
            return false;
        }
        final String tag = element.getTagName();
        return "a".equalsIgnoreCase(tag);
    }
}
