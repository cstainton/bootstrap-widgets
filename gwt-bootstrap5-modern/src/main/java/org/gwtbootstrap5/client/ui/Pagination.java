package org.gwtbootstrap5.client.ui;

public class Pagination extends ElementPanel {

    public Pagination() {
        super("ul");
        addStyleName("pagination");
    }

    public void setLarge(boolean large) {
        setStyleName("pagination-lg", large);
    }

    public void setSmall(boolean small) {
        setStyleName("pagination-sm", small);
    }
}
