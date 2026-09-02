package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.TextBoxBase;
import org.gwtbootstrap5.client.ui.constants.Styles;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.text.shared.testing.PassthroughParser;
import com.google.gwt.text.shared.testing.PassthroughRenderer;

public class TextBox extends TextBoxBase {

    public TextBox() {
        this(Document.get().createTextInputElement());
    }

    public TextBox(String text) {
        this();
        setText(text);
    }

    public TextBox(final Element element) {
        this(element, PassthroughRenderer.instance(), PassthroughParser.instance());
    }

    public TextBox(Element element, Renderer<String> renderer, Parser<String> parser) {
        super(element, renderer, parser);
        setStyleName(Styles.FORM_CONTROL);
    }

    public void clear() {
        super.setValue(null);
    }
}
