package org.gwtbootstrap5.client.ui;

public class InputGroupAddon extends ElementPanel {

    public InputGroupAddon() {
        this("");
    }

    public InputGroupAddon(String text) {
        super("span");
        addStyleName("input-group-text");
        setText(text);
    }
}
