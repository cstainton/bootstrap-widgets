package org.gwtbootstrap5.teavm.ui;

public class ButtonGroup extends Panel {

    public ButtonGroup() {
        super("div");
        addStyleName("btn-group");
        setAttribute("role", "group");
    }

    @Override
    public ButtonGroup add(final Widget child) {
        super.add(child);
        return this;
    }

    public ButtonGroup setVertical(final boolean vertical) {
        setStyleName("btn-group-vertical", vertical);
        setStyleName("btn-group", !vertical);
        return this;
    }
}
