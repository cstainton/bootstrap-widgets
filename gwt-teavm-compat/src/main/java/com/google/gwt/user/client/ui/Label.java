package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

/** Text-only widget rendered as a {@code div}, matching GWT's {@code Label}. */
public class Label extends Widget implements HasText {

    public Label() {
        this(Document.get().createDivElement());
        setStyleName("gwt-Label");
    }

    public Label(final String text) {
        this();
        setText(text);
    }

    protected Label(final Element element) {
        setElement(element);
    }

    @Override
    public String getText() {
        return getElement().getInnerText();
    }

    @Override
    public void setText(final String text) {
        getElement().setInnerText(text);
    }
}
