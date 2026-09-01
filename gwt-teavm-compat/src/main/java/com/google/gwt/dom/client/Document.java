package com.google.gwt.dom.client;

import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;

public final class Document {
    private static final Document INSTANCE = new Document();

    private Document() {
    }

    public static Document get() {
        return INSTANCE;
    }

    public Element createElement(final String tagName) {
        return new Element(HTMLDocument.current().createElement(tagName));
    }

    public Element createDivElement() {
        return createElement("div");
    }

    public Element createSpanElement() {
        return createElement("span");
    }

    public Element createHElement(final int size) {
        return createElement("h" + size);
    }

    public Element createPushButtonElement() {
        return createElement("button");
    }

    public Element createAnchorElement() {
        return createElement("a");
    }

    public Element getBody() {
        return new Element(HTMLDocument.current().getBody());
    }

    public Element getElementById(final String id) {
        final HTMLElement found = HTMLDocument.current().getElementById(id);
        return found == null ? null : new Element(found);
    }
}
