package org.gwtbootstrap5.teavm.ui;

import org.gwtbootstrap5.teavm.bootstrap.TeaVmBootstrap;

public class Modal extends Panel {

    private final FlowPanel dialog = new FlowPanel();
    private final FlowPanel content = new FlowPanel();
    private final FlowPanel body = new FlowPanel();

    public Modal() {
        super("div");
        addStyleName("modal");
        setAttribute("tabindex", "-1");
        dialog.addStyleName("modal-dialog");
        content.addStyleName("modal-content");
        body.addStyleName("modal-body");
        content.add(body);
        dialog.add(content);
        add(dialog);
    }

    public Modal addToBody(final Widget child) {
        body.add(child);
        return this;
    }

    public void show() {
        TeaVmBootstrap.showModal(unwrap());
    }

    public void hide() {
        TeaVmBootstrap.hideModal(unwrap());
    }
}
