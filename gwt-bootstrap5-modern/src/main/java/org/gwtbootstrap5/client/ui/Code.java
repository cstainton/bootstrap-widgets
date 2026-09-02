package org.gwtbootstrap5.client.ui;

public class Code extends ElementPanel {

    public Code() {
        this("");
    }

    public Code(String text) {
        super("code");
        setText(text);
    }
}
