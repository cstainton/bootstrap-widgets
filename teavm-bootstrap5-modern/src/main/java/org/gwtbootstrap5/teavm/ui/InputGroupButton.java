package org.gwtbootstrap5.teavm.ui;

public class InputGroupButton extends Panel {

    public InputGroupButton() {
        super("span");
        setStyleName("input-group-text p-0 border-0 bg-transparent");
    }

    @Override
    public InputGroupButton add(final Widget child) {
        super.add(child);
        return this;
    }
}
