package org.gwtbootstrap5.teavm.ui;

import org.gwtbootstrap5.teavm.dom.TeaVmDomElement;
import org.teavm.jso.dom.html.HTMLDocument;

public final class RootPanel extends Panel {

    private RootPanel(final TeaVmDomElement element) {
        super(element);
    }

    public static RootPanel get() {
        return new RootPanel(new TeaVmDomElement(HTMLDocument.current().getBody()));
    }

    public static RootPanel get(final String id) {
        final TeaVmDomElement element = TeaVmDomElement.byId(id);
        if (element == null) {
            throw new IllegalArgumentException("No element found for id " + id);
        }
        return new RootPanel(element);
    }
}
