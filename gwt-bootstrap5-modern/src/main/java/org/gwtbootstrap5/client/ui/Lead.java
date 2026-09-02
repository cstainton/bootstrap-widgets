package org.gwtbootstrap5.client.ui;

public class Lead extends Paragraph {

    public Lead() {
        addStyleName("lead");
    }

    public Lead(String text) {
        this();
        setText(text);
    }
}
