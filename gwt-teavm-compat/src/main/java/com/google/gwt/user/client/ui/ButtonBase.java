package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Element;

/** Base class for button-like widgets. */
public abstract class ButtonBase extends FocusWidget implements HasHTML {

    protected ButtonBase(final Element element) {
        super(element);
    }

    @Override
    public String getText() {
        return getElement().getInnerText();
    }

    @Override
    public void setText(final String text) {
        getElement().setInnerText(text == null ? "" : text);
    }

    @Override
    public String getHTML() {
        return getElement().getInnerHTML();
    }

    @Override
    public void setHTML(final String html) {
        getElement().setInnerHTML(html == null ? "" : html);
    }
}
