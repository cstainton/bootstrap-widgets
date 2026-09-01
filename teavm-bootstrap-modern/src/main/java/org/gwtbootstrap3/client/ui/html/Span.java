package org.gwtbootstrap3.client.ui.html;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;

public class Span extends AbstractTextWidget {
    public Span() {
        super(Document.get().createSpanElement());
    }

    public Span(final String html) {
        this();
        setHTML(html);
    }
}
