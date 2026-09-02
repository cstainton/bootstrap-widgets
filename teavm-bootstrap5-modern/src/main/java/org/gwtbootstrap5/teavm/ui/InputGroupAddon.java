package org.gwtbootstrap5.teavm.ui;

public class InputGroupAddon extends TextWidget {

    public InputGroupAddon() {
        this("");
    }

    public InputGroupAddon(final String text) {
        super("span");
        addStyleName("input-group-text");
        setText(text);
    }
}
