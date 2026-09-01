package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;

public class Label extends Widget implements HasHTML {
    public Label() {
        setElement(Document.get().createSpanElement());
        setStyleName("badge text-bg-secondary");
    }

    public Label(final String text) {
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
