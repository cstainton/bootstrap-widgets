package org.gwtbootstrap5.client.ui;

public class DescriptionTitle extends ElementPanel {

    public DescriptionTitle() {
        this("");
    }

    public DescriptionTitle(String text) {
        super("dt");
        setText(text);
    }
}
