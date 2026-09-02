package org.gwtbootstrap5.client.ui;

public class Caption extends ElementPanel {

    public Caption() {
        super("caption");
    }

    public Caption(String text) {
        this();
        setText(text);
    }
}
