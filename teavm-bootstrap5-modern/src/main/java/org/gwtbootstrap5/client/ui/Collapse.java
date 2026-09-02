/*
 * TeaVM port of the Bootstrap 5 widget of the same name.
 *
 * Identical to the GWT widget in package, API and behaviour; the only difference is
 * that Bootstrap's JavaScript is reached through {@link BootstrapJs} (TeaVM @JSBody)
 * rather than JSNI, which TeaVM cannot compile. When the GWT module moves its JSNI
 * behind a shared interface, this file collapses back into that one definition.
 */
package org.gwtbootstrap5.client.ui;

public class Collapse extends ElementPanel {

    public Collapse() {
        super("div");
        addStyleName("collapse");
    }

    public void setShown(boolean shown) {
        setStyleName("show", shown);
    }

    public void setIn(boolean in) {
        setShown(in);
    }

    public boolean isShown() {
        return getStyleName().contains("show");
    }

    public boolean isHidden() {
        return !isShown();
    }

    public boolean isCollapsing() {
        return getStyleName().contains("collapsing");
    }

    public void show() {
        BootstrapJs.callCollapse(getElement(), "show");
    }

    public void hide() {
        BootstrapJs.callCollapse(getElement(), "hide");
    }

    public void toggle() {
        BootstrapJs.callCollapse(getElement(), "toggle");
    }



}
