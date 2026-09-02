package com.google.gwt.dom.client;

import org.teavm.jso.dom.html.HTMLElement;

/** Typed view of a {@code <TextArea>}-family element. */
public class TextAreaElement extends Element {

    public TextAreaElement(final HTMLElement element) {
        super(element);
    }

    public static TextAreaElement as(final Element element) {
        return element == null ? null : new TextAreaElement(element.unwrap());
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

    public int getRows() {
        return getPropertyInt("rows");
    }

    public void setRows(final int rows) {
        setPropertyInt("rows", rows);
    }

    public int getCols() {
        return getPropertyInt("cols");
    }

    public void setCols(final int cols) {
        setPropertyInt("cols", cols);
    }

    public static final String TAG = "textarea";

    /** True when the element is a {@code <textarea>}. */
    public static boolean is(final Element element) {
        if (element == null) {
            return false;
        }
        final String tag = element.getTagName();
        return "textarea".equalsIgnoreCase(tag);
    }
}
