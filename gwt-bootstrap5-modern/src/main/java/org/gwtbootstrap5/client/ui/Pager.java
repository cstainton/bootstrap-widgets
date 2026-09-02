package org.gwtbootstrap5.client.ui;

public class Pager extends Pagination {

    private final PageItem previous = new PageItem("Previous", "#");
    private final PageItem next = new PageItem("Next", "#");

    public Pager() {
        add(previous);
        add(next);
    }

    public void setAlignToSides(boolean alignToSides) {
        setStyleName("justify-content-between", alignToSides);
    }

    public void setPreviousText(String text) {
        previous.setText(text);
    }

    public void setPreviousEnabled(boolean enabled) {
        previous.setDisabled(!enabled);
    }

    public void setNextText(String text) {
        next.setText(text);
    }

    public void setNextEnabled(boolean enabled) {
        next.setDisabled(!enabled);
    }
}
