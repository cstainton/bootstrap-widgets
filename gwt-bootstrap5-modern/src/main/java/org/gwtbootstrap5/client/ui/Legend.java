package org.gwtbootstrap5.client.ui;

public class Legend extends ElementPanel {

    public Legend() {
        this("");
    }

    public Legend(String text) {
        super("legend");
        setText(text);
    }
}
