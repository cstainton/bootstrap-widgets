package org.gwtbootstrap3.client.ui.html;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;
import org.gwtbootstrap3.client.ui.constants.ElementTags;

public class Italic extends AbstractTextWidget {
    public Italic() {
        super(Document.get().createElement(ElementTags.EM));
    }

    public Italic(final String text) {
        this();
        setHTML(text);
    }
}
