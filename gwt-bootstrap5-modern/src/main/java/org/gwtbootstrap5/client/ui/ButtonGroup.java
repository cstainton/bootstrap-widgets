package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class ButtonGroup extends ElementPanel {

    public ButtonGroup() {
        super("div");
        addStyleName("btn-group");
        getElement().setAttribute("role", "group");
    }

    public void setVertical(boolean vertical) {
        setStyleName("btn-group-vertical", vertical);
        setStyleName("btn-group", !vertical);
    }

    public void addButton(Widget button) {
        add(button);
    }
}
