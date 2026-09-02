package org.gwtbootstrap5.teavm.ui;

import org.gwtbootstrap5.teavm.bootstrap.TeaVmBootstrap;

public class Popover extends Tooltip {

    public Popover() {
        super();
        setAttribute("data-bs-toggle", "popover");
    }

    public Popover(final String title, final String content) {
        this();
        setTitle(title);
        setContent(content);
    }

    public Popover(final Widget widget, final String title, final String content) {
        this(title, content);
        setWidget(widget);
    }

    public Popover setContent(final String content) {
        setAttribute("data-bs-content", content == null ? "" : content);
        return this;
    }

    @Override
    public void init() {
        TeaVmBootstrap.initPopover(unwrap());
    }

    @Override
    public void show() {
        TeaVmBootstrap.showPopover(unwrap());
    }

    @Override
    public void hide() {
        TeaVmBootstrap.hidePopover(unwrap());
    }
}
