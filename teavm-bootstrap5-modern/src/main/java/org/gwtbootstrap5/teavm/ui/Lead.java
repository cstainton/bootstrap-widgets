package org.gwtbootstrap5.teavm.ui;

public class Lead extends Paragraph {

    public Lead() {
        addStyleName("lead");
    }

    public Lead(final String text) {
        this();
        setText(text);
    }
}
