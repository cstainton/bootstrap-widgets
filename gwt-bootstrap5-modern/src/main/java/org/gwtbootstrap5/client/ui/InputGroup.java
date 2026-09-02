package org.gwtbootstrap5.client.ui;

public class InputGroup extends ElementPanel {

    public InputGroup() {
        super("div");
        addStyleName("input-group");
    }

    public void setLarge(boolean large) {
        setStyleName("input-group-lg", large);
    }

    public void setSmall(boolean small) {
        setStyleName("input-group-sm", small);
    }

    public boolean isLarge() {
        return getStyleName().contains("input-group-lg");
    }

    public boolean isSmall() {
        return getStyleName().contains("input-group-sm");
    }
}
