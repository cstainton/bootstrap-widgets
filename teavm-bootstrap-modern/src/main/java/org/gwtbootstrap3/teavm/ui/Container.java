package org.gwtbootstrap3.teavm.ui;

public class Container extends FlowPanel {

    public Container() {
        this(false);
    }

    public Container(final boolean fluid) {
        addStyleName(fluid ? "container-fluid" : "container");
    }
}
