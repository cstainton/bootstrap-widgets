package org.gwtbootstrap5.client.ui;

public class NavbarButton extends Button {

    public NavbarButton() {
        this("");
    }

    public NavbarButton(String text) {
        super(text, Variant.SECONDARY);
        addStyleName("navbar-btn");
    }
}
