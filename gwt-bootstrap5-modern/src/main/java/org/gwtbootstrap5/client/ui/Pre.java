package org.gwtbootstrap5.client.ui;

public class Pre extends ElementPanel {

    public Pre() {
        this("");
    }

    public Pre(String text) {
        super("pre");
        setText(text);
    }
}
