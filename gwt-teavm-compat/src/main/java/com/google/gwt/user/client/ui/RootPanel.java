package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

public final class RootPanel extends ComplexPanel {
    private static RootPanel bodyRoot;

    private RootPanel(final Element element) {
        setElement(element);
    }

    public static RootPanel get() {
        if (bodyRoot == null) {
            bodyRoot = new RootPanel(Document.get().getBody());
        }
        return bodyRoot;
    }

    public static RootPanel get(final String id) {
        final Element element = Document.get().getElementById(id);
        return element == null ? null : new RootPanel(element);
    }
}
