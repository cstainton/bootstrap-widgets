package org.gwtbootstrap5.client.ui;

public class LinkedGroupItem extends ElementPanel {

    public LinkedGroupItem() {
        this("", "#");
    }

    public LinkedGroupItem(String text, String href) {
        super("a");
        addStyleName("list-group-item list-group-item-action");
        setText(text);
        setHref(href);
    }

    public void setHref(String href) {
        getElement().setAttribute("href", href == null ? "#" : href);
    }

    public void setActive(boolean active) {
        setStyleName("active", active);
    }

    public void setDisabled(boolean disabled) {
        setStyleName("disabled", disabled);
        getElement().setAttribute("aria-disabled", disabled ? "true" : "false");
    }
}
