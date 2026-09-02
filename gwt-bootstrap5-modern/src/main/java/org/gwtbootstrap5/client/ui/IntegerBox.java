package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.ValueBoxBase;
import org.gwtbootstrap5.client.ui.constants.Styles;

import com.google.gwt.dom.client.Document;
import com.google.gwt.text.client.IntegerParser;
import com.google.gwt.text.client.IntegerRenderer;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;

public class IntegerBox extends ValueBoxBase<Integer> {

    public IntegerBox() {
        this(IntegerRenderer.instance(), IntegerParser.instance());
    }

    public IntegerBox(Renderer<Integer> renderer, Parser<Integer> parser) {
        super(Document.get().createTextInputElement(), renderer, parser);
        addStyleName(Styles.FORM_CONTROL);
    }
}
