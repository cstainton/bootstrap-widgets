package org.gwtbootstrap5.client.ui;

public class FormControlStatic extends ElementPanel {

    public FormControlStatic() {
        this("");
    }

    public FormControlStatic(String text) {
        super("p");
        addStyleName("form-control-plaintext");
        setText(text);
    }
}
