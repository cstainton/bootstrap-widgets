package org.gwtbootstrap3.client.ui.html;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;
import org.gwtbootstrap3.client.ui.constants.ElementTags;

public class Small extends AbstractTextWidget {
    public Small() {
        super(Document.get().createElement(ElementTags.SMALL));
    }

    public Small(final String text) {
        this();
        setHTML(text);
    }
}
