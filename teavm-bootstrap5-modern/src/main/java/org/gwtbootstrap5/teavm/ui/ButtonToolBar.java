package org.gwtbootstrap5.teavm.ui;

public class ButtonToolBar extends Panel {

    public ButtonToolBar() {
        super("div");
        addStyleName("btn-toolbar");
        setAttribute("role", "toolbar");
    }

    @Override
    public ButtonToolBar add(final Widget child) {
        super.add(child);
        return this;
    }
}
