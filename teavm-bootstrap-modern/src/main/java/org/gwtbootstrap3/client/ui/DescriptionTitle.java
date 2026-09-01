package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;
import org.gwtbootstrap3.client.ui.base.DescriptionComponent;
import org.gwtbootstrap3.client.ui.constants.ElementTags;

public class DescriptionTitle extends AbstractTextWidget implements DescriptionComponent {
    public DescriptionTitle() {
        super(Document.get().createElement(ElementTags.DT));
    }

    public DescriptionTitle(final String text) {
        this();
        setText(text);
    }
}
