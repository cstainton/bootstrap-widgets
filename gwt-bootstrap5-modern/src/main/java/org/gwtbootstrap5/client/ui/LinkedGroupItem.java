package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.user.client.History;

public class LinkedGroupItem extends ElementPanel {

    private String targetHistoryToken;
    private Variant variant;

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
        AnchorElement.as(getElement()).setHref(href == null ? "#" : href);
    }

    public String getHref() {
        return AnchorElement.as(getElement()).getHref();
    }

    public void setTargetHistoryToken(String targetHistoryToken) {
        this.targetHistoryToken = targetHistoryToken;
        setHref("#" + History.encodeHistoryToken(targetHistoryToken));
    }

    public String getTargetHistoryToken() {
        return targetHistoryToken;
    }

    public void setActive(boolean active) {
        setStyleName("active", active);
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

    public void setType(Variant variant) {
        setVariant(variant);
    }

    public Variant getType() {
        return getVariant();
    }

    private String styleName(Variant variant) {
        return "list-group-item-" + variant.cssName();
    }
}
