package org.gwtbootstrap5.client.ui;

public class DropDownMenu extends ElementPanel {

    public DropDownMenu() {
        super("ul");
        addStyleName("dropdown-menu");
        getElement().setAttribute("role", "menu");
    }

    public void setEndAligned(boolean endAligned) {
        setStyleName("dropdown-menu-end", endAligned);
    }

    public boolean isEndAligned() {
        return getStyleName().contains("dropdown-menu-end");
    }
}
