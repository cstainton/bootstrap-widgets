package org.gwtbootstrap3.client.ui;

import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.html.UnorderedList;

public class ListGroup extends UnorderedList {
    public ListGroup() {
        setStyleName(Styles.LIST_GROUP);
    }

    @Override
    public void add(final Widget child) {
        if (!(child instanceof ListGroupItem)) {
            throw new IllegalArgumentException("Only ListGroupItems can be inside a ListGroup.");
        }
        super.add(child);
    }
}
