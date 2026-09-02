package org.gwtbootstrap5.teavm.ui;

import org.gwtbootstrap5.teavm.bootstrap.TeaVmBootstrap;

public class Tooltip extends Panel {

    public Tooltip() {
        super("span");
        setStyleName("d-inline-block");
        setAttribute("data-bs-toggle", "tooltip");
    }

    public Tooltip(final String title) {
        this();
        setTitle(title);
    }

    public Tooltip(final Widget widget, final String title) {
        this(title);
        setWidget(widget);
    }

    public Tooltip setWidget(final Widget widget) {
        clear();
        add(widget);
        return this;
    }

    @Override
    public void setTitle(final String title) {
        getElement().setAttribute("title", title == null ? "" : title);
    }

    public void init() {
        TeaVmBootstrap.initTooltip(unwrap());
    }

    public void show() {
        TeaVmBootstrap.showTooltip(unwrap());
    }

    public void hide() {
        TeaVmBootstrap.hideTooltip(unwrap());
    }
}
