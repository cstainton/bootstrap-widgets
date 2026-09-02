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

    public String getPlaceholder() {
        return getElement().getAttribute("placeholder");
    }

    public void setType(String type) {
        getElement().setAttribute("type", type == null ? "text" : type);
    }

    public String getType() {
        return getElement().getAttribute("type");
    }

    public void setMin(String min) {
        getElement().setAttribute("min", min == null ? "" : min);
    }

    public void setMax(String max) {
        getElement().setAttribute("max", max == null ? "" : max);
    }

    public void setName(String name) {
        getElement().setAttribute("name", name == null ? "" : name);
    }

    public String getName() {
        return getElement().getAttribute("name");
    }
}
