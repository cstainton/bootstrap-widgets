package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;

public class Pre extends AbstractTextWidget {
    public Pre() {
        super(Document.get().createPreElement());
    }

    public Pre(final String text) {
        this();
        setText(text);
    }
}
