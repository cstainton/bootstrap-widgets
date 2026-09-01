package org.gwtbootstrap3.client.ui.html;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;

public class Paragraph extends Widget implements HasHTML {
    public Paragraph() {
        setElement(Document.get().createElement("p"));
    }

    public Paragraph(final String text) {
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
