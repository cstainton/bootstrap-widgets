package org.gwtbootstrap5.client.ui;

public class TabPane extends ElementPanel {

    public TabPane() {
        super("div");
        addStyleName("tab-pane fade");
        getElement().setAttribute("role", "tabpanel");
    }

    public void setActive(boolean active) {
        setStyleName("show", active);
        setStyleName("active", active);
    }
}
