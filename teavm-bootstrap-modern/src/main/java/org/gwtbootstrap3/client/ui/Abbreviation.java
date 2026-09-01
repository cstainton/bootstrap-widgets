package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.uibinder.client.UiConstructor;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;
import org.gwtbootstrap3.client.ui.constants.ElementTags;

public class Abbreviation extends AbstractTextWidget {
    @UiConstructor
    public Abbreviation(final String title) {
        super(Document.get().createElement(ElementTags.ABBR));
        setTitle(title);
    }
}
