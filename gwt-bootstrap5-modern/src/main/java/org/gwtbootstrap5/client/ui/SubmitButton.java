package org.gwtbootstrap5.client.ui;

public class SubmitButton extends Button {

    public SubmitButton() {
        this("");
    }

    public SubmitButton(String text) {
        super(text, Variant.PRIMARY);
        getElement().setAttribute("type", "submit");
    }
}
