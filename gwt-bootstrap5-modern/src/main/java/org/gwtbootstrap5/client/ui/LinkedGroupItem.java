package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.user.client.History;

public class LinkedGroupItem extends ElementPanel {

    private String targetHistoryToken;

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
}
