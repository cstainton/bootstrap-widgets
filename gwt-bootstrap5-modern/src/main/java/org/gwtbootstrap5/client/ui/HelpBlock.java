package org.gwtbootstrap5.client.ui;

public class HelpBlock extends ElementPanel {

    public HelpBlock() {
        this("");
    }

    public HelpBlock(String text) {
        super("div");
        addStyleName("form-text");
        setText(text);
    }
}
