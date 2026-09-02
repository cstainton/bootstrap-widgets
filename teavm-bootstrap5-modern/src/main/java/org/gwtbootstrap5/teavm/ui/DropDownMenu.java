package org.gwtbootstrap5.teavm.ui;

public class DropDownMenu extends Panel {

    public DropDownMenu() {
        super("ul");
        addStyleName("dropdown-menu");
    }

    public DropDownMenu setEndAligned(final boolean endAligned) {
        setStyleName("dropdown-menu-end", endAligned);
        return this;
    }
}
