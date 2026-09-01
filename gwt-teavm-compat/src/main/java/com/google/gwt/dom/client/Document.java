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

    public Element createBRElement() {
        return createElement("br");
    }

    public Element createDLElement() {
        return createElement("dl");
    }

    public Element createFieldSetElement() {
        return createElement("fieldset");
    }

    public Element createImageElement() {
        return createElement("img");
    }

    public Element createLabelElement() {
        return createElement("label");
    }

    public Element createLegendElement() {
        return createElement("legend");
    }

    public Element createLIElement() {
        return createElement("li");
    }

    public Element createOLElement() {
        return createElement("ol");
    }

    public Element createPreElement() {
        return createElement("pre");
    }

    public Element createULElement() {
        return createElement("ul");
    }

    public Element getBody() {
        return new Element(HTMLDocument.current().getBody());
    }

    public Element getElementById(final String id) {
        final HTMLElement found = HTMLDocument.current().getElementById(id);
        return found == null ? null : new Element(found);
    }
}
