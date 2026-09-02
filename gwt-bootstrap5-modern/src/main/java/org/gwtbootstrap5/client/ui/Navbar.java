package org.gwtbootstrap5.client.ui;

public class Navbar extends ElementPanel {

    private final Container container = new Container();
    private final NavbarNav nav = new NavbarNav();

    public Navbar() {
        super("nav");
        setStyleName("navbar navbar-expand-lg bg-body-tertiary border-bottom");
        container.addStyleName("py-0");
        container.add(nav);
        add(container);
    }

    public Container getContainer() {
        return container;
    }

    public NavbarNav getNav() {
        return nav;
    }

    public void setDark(boolean dark) {
        setStyleName(dark ? "navbar navbar-expand-lg navbar-dark bg-dark" : "navbar navbar-expand-lg bg-body-tertiary border-bottom");
    }
}
