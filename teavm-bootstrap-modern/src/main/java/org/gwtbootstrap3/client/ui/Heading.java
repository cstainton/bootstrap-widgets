package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.constants.HeadingSize;

public class Heading extends Widget implements HasHTML {
    @UiConstructor
    public Heading(final HeadingSize size) {
        setElement(Document.get().createHElement(size.getHeadingSize()));
    }

    public Heading(final HeadingSize size, final String text) {
        this(size);
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
