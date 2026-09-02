package org.gwtbootstrap5.client.ui;

public class ListGroupItem extends ElementPanel {

    private Variant variant;

    public ListGroupItem() {
        super("li");
        addStyleName("list-group-item");
    }

    public ListGroupItem(String text) {
        this();
        setText(text);
    }

    public void setVariant(Variant variant) {
        if (this.variant != null) {
            removeStyleName(styleName(this.variant));
        }
        this.variant = variant;
        if (variant != null) {
            addStyleName(styleName(variant));
        }
    }

    public Variant getVariant() {
        return variant;
    }

    public void setActive(boolean active) {
        setStyleName("active", active);
        getElement().setAttribute("aria-current", active ? "true" : "false");
    }

    public void setDisabled(boolean disabled) {
        setStyleName("disabled", disabled);
        getElement().setAttribute("aria-disabled", disabled ? "true" : "false");
    }

    private String styleName(Variant variant) {
        return "list-group-item-" + variant.cssName();
    }
}
