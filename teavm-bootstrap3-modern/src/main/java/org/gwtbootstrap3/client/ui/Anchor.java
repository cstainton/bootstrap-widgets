package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;

public class Anchor extends Widget implements HasHTML {
    public Anchor() {
        setElement(Document.get().createAnchorElement());
    }

    public Anchor(final String text, final String href) {
        this();
        setText(text);
        setHref(href);
    }

    public void setHref(final String href) {
        getElement().setAttribute("href", href);
    }

    public String getHref() {
        return getElement().getAttribute("href");
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
