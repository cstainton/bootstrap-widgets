package com.google.gwt.dom.client;

import org.teavm.jso.dom.html.HTMLElement;

/** Typed view of a {@code <Input>}-family element. */
public class InputElement extends Element {

    public InputElement(final HTMLElement element) {
        super(element);
    }

    public static InputElement as(final Element element) {
        return element == null ? null : new InputElement(element.unwrap());
    }

    public String getValue() {
        return getPropertyString("value");
    }

    public void setValue(final String value) {
        setPropertyString("value", value);
    }

    public boolean isChecked() {
        return getPropertyBoolean("checked");
    }

    public void setChecked(final boolean checked) {
        setPropertyBoolean("checked", checked);
    }

    public boolean isDefaultChecked() {
        return getPropertyBoolean("defaultChecked");
    }

    public void setDefaultChecked(final boolean checked) {
        setPropertyBoolean("defaultChecked", checked);
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

    public String getType() {
        return getPropertyString("type");
    }

    public static final String TAG = "input";

    /** True when the element is a {@code <input>}. */
    public static boolean is(final Element element) {
        if (element == null) {
            return false;
        }
        final String tag = element.getTagName();
        return "input".equalsIgnoreCase(tag);
    }
}
