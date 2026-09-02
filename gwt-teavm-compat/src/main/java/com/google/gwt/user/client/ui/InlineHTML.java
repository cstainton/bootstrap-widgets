package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;

/** Widget holding arbitrary HTML, rendered as a {@code span}. */
public class InlineHTML extends HTML {

    public InlineHTML() {
        super(Document.get().createSpanElement());
        setStyleName("gwt-InlineHTML");
    }

    public InlineHTML(final String html) {
        this();
        setHTML(html);
    }
}
