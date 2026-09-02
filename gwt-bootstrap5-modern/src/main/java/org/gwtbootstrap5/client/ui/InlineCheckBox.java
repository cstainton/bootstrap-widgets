package org.gwtbootstrap5.client.ui;

public class InlineCheckBox extends CheckBox {

    public InlineCheckBox() {
        this("");
    }

    public InlineCheckBox(String text) {
        super(text);
        addStyleName("form-check-inline");
    }
}
