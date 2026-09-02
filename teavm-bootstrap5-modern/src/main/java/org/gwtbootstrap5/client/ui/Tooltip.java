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

public class Tooltip extends ElementPanel {

    public Tooltip() {
        super("span");
        setStyleName("d-inline-block");
        getElement().setAttribute("data-bs-toggle", "tooltip");
    }

    public Tooltip(String title) {
        this();
        setTitle(title);
    }

    public Tooltip(Widget widget, String title) {
        this(title);
        setWidget(widget);
    }

    public void setWidget(Widget widget) {
        clear();
        add(widget);
    }

    public void setTitle(String title) {
        getElement().setAttribute("title", title == null ? "" : title);
    }

    public void init() {
        BootstrapJs.init("Tooltip", getElement());
    }

    public void show() {
        BootstrapJs.call("Tooltip", getElement(), "show");
    }

    public void hide() {
        BootstrapJs.call("Tooltip", getElement(), "hide");
    }



}
