package org.gwtbootstrap3.client.ui.html;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;
import org.gwtbootstrap3.client.ui.constants.ElementTags;

public class Strong extends AbstractTextWidget {
    public Strong() {
        super(Document.get().createElement(ElementTags.STRONG));
    }

    public Strong(final String text) {
        this();
        setHTML(text);
    }
}
