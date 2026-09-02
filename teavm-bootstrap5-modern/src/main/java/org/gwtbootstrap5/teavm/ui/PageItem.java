package org.gwtbootstrap5.teavm.ui;

public class PageItem extends Panel {

    private final Anchor link = new Anchor();

    public PageItem() {
        super("li");
        addStyleName("page-item");
        link.addStyleName("page-link");
        add(link);
    }

    public PageItem(final String text, final String href) {
        this();
        link.setText(text);
        link.setHref(href == null ? "#" : href);
    }

    public PageItem setActive(final boolean active) {
        setStyleName("active", active);
        link.setAttribute("aria-current", active ? "page" : "false");
        return this;
    }

    public PageItem setDisabled(final boolean disabled) {
        setStyleName("disabled", disabled);
        link.setAttribute("aria-disabled", disabled ? "true" : "false");
        link.setAttribute("tabindex", disabled ? "-1" : "0");
        return this;
    }
}
