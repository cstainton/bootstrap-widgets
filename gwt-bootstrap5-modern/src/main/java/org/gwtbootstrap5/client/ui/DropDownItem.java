package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class DropDownItem extends ElementPanel {

    private final Anchor anchor = new Anchor();

    public DropDownItem() {
        super("li");
        anchor.addStyleName("dropdown-item");
        super.add(anchor);
    }

    public DropDownItem(String text, String href) {
        this();
        setText(text);
        setHref(href);
    }

    @Override
    public String getText() {
        return anchor.getText();
    }

    @Override
    public void setText(String text) {
        anchor.setText(text == null ? "" : text);
    }

    public void setHref(String href) {
        anchor.setHref(href == null ? "#" : href);
    }

    public String getHref() {
        return anchor.getHref();
    }

    @Override
    public void add(Widget child) {
        anchor.add(child);
    }

    public void setActive(boolean active) {
        anchor.setStyleName("active", active);
        anchor.getElement().setAttribute("aria-current", active ? "true" : "false");
    }

    public void setDisabled(boolean disabled) {
        anchor.setStyleName("disabled", disabled);
        anchor.getElement().setAttribute("aria-disabled", disabled ? "true" : "false");
        anchor.getElement().setAttribute("tabindex", disabled ? "-1" : "0");
    }
}
