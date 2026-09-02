package org.gwtbootstrap5.client.ui;

public class ButtonToolBar extends ElementPanel {

    public ButtonToolBar() {
        super("div");
        addStyleName("btn-toolbar");
        getElement().setAttribute("role", "toolbar");
    }

    public void addGroup(ButtonGroup group) {
        add(group);
    }
}
