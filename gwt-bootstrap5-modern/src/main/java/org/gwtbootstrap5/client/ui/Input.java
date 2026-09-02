package org.gwtbootstrap5.client.ui;

public class Input extends ElementPanel {

    public Input() {
        this("text");
    }

    public Input(String type) {
        super("input");
        addStyleName("form-control");
        setType(type);
    }

    public String getValue() {
        return getElement().getPropertyString("value");
    }

    public void setValue(String value) {
        getElement().setPropertyString("value", value == null ? "" : value);
    }

    public void setPlaceholder(String placeholder) {
        getElement().setAttribute("placeholder", placeholder == null ? "" : placeholder);
    }

    public void setType(String type) {
        getElement().setAttribute("type", type == null ? "text" : type);
    }
}
