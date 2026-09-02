package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.constants.ButtonType;

public class SubmitButton extends Button {

    public SubmitButton() {
        this("");
    }

    public SubmitButton(String text) {
        super(text, ButtonType.PRIMARY);
        getElement().setAttribute("type", "submit");
    }
}
