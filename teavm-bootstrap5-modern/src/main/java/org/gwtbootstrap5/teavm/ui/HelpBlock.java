package org.gwtbootstrap5.teavm.ui;

public class HelpBlock extends TextWidget {

    public HelpBlock() {
        this("");
    }

    public HelpBlock(final String text) {
        super("div");
        addStyleName("form-text");
        setText(text);
    }
}
