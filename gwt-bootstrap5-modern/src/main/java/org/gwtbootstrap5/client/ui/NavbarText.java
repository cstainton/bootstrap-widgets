package org.gwtbootstrap5.client.ui;

public class NavbarText extends ElementPanel {

    public NavbarText() {
        super("span");
        addStyleName("navbar-text");
    }

    public NavbarText(String text) {
        this();
        setText(text);
    }
}
