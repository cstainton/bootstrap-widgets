package org.gwtbootstrap5.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import org.gwtbootstrap5.client.ui.constants.ButtonType;

public class CheckBoxButton extends Button implements HasValue<Boolean>, HasValueChangeHandlers<Boolean> {

    private boolean value;

    public CheckBoxButton() {
        this("");
    }

    public CheckBoxButton(String text) {
        super(text, ButtonType.DEFAULT);
        addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                setValue(!getValue(), true);
            }
        });
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        setValue(value, false);
    }

    @Override
    public void setValue(Boolean value, boolean fireEvents) {
        boolean effectiveValue = value != null && value;
        boolean oldValue = this.value;
        this.value = effectiveValue;
        setActive(effectiveValue);
        if (fireEvents && oldValue != effectiveValue) {
            ValueChangeEvent.fire(this, effectiveValue);
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }
}
