package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class PageItem extends ElementPanel {

    private final Anchor link = new Anchor();

    public PageItem() {
        super("li");
        addStyleName("page-item");
        link.addStyleName("page-link");
        super.add(link);
    }

    public PageItem(String text, String href) {
        this();
        setText(text);
        setHref(href);
    }

    @Override
    public String getText() {
        return link.getText();
    }

    @Override
    public void setText(String text) {
        link.setText(text == null ? "" : text);
    }

    public void setHref(String href) {
        link.setHref(href == null ? "#" : href);
    }

    public String getHref() {
        return link.getHref();
    }

    @Override
    public void add(Widget child) {
        link.add(child);
    }

    public void setActive(boolean active) {
        setStyleName("active", active);
        if (active) {
            link.getElement().setAttribute("aria-current", "page");
        } else {
            link.getElement().removeAttribute("aria-current");
        }
    }

    public void setDisabled(boolean disabled) {
        setStyleName("disabled", disabled);
        link.getElement().setAttribute("aria-disabled", disabled ? "true" : "false");
        link.getElement().setAttribute("tabindex", disabled ? "-1" : "0");
    }
}
