package org.gwtbootstrap5.teavm.ui;

public class Navbar extends Panel {

    private final Container container = new Container();
    private final NavbarNav nav = new NavbarNav();

    public Navbar() {
        super("nav");
        setStyleName("navbar navbar-expand-lg bg-body-tertiary border-bottom");
        container.add(nav);
        add(container);
    }

    public Container getContainer() {
        return container;
    }

    public NavbarNav getNav() {
        return nav;
    }
}
