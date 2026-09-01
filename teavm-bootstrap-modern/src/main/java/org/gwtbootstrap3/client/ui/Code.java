package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;
import org.gwtbootstrap3.client.ui.constants.ElementTags;

public class Code extends AbstractTextWidget {
    public Code() {
        super(Document.get().createElement(ElementTags.CODE));
    }

    public Code(final String text) {
        this();
        setText(text);
    }
}
