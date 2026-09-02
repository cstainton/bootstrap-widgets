package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.ValueBoxBase;
import org.gwtbootstrap5.client.ui.constants.Styles;

import com.google.gwt.dom.client.Document;
import com.google.gwt.text.client.DoubleParser;
import com.google.gwt.text.client.DoubleRenderer;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;

public class DoubleBox extends ValueBoxBase<Double> {

    public DoubleBox() {
        this(DoubleRenderer.instance(), DoubleParser.instance());
    }

    public DoubleBox(Renderer<Double> renderer, Parser<Double> parser) {
        super(Document.get().createTextInputElement(), renderer, parser);
        addStyleName(Styles.FORM_CONTROL);
    }
}
