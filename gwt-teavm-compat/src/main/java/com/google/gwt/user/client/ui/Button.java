package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;

/** Push button rendered as a {@code button} element. */
public class Button extends FocusWidget implements HasHTML {

    public Button() {
        super(Document.get().createPushButtonElement());
        getElement().setAttribute("type", "button");
        setStyleName("gwt-Button");
    }

    public Button(final String text) {
        this();
        setText(text);
    }

    @Override
    public String getText() {
        return getElement().getInnerText();
    }

    @Override
    public void setText(final String text) {
        getElement().setInnerText(text);
    }

    @Override
    public String getHTML() {
        return getElement().getInnerHTML();
    }

    @Override
    public void setHTML(final String html) {
        getElement().setInnerHTML(html);
    }
}
