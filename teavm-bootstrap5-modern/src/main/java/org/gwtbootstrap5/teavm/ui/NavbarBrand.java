package org.gwtbootstrap5.teavm.ui;

public class NavbarBrand extends Anchor {

    public NavbarBrand() {
        super();
        addStyleName("navbar-brand");
    }

    public NavbarBrand(final String text, final String href) {
        this();
        setText(text);
        setHref(href);
    }
}
