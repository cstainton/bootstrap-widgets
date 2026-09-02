package org.gwtbootstrap5.teavm.ui;

public class FormGroup extends Panel {

    public FormGroup() {
        super("div");
        addStyleName("mb-3");
    }

    @Override
    public FormGroup add(final Widget child) {
        super.add(child);
        return this;
    }
}
