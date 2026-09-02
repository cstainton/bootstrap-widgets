package org.gwtbootstrap5.client.ui;

public class Radio extends CheckBox {

    public Radio() {
        this("");
    }

    public Radio(String text) {
        super(text);
        getInput().setType("radio");
    }

    public Radio(String name, String text) {
        this(text);
        setName(name);
    }
}
