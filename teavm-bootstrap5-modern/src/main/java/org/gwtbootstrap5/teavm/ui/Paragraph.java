package org.gwtbootstrap5.teavm.ui;

public class Paragraph extends TextWidget {

    public Paragraph() {
        super("p");
    }

    public Paragraph(final String text) {
        this();
        setText(text);
    }
}
