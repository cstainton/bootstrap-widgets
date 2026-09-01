package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.base.DescriptionComponent;
import org.gwtbootstrap3.client.ui.constants.Styles;

public class Description extends FlowPanel {
    public Description() {
        setElement(Document.get().createDLElement());
    }

    public void setHorizontal(final boolean horizontal) {
        setStyleName(Styles.DL_HORIZONTAL, horizontal);
    }

    @Override
    public void add(final Widget child) {
        if (!(child instanceof DescriptionComponent)) {
            throw new IllegalArgumentException("Description can only contain DescriptionData and DescriptionTitle");
        }
        super.add(child);
    }
}
