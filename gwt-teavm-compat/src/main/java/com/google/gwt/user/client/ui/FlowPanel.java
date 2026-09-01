package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;

public class FlowPanel extends ComplexPanel {
    public FlowPanel() {
        setElement(Document.get().createDivElement());
    }
}
