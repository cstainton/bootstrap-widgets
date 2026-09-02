package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class ListGroup extends ElementPanel {

    public ListGroup() {
        super("ul");
        addStyleName("list-group");
    }

    @Override
    public void add(Widget child) {
        if (!(child instanceof ListGroupItem)) {
            throw new IllegalArgumentException("Only ListGroupItem widgets can be inside a ListGroup.");
        }
        super.add(child);
    }
}
