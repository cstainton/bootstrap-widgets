package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;

public class TextBox extends TextBoxBase {

    public TextBox() {
        super(Document.get().createInputElement("text"));
        setStyleName("gwt-TextBox");
    }

    public int getMaxLength() {
        return getElement().getPropertyInt("maxLength");
    }

    public void setMaxLength(final int length) {
        getElement().setPropertyInt("maxLength", length);
    }
}
