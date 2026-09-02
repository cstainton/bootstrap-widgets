package org.gwtbootstrap5.teavm.ui;

public class DropDownItem extends Panel {

    private final Anchor anchor = new Anchor();

    public DropDownItem() {
        super("li");
        anchor.addStyleName("dropdown-item");
        add(anchor);
    }

    public DropDownItem(final String text, final String href) {
        this();
        anchor.setText(text);
        anchor.setHref(href == null ? "#" : href);
    }

    public DropDownItem setActive(final boolean active) {
        anchor.setStyleName("active", active);
        anchor.setAttribute("aria-current", active ? "true" : "false");
        return this;
    }

    public DropDownItem setDisabled(final boolean disabled) {
        anchor.setStyleName("disabled", disabled);
        anchor.setAttribute("aria-disabled", disabled ? "true" : "false");
        anchor.setAttribute("tabindex", disabled ? "-1" : "0");
        return this;
    }
}
