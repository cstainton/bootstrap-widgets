package org.gwtbootstrap5.teavm.ui;

import org.gwtbootstrap5.teavm.bootstrap.TeaVmBootstrap;

public class Collapse extends Panel {

    public Collapse() {
        super("div");
        addStyleName("collapse");
    }

    @Override
    public Collapse add(final Widget child) {
        super.add(child);
        return this;
    }

    public Collapse setShown(final boolean shown) {
        setStyleName("show", shown);
        return this;
    }

    public void show() {
        TeaVmBootstrap.showCollapse(unwrap());
    }

    public void hide() {
        TeaVmBootstrap.hideCollapse(unwrap());
    }

    public void toggle() {
        TeaVmBootstrap.toggleCollapse(unwrap());
    }
}
