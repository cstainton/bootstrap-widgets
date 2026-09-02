/*
 * TeaVM port of the Bootstrap 5 widget of the same name.
 *
 * Identical to the GWT widget in package, API and behaviour; the only difference is
 * that Bootstrap's JavaScript is reached through {@link BootstrapJs} (TeaVM @JSBody)
 * rather than JSNI, which TeaVM cannot compile. When the GWT module moves its JSNI
 * behind a shared interface, this file collapses back into that one definition.
 */
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

    @Override
    public String getText() {
        return anchor.getText();
    }

    public void setTarget(String targetId) {
        String id = targetId == null ? "" : targetId;
        anchor.setHref("#" + id);
        anchor.getElement().setAttribute("data-bs-target", "#" + id);
    }

    public void setHref(String href) {
        anchor.setHref(href == null ? "#" : href);
        anchor.getElement().setAttribute("data-bs-target", href == null ? "#" : href);
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
        anchor.getElement().setAttribute("aria-selected", active ? "true" : "false");
    }

    public boolean isActive() {
        return anchor.getStyleName().contains("active");
    }

    public void setEnabled(boolean enabled) {
        anchor.setEnabled(enabled);
        anchor.setStyleName("disabled", !enabled);
        anchor.getElement().setAttribute("aria-disabled", enabled ? "false" : "true");
    }

    public void showTab() {
        BootstrapJs.call("Tab", anchor.getElement(), "show");
    }

    public void showTab(boolean fireEvents) {
        showTab();
    }

}
