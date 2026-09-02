package com.google.gwt.dom.client;

import org.teavm.jso.dom.html.HTMLElement;

/** Typed view of a {@code <Button>}-family element. */
public class ButtonElement extends Element {

    public ButtonElement(final HTMLElement element) {
        super(element);
    }

    public static ButtonElement as(final Element element) {
        return element == null ? null : new ButtonElement(element.unwrap());
    }

    public String getValue() {
        return getPropertyString("value");
    }

    public void setValue(final String value) {
        setPropertyString("value", value);
    }

    public String getName() {
        return getPropertyString("name");
    }

    public void setName(final String name) {
        setPropertyString("name", name);
    }

    public boolean isDisabled() {
        return getPropertyBoolean("disabled");
    }

    public void setDisabled(final boolean disabled) {
        setPropertyBoolean("disabled", disabled);
    }

    public static final String TAG = "button";

    /** True when the element is a {@code <button>}. */
    public static boolean is(final Element element) {
        if (element == null) {
            return false;
        }
        final String tag = element.getTagName();
        return "button".equalsIgnoreCase(tag);
    }
}
