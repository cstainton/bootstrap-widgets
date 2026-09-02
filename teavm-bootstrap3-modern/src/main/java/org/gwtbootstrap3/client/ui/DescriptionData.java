package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;
import org.gwtbootstrap3.client.ui.base.DescriptionComponent;
import org.gwtbootstrap3.client.ui.constants.ElementTags;

public class DescriptionData extends AbstractTextWidget implements DescriptionComponent {
    public DescriptionData() {
        super(Document.get().createElement(ElementTags.DD));
    }

    public DescriptionData(final String text) {
        this();
        setText(text);
    }
}
