package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.constants.ButtonType;

public class NavbarButton extends Button {

    public NavbarButton() {
        this("");
    }

    public NavbarButton(String text) {
        super(text, ButtonType.DEFAULT);
        addStyleName("navbar-btn");
    }
}
