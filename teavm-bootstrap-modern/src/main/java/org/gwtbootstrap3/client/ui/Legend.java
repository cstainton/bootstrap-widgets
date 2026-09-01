package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;

public class Legend extends AbstractTextWidget {
    public Legend() {
        super(Document.get().createLegendElement());
    }

    public Legend(final String text) {
        this();
        setText(text);
    }
}
