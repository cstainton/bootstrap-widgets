package org.gwtbootstrap5.teavm.ui;

public class ListGroup extends Panel {

    public ListGroup() {
        super("ul");
        addStyleName("list-group");
    }

    @Override
    public ListGroup add(final Widget child) {
        if (!(child instanceof ListGroupItem)) {
            throw new IllegalArgumentException("Only ListGroupItem widgets can be inside a ListGroup.");
        }
        super.add(child);
        return this;
    }
}
