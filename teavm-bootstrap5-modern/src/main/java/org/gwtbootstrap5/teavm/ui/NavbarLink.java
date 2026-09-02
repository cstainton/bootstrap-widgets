package org.gwtbootstrap5.teavm.ui;

public class NavbarLink extends Panel {

    private final Anchor anchor = new Anchor();

    public NavbarLink() {
        super("li");
        addStyleName("nav-item");
        anchor.addStyleName("nav-link");
        add(anchor);
    }

    public NavbarLink(final String text, final String href) {
        this();
        anchor.setText(text);
        anchor.setHref(href == null ? "#" : href);
    }

    public NavbarLink setActive(final boolean active) {
        anchor.setStyleName("active", active);
        anchor.setAttribute("aria-current", active ? "page" : "false");
        return this;
    }
}
