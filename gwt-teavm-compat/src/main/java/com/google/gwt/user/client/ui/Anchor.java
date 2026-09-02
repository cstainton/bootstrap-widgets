package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;

/** Hyperlink widget rendered as an {@code a} element. */
public class Anchor extends FocusWidget implements HasHTML {

    public Anchor() {
        super(Document.get().createAnchorElement());
    }

    public Anchor(final String text) {
        this();
        setText(text);
    }

    public Anchor(final String text, final String href) {
        this(text);
        setHref(href);
    }

    public String getHref() {
        return getElement().getAttribute("href");
    }

    public void setHref(final String href) {
        if (href == null) {
            getElement().removeAttribute("href");
        } else {
            getElement().setAttribute("href", href);
        }
    }

    public String getTarget() {
        return getElement().getAttribute("target");
    }

    public void setTarget(final String target) {
        getElement().setAttribute("target", target);
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
