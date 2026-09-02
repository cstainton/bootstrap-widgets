package org.gwtbootstrap5.client.ui;

public class PanelFooter extends ElementPanel {

    public PanelFooter() {
        super("div");
        addStyleName("card-footer");
    }

    public PanelFooter(String text) {
        this();
        setText(text);
    }
}
