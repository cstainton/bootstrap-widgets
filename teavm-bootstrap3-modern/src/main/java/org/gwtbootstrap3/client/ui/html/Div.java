package org.gwtbootstrap3.client.ui.html;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.FlowPanel;

public class Div extends FlowPanel {
    public Div() {
        setElement(Document.get().createDivElement());
    }
}
