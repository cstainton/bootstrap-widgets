package org.gwtbootstrap5.teavm.ui;

public class Progress extends Panel {

    public Progress() {
        super("div");
        addStyleName("progress");
    }

    @Override
    public Progress add(final Widget child) {
        super.add(child);
        return this;
    }
}
