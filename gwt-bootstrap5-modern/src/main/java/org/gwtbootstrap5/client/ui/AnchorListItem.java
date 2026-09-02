package org.gwtbootstrap5.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

public class AnchorListItem extends ElementPanel {

    private final Anchor anchor = new Anchor();

    public AnchorListItem() {
        super("li");
        super.add(anchor);
    }

    public AnchorListItem(String text) {
        this();
        setText(text);
    }

    public AnchorListItem(String text, String href) {
        this(text);
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

    public void setTargetHistoryToken(String targetHistoryToken) {
        anchor.setTargetHistoryToken(targetHistoryToken);
    }

    public String getTargetHistoryToken() {
        return anchor.getTargetHistoryToken();
    }

    @Override
    public void add(Widget child) {
        anchor.add(child);
    }

    public Anchor getAnchor() {
        return anchor;
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return anchor.addHandler(handler, ClickEvent.getType());
    }

    public void setActive(boolean active) {
        setStyleName("active", active);
        anchor.setStyleName("active", active);
        if (active) {
            anchor.getElement().setAttribute("aria-current", "page");
        } else {
            anchor.getElement().removeAttribute("aria-current");
        }
    }

    public boolean isActive() {
        return getStyleName().contains("active") || anchor.getStyleName().contains("active");
    }

    public void setEnabled(boolean enabled) {
        setStyleName("disabled", !enabled);
        anchor.setEnabled(enabled);
    }

    public boolean isEnabled() {
        return anchor.isEnabled();
    }
}
