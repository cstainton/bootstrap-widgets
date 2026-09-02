package org.gwtbootstrap5.client.ui;

public class NavbarNav extends ElementPanel {

    public NavbarNav() {
        super("ul");
        setStyleName("navbar-nav me-auto mb-2 mb-lg-0");
    }

    public void setEndAligned(boolean endAligned) {
        setStyleName(endAligned ? "navbar-nav ms-auto mb-2 mb-lg-0" : "navbar-nav me-auto mb-2 mb-lg-0");
    }
}
