package org.gwtbootstrap5.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class CheckBoxButton extends Button {

    private boolean value;

    public CheckBoxButton() {
        this("");
    }

    public CheckBoxButton(String text) {
        super(text, Variant.SECONDARY);
        addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                setValue(!getValue());
            }
        });
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
        setStyleName("active", value);
        getElement().setAttribute("aria-pressed", Boolean.toString(value));
    }
}
