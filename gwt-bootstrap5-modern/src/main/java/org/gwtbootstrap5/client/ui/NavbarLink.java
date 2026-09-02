package org.gwtbootstrap5.client.ui;

public class NavbarLink extends Anchor {

    public NavbarLink() {
        super();
        setStyleName("nav-link navbar-link");
    }

    public NavbarLink(String text, String href) {
        this();
        setText(text);
        setHref(href);
    }

    public void setActive(boolean active) {
        setStyleName("active", active);
        if (active) {
            getElement().setAttribute("aria-current", "page");
        } else {
            getElement().removeAttribute("aria-current");
        }
    }

    public void setDisabled(boolean disabled) {
        setEnabled(!disabled);
    }
}
