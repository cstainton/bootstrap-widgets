package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.HasInputType;
import org.gwtbootstrap5.client.ui.base.ValueBoxBase;
import org.gwtbootstrap5.client.ui.constants.ElementTags;
import org.gwtbootstrap5.client.ui.constants.InputType;
import org.gwtbootstrap5.client.ui.constants.Styles;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.text.shared.testing.PassthroughParser;
import com.google.gwt.text.shared.testing.PassthroughRenderer;
import com.google.gwt.uibinder.client.UiConstructor;

public class Input extends ValueBoxBase<String> implements HasInputType {

    private static final String MIN = "min";
    private static final String MAX = "max";
    private static final String NAME = "name";

    public Input() {
        this(PassthroughRenderer.instance(), PassthroughParser.instance());
    }

    public Input(String type) {
        this();
        setType(type);
    }

    public Input(Renderer<String> renderer, Parser<String> parser) {
        super(Document.get().createElement(ElementTags.INPUT), renderer, parser);
        addStyleName(Styles.FORM_CONTROL);
    }

    @UiConstructor
    public Input(final InputType type) {
        this();
        setType(type);
    }

    public void setMin(final String min) {
        getElement().setAttribute(MIN, min == null ? "" : min);
    }

    public void setMax(final String max) {
        getElement().setAttribute(MAX, max == null ? "" : max);
    }

    public void setType(String type) {
        getElement().setAttribute(TYPE, type == null ? InputType.TEXT.getType() : type);
    }

    @Override
    public void setType(final InputType inputType) {
        setType(inputType == null ? InputType.TEXT.getType() : inputType.getType());
    }

    @Override
    public InputType getType() {
        String type = getElement().getAttribute(TYPE);
        for (InputType inputType : InputType.values()) {
            if (inputType.getType().equals(type)) {
                return inputType;
            }
        }
        return null;
    }

    public String getTypeName() {
        return getElement().getAttribute(TYPE);
    }

    public void setName(String name) {
        getElement().setAttribute(NAME, name == null ? "" : name);
    }

    public String getName() {
        return getElement().getAttribute(NAME);
    }

    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        return addDomHandler(handler, ChangeEvent.getType());
    }
}
