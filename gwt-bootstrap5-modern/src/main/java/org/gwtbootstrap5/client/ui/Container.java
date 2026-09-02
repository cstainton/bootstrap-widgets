package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.FlowPanel;

public class Container extends FlowPanel {

    public Container() {
        setFluid(false);
    }

    public void setFluid(boolean fluid) {
        removeStyleName(fluid ? "container" : "container-fluid");
        addStyleName(fluid ? "container-fluid" : "container");
    }
}
