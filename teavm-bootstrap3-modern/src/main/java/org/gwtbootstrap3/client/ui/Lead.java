package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;
import org.gwtbootstrap3.client.ui.constants.Styles;

public class Lead extends AbstractTextWidget {
    public Lead() {
        super(Document.get().createElement("p"));
        setStyleName(Styles.LEAD);
    }

    public Lead(final String text) {
        this();
        setText(text);
    }
}
