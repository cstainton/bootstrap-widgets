package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class TabListItem extends ElementPanel {

    private final Anchor anchor = new Anchor();

    public TabListItem() {
        super("li");
        addStyleName("nav-item");
        anchor.addStyleName("nav-link");
        anchor.getElement().setAttribute("data-bs-toggle", "tab");
        anchor.getElement().setAttribute("role", "tab");
        super.add(anchor);
    }

    public TabListItem(String text, String targetId) {
        this();
        setText(text);
        setTarget(targetId);
    }

    @Override
    public void setText(String text) {
        anchor.setText(text == null ? "" : text);
    }

    public void setTarget(String targetId) {
        String id = targetId == null ? "" : targetId;
        anchor.setHref("#" + id);
        anchor.getElement().setAttribute("data-bs-target", "#" + id);
    }

    @Override
    public void add(Widget child) {
        anchor.add(child);
    }

    public void setActive(boolean active) {
        anchor.setStyleName("active", active);
        anchor.getElement().setAttribute("aria-selected", active ? "true" : "false");
    }
}
