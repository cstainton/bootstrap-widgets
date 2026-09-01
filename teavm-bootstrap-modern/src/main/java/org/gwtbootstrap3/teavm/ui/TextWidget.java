package org.gwtbootstrap3.teavm.ui;

public class TextWidget extends Widget {

    protected TextWidget(final String tagName) {
        super(tagName);
    }

    public TextWidget setText(final String text) {
        getElement().setText(text);
        return this;
    }

    public TextWidget setHtml(final String html) {
        getElement().setHtml(html);
        return this;
    }
}
