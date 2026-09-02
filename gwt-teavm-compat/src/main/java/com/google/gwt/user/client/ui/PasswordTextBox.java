package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;

public class PasswordTextBox extends TextBoxBase {

    public PasswordTextBox() {
        super(Document.get().createInputElement("password"));
        setStyleName("gwt-PasswordTextBox");
    }

    public int getMaxLength() {
        return getElement().getPropertyInt("maxLength");
    }

    public void setMaxLength(final int length) {
        getElement().setPropertyInt("maxLength", length);
    }
}
