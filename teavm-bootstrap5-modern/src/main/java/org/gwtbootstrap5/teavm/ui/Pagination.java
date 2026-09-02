package org.gwtbootstrap5.teavm.ui;

public class Pagination extends Panel {

    public Pagination() {
        super("ul");
        addStyleName("pagination");
    }

    @Override
    public Pagination add(final Widget child) {
        super.add(child);
        return this;
    }

    public Pagination setLarge(final boolean large) {
        setStyleName("pagination-lg", large);
        return this;
    }

    public Pagination setSmall(final boolean small) {
        setStyleName("pagination-sm", small);
        return this;
    }
}
