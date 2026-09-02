package org.gwtbootstrap3.teavm.ui;

import org.gwtbootstrap3.teavm.dom.TeaVmDomElement;
import org.teavm.jso.dom.html.HTMLDocument;

public final class Mount {

    private Mount() {
    }

    public static void toBody(final Widget widget) {
        new TeaVmDomElement(HTMLDocument.current().getBody()).appendChild(widget.getElement());
    }

    public static void toElement(final String id, final Widget widget) {
        final TeaVmDomElement element = TeaVmDomElement.byId(id);
        if (element == null) {
            throw new IllegalArgumentException("No element found for id " + id);
        }
        element.appendChild(widget.getElement());
    }
}
