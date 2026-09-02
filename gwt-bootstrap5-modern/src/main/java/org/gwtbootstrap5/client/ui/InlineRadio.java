package org.gwtbootstrap5.client.ui;

public class InlineRadio extends Radio {

    public InlineRadio() {
        this("");
    }

    public InlineRadio(String text) {
        super(text);
        addStyleName("form-check-inline");
    }
}
