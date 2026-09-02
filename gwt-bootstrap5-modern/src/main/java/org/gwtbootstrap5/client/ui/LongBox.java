package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.ValueBoxBase;
import org.gwtbootstrap5.client.ui.constants.Styles;

import com.google.gwt.dom.client.Document;
import com.google.gwt.text.client.LongParser;
import com.google.gwt.text.client.LongRenderer;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;

public class LongBox extends ValueBoxBase<Long> {

    public LongBox() {
        this(LongRenderer.instance(), LongParser.instance());
    }

    public LongBox(Renderer<Long> renderer, Parser<Long> parser) {
        super(Document.get().createTextInputElement(), renderer, parser);
        addStyleName(Styles.FORM_CONTROL);
    }
}
