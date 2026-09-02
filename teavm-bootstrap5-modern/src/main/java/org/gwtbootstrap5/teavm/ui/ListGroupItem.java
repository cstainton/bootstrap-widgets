package org.gwtbootstrap5.teavm.ui;

public class ListGroupItem extends TextWidget {

    private Variant variant;

    public ListGroupItem() {
        super("li");
        addStyleName("list-group-item");
    }

    public ListGroupItem(final String text) {
        this();
        setText(text);
    }

    public ListGroupItem setVariant(final Variant variant) {
        if (this.variant != null) {
            removeStyleName(styleName(this.variant));
        }
        this.variant = variant;
        if (variant != null) {
            addStyleName(styleName(variant));
        }
        return this;
    }

    public ListGroupItem setActive(final boolean active) {
        setStyleName("active", active);
        setAttribute("aria-current", active ? "true" : "false");
        return this;
    }

    public ListGroupItem setDisabled(final boolean disabled) {
        setStyleName("disabled", disabled);
        setAttribute("aria-disabled", disabled ? "true" : "false");
        return this;
    }

    private String styleName(final Variant variant) {
        return "list-group-item-" + variant.cssName();
    }
}
