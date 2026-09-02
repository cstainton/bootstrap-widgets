package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

public class Image extends Widget {
    public Image() {
        setElement(Document.get().createImageElement());
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

    public void setAltText(final String altText) {
        getElement().setAttribute("alt", altText);
    }
}
