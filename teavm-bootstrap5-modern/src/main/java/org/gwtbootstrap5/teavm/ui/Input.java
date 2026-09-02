package org.gwtbootstrap5.teavm.ui;

public class Input extends Widget {

    public Input() {
        this("text");
    }

    public Input(final String type) {
        super("input");
        addStyleName("form-control");
        setType(type);
    }

    public String getValue() {
        return getElement().getAttribute("value");
    }

    public Input setValue(final String value) {
        setAttribute("value", value == null ? "" : value);
        return this;
    }

    public Input setPlaceholder(final String placeholder) {
        setAttribute("placeholder", placeholder == null ? "" : placeholder);
        return this;
    }

    public Input setType(final String type) {
        setAttribute("type", type == null ? "text" : type);
        return this;
    }
}
