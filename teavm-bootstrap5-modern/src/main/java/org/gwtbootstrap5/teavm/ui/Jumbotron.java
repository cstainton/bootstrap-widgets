package org.gwtbootstrap5.teavm.ui;

public class Jumbotron extends Panel {

    public Jumbotron() {
        super("div");
        setStyleName("p-5 mb-4 bg-body-tertiary rounded-3");
    }

    @Override
    public Jumbotron add(final Widget child) {
        super.add(child);
        return this;
    }
}
