package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;

/** Image widget rendered as an {@code img} element. */
public class Image extends Widget {

    public Image() {
        setElement(Document.get().createImageElement());
        setStyleName("gwt-Image");
    }

    public Image(final String url) {
        this();
        setUrl(url);
    }

    public String getUrl() {
        return getElement().getAttribute("src");
    }

    public void setUrl(final String url) {
        getElement().setAttribute("src", url);
    }

    public String getAltText() {
        return getElement().getAttribute("alt");
    }

    public void setAltText(final String altText) {
        getElement().setAttribute("alt", altText == null ? "" : altText);
    }
}
