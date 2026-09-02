package org.gwtbootstrap5.teavm.ui;

public class NavbarNav extends Panel {

    public NavbarNav() {
        super("ul");
        setStyleName("navbar-nav me-auto mb-2 mb-lg-0");
    }

    public NavbarNav setEndAligned(final boolean endAligned) {
        setStyleName(endAligned ? "navbar-nav ms-auto mb-2 mb-lg-0" : "navbar-nav me-auto mb-2 mb-lg-0");
        return this;
    }
}
