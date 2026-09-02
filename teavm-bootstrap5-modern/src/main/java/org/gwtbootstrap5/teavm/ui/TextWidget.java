package org.gwtbootstrap5.teavm.ui;

public class TextWidget extends Widget {

    protected TextWidget(final String tagName) {
        super(tagName);
    }

    public TextWidget setText(final String text) {
        getElement().setInnerText(text);
        return this;
    }

    public TextWidget setHtml(final String html) {
        getElement().setInnerHTML(html);
        return this;
    }
}
