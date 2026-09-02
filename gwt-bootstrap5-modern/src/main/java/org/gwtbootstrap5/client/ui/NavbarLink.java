package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class NavbarLink extends ElementPanel {

    private final Anchor anchor = new Anchor();

    public NavbarLink() {
        super("li");
        addStyleName("nav-item");
        anchor.addStyleName("nav-link");
        super.add(anchor);
    }

    public NavbarLink(String text, String href) {
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
        if (active) {
            anchor.getElement().setAttribute("aria-current", "page");
        } else {
            anchor.getElement().removeAttribute("aria-current");
        }
    }

    public void setDisabled(boolean disabled) {
        anchor.setStyleName("disabled", disabled);
        anchor.getElement().setAttribute("aria-disabled", disabled ? "true" : "false");
    }
}
