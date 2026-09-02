package org.gwtbootstrap5.teavm.ui;

public class InputGroup extends Panel {

    public InputGroup() {
        super("div");
        addStyleName("input-group");
    }

    @Override
    public InputGroup add(final Widget child) {
        super.add(child);
        return this;
    }

    public InputGroup setLarge(final boolean large) {
        setStyleName("input-group-lg", large);
        return this;
    }

    public InputGroup setSmall(final boolean small) {
        setStyleName("input-group-sm", small);
        return this;
    }
}
