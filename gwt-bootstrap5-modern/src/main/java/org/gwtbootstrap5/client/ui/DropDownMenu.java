package org.gwtbootstrap5.client.ui;

public class DropDownMenu extends ElementPanel {

    public DropDownMenu() {
        super("ul");
        addStyleName("dropdown-menu");
    }

    public void setEndAligned(boolean endAligned) {
        setStyleName("dropdown-menu-end", endAligned);
    }
}
