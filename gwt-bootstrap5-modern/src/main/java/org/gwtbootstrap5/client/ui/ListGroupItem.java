package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.HasType;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.ListGroupItemType;

public class ListGroupItem extends ElementPanel implements HasType<ListGroupItemType> {

    private Variant variant;

    public ListGroupItem() {
        super("li");
        addStyleName("list-group-item");
    }

    public ListGroupItem(String text) {
        this();
        setText(text);
    }

    public ListGroupItem(String text, ListGroupItemType type) {
        this(text);
        setType(type);
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

    @Override
    public void setType(ListGroupItemType type) {
        StyleHelper.addUniqueEnumStyleName(this, ListGroupItemType.class, type == null ? ListGroupItemType.DEFAULT : type);
    }

    @Override
    public ListGroupItemType getType() {
        return ListGroupItemType.fromStyleName(getStyleName());
    }

    public void setActive(boolean active) {
        setStyleName("active", active);
        getElement().setAttribute("aria-current", active ? "true" : "false");
    }

    public boolean isActive() {
        return getStyleName().contains("active");
    }

    public void setDisabled(boolean disabled) {
        setStyleName("disabled", disabled);
        getElement().setAttribute("aria-disabled", disabled ? "true" : "false");
    }

    public void setEnabled(boolean enabled) {
        setDisabled(!enabled);
    }

    public boolean isEnabled() {
        return !"true".equals(getElement().getAttribute("aria-disabled"));
    }

    private String styleName(Variant variant) {
        return "list-group-item-" + variant.cssName();
    }
}
