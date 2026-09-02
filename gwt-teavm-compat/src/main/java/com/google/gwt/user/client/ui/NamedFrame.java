package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;

/** A named {@code iframe}, used as a form submit target. */
public class NamedFrame extends Widget {

    public NamedFrame(final String name) {
        setElement(Document.get().createElement("iframe"));
        getElement().setPropertyString("name", name);
        setStyleName("gwt-Frame");
    }

    public String getName() {
        return getElement().getPropertyString("name");
    }

    public String getUrl() {
        return getElement().getAttribute("src");
    }

    public void setUrl(final String url) {
        getElement().setAttribute("src", url);
    }
}
