package org.gwtbootstrap5.teavm.ui;

public class Divider extends Panel {

    public Divider() {
        super("li");
        add(new Widget("hr").setAttribute("class", "dropdown-divider"));
    }
}
