/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2018 GwtBootstrap3
 * %%
 * Modified from the GwtBootstrap3 original for the Bootstrap 5 track of
 * GWT Bootstrap Modern: moved to the org.gwtbootstrap5 namespace and re-targeted
 * at Bootstrap 5 markup, class names and JavaScript APIs.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.Document;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import org.gwtbootstrap5.client.ui.base.HasFormValue;

public class CheckBox extends ElementPanel implements HasValue<Boolean>, HasValueChangeHandlers<Boolean>, HasFormValue {

    private final Input input = new Input("checkbox");
    private final FormLabel label = new FormLabel();

    public CheckBox() {
        this("");
    }

    public CheckBox(String text) {
        super("div");
        addStyleName("form-check");
        input.removeStyleName("form-control");
        input.addStyleName("form-check-input");
        label.removeStyleName("form-label");
        label.addStyleName("form-check-label");
        label.setText(text);
        String inputId = Document.get().createUniqueId();
        input.getElement().setId(inputId);
        label.setFor(inputId);
        add(input);
        add(label);
        input.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ValueChangeEvent.fire(CheckBox.this, getValue());
            }
        });
    }

    @Override
    public Boolean getValue() {
        return input.getElement().getPropertyBoolean("checked");
    }

    @Override
    public void setValue(Boolean value) {
        setValue(value, false);
    }

    @Override
    public void setValue(Boolean value, boolean fireEvents) {
        Boolean effectiveValue = value != null && value;
        Boolean oldValue = getValue();
        input.getElement().setPropertyBoolean("checked", effectiveValue);
        if (fireEvents && !oldValue.equals(effectiveValue)) {
            ValueChangeEvent.fire(this, effectiveValue);
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    public void setName(String name) {
        input.getElement().setAttribute("name", name == null ? "" : name);
    }

    public String getName() {
        return input.getElement().getAttribute("name");
    }

    @Override
    public String getFormValue() {
        return input.getValue();
    }

    @Override
    public void setFormValue(String value) {
        input.setValue(value);
    }

    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        return input.addChangeHandler(handler);
    }

    public void setEnabled(boolean enabled) {
        input.getElement().setPropertyBoolean("disabled", !enabled);
        setStyleName("disabled", !enabled);
    }

    public boolean isEnabled() {
        return !input.getElement().getPropertyBoolean("disabled");
    }

    protected Input getInput() {
        return input;
    }

    protected FormLabel getLabel() {
        return label;
    }
}
