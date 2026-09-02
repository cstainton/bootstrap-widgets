package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class InputGroupButton extends ElementPanel {

    public InputGroupButton() {
        super("span");
        setStyleName("input-group-text p-0 border-0 bg-transparent");
    }

    public InputGroupButton(Widget child) {
        this();
        add(child);
    }
}
