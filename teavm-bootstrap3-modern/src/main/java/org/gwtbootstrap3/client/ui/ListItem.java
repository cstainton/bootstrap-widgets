package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;

public class ListItem extends AbstractTextWidget {
    public ListItem() {
        super(Document.get().createLIElement());
    }

    public ListItem(final String text) {
        this();
        setText(text);
    }
}
