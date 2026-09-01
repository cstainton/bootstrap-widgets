package org.gwtbootstrap3.client.ui;

import org.gwtbootstrap3.client.ui.html.Div;

public class Container extends Div {
    public Container() {
        setStyleName("container");
    }

    public void setFluid(final boolean fluid) {
        removeStyleName(fluid ? "container" : "container-fluid");
        addStyleName(fluid ? "container-fluid" : "container");
    }
}
