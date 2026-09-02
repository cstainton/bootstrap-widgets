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

    public void setLarge(boolean large) {
        setStyleName("btn-group-lg", large);
    }

    public void setSmall(boolean small) {
        setStyleName("btn-group-sm", small);
    }

    public void addButton(Widget button) {
        add(button);
    }
}
