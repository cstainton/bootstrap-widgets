package org.gwtbootstrap5.teavm.ui;

public class Form extends Panel {

    public Form() {
        super("form");
    }

    @Override
    public Form add(final Widget child) {
        super.add(child);
        return this;
    }

    public Form setInline(final boolean inline) {
        setStyleName("row row-cols-lg-auto g-3 align-items-center", inline);
        return this;
    }
}
