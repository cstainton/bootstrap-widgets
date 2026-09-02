package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.TextAreaElement;

public class TextArea extends TextBoxBase {

    public TextArea() {
        super(Document.get().createTextAreaElement());
        setStyleName("gwt-TextArea");
    }

    protected TextAreaElement getTextAreaElement() {
        return TextAreaElement.as(getElement());
    }

    public int getVisibleLines() {
        return getTextAreaElement().getRows();
    }

    public void setVisibleLines(final int lines) {
        getTextAreaElement().setRows(lines);
    }

    public int getCharacterWidth() {
        return getTextAreaElement().getCols();
    }

    public void setCharacterWidth(final int width) {
        getTextAreaElement().setCols(width);
    }
}
