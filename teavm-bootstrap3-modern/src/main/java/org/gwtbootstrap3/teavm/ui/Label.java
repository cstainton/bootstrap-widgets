package org.gwtbootstrap3.teavm.ui;

public class Label extends TextWidget {

    public Label() {
        super("span");
    }

    public Label(final String text) {
        this();
        setText(text);
    }
}
