package org.gwtbootstrap5.teavm.ui;

import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.xml.Node;

public final class Mount {

    private Mount() {
    }

    public static void toBody(final Widget widget) {
        HTMLDocument.current().getBody().appendChild((Node) widget.unwrap());
    }

    public static void toElement(final String id, final Widget widget) {
        final HTMLElement element = HTMLDocument.current().getElementById(id);
        if (element == null) {
            throw new IllegalArgumentException("No element found for id " + id);
        }
        element.appendChild((Node) widget.unwrap());
    }
}
