package org.gwtbootstrap5.client.ui;

public class Paragraph extends ElementPanel {

    public Paragraph() {
        super("p");
    }

    public Paragraph(String text) {
        this();
        setText(text);
    }
}
