package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

/**
 * Panel whose markup is supplied as HTML; widgets are attached into elements
 * addressed by id within that markup.
 */
public class HTMLPanel extends ComplexPanel {

    public HTMLPanel(final String html) {
        this("div", html);
    }

    public HTMLPanel(final com.google.gwt.safehtml.shared.SafeHtml safeHtml) {
        this("div", safeHtml == null ? "" : safeHtml.asString());
    }

    public HTMLPanel(final String tag, final String html) {
        setElement(Document.get().createElement(tag == null ? "div" : tag));
        getElement().setInnerHTML(html == null ? "" : html);
    }

    public static String createUniqueId() {
        return Document.get().createUniqueId();
    }

    /** Attaches a widget inside the element with the given id. */
    public void add(final Widget widget, final String id) {
        final Element container = getElementById(id);
        if (container == null) {
            throw new IllegalArgumentException("No element with id " + id + " in this HTMLPanel");
        }
        add(widget, container);
    }

    public void addAndReplaceElement(final Widget widget, final Element toReplace) {
        final Element parent = toReplace.getParentElement();
        if (parent == null) {
            throw new IllegalArgumentException("Element to replace has no parent");
        }
        parent.insertBefore(widget.getElement(), toReplace);
        toReplace.removeFromParent();
        getChildren().add(widget);
        adopt(widget);
    }

    private Element getElementById(final String id) {
        return findById(getElement(), id);
    }

    private static Element findById(final Element root, final String id) {
        final org.teavm.jso.dom.html.HTMLElement found = query(root.unwrap(), id);
        return found == null ? null : new Element(found);
    }

    @org.teavm.jso.JSBody(params = {"el", "id"},
            script = "return el.querySelector('#' + id);")
    private static native org.teavm.jso.dom.html.HTMLElement query(
            org.teavm.jso.dom.html.HTMLElement el, String id);
}
