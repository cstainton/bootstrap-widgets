package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

/** Panel that lays its children out in normal document flow. */
public class FlowPanel extends ComplexPanel implements InsertPanel.ForIsWidget {

    public FlowPanel() {
        this(Document.get().createDivElement());
    }

    public FlowPanel(final Element element) {
        setElement(element);
    }

    @Override
    public void insert(final Widget child, final int beforeIndex) {
        insert(child, getElement(), beforeIndex, true);
    }

    @Override
    public void insert(final IsWidget child, final int beforeIndex) {
        insert(child == null ? null : child.asWidget(), beforeIndex);
    }
}
