package org.gwtbootstrap5.teavm.ui;

public class Pager extends Pagination {

    private final PageItem previous = new PageItem("Previous", "#");
    private final PageItem next = new PageItem("Next", "#");

    public Pager() {
        add(previous);
        add(next);
    }

    public Pager setAlignToSides(final boolean alignToSides) {
        setStyleName("justify-content-between", alignToSides);
        return this;
    }

    public Pager setPreviousText(final String text) {
        previous.setText(text);
        return this;
    }

    public Pager setPreviousEnabled(final boolean enabled) {
        previous.setDisabled(!enabled);
        return this;
    }

    public Pager setNextText(final String text) {
        next.setText(text);
        return this;
    }

    public Pager setNextEnabled(final boolean enabled) {
        next.setDisabled(!enabled);
        return this;
    }
}
