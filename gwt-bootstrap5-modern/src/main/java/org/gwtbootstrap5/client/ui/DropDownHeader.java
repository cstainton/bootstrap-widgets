package org.gwtbootstrap5.client.ui;

public class DropDownHeader extends ElementPanel {

    public DropDownHeader() {
        this("");
    }

    public DropDownHeader(String text) {
        super("h6");
        addStyleName("dropdown-header");
        setText(text);
    }
}
