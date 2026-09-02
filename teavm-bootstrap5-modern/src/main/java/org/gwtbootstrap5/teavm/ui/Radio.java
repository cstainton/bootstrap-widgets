package org.gwtbootstrap5.teavm.ui;

public class Radio extends CheckBox {

    public Radio() {
        this("");
    }

    public Radio(final String text) {
        super(text);
        getInput().setType("radio");
    }
}
