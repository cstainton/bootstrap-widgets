package org.gwtbootstrap5.client.ui;

public class PanelHeader extends ElementPanel {

    public PanelHeader() {
        super("div");
        addStyleName("card-header");
    }

    public PanelHeader(String text) {
        this();
        setText(text);
    }
}
