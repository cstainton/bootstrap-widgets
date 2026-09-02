package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;

/** Text-only widget rendered as a {@code span}. */
public class InlineLabel extends Label {

    public InlineLabel() {
        super(Document.get().createSpanElement());
        setStyleName("gwt-InlineLabel");
    }

    public InlineLabel(final String text) {
        this();
        setText(text);
    }
}
