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

public class Popover extends Tooltip {

    public Popover() {
        super();
        getElement().setAttribute("data-bs-toggle", "popover");
    }

    public Popover(String title, String content) {
        this();
        setTitle(title);
        setContent(content);
    }

    public Popover(Widget widget, String title, String content) {
        this(title, content);
        setWidget(widget);
    }

    public void setContent(String content) {
        getElement().setAttribute("data-bs-content", content == null ? "" : content);
    }

    @Override
    public void init() {
        BootstrapJs.init("Popover", getElement());
    }

    @Override
    public void show() {
        BootstrapJs.call("Popover", getElement(), "show");
    }

    @Override
    public void hide() {
        BootstrapJs.call("Popover", getElement(), "hide");
    }



}
