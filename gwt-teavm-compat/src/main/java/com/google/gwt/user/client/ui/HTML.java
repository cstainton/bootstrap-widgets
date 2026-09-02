package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

/** Widget holding arbitrary HTML, rendered as a {@code div}. */
public class HTML extends Label implements HasHTML {

    public HTML() {
        super(Document.get().createDivElement());
        setStyleName("gwt-HTML");
    }

    public HTML(final String html) {
        this();
        setHTML(html);
    }

    protected HTML(final Element element) {
        super(element);
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
