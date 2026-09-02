package org.gwtbootstrap5.teavm.ui;

public class FormLabel extends TextWidget {

    public FormLabel() {
        this("");
    }

    public FormLabel(final String text) {
        super("label");
        addStyleName("form-label");
        setText(text);
    }
}
