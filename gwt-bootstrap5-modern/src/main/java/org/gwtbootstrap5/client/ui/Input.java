package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.HasInputType;
import org.gwtbootstrap5.client.ui.constants.InputType;

public class Input extends ElementPanel implements HasInputType {

    public Input() {
        this("text");
    }

    public Input(String type) {
        super("input");
        addStyleName("form-control");
        setType(type);
    }

    public Input(InputType type) {
        this(type == null ? InputType.TEXT.getType() : type.getType());
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

    @Override
    public void setType(InputType inputType) {
        setType(inputType == null ? InputType.TEXT.getType() : inputType.getType());
    }

    @Override
    public InputType getType() {
        String type = getElement().getAttribute("type");
        for (InputType inputType : InputType.values()) {
            if (inputType.getType().equals(type)) {
                return inputType;
            }
        }
        return null;
    }

    public String getTypeName() {
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
