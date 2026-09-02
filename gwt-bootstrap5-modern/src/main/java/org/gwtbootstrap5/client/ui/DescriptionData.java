package org.gwtbootstrap5.client.ui;

public class DescriptionData extends ElementPanel {

    public DescriptionData() {
        this("");
    }

    public DescriptionData(String text) {
        super("dd");
        setText(text);
    }
}
