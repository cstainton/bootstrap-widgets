package org.gwtbootstrap5.client.ui;

public class NavbarBrand extends Anchor {

    public NavbarBrand() {
        super();
        addStyleName("navbar-brand");
    }

    public NavbarBrand(String text, String href) {
        this();
        setText(text);
        setHref(href);
    }
}
