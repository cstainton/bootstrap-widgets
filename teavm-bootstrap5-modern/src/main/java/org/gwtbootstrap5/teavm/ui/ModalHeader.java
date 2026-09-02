package org.gwtbootstrap5.teavm.ui;

public class ModalHeader extends Panel {

    public ModalHeader() {
        super("div");
        addStyleName("modal-header");
    }

    public ModalHeader(final String title) {
        this();
        add(new Heading(5, title).setAttribute("class", "modal-title"));
        add(new Widget("button")
                .setAttribute("type", "button")
                .setAttribute("class", "btn-close")
                .setAttribute("data-bs-dismiss", "modal")
                .setAttribute("aria-label", "Close"));
    }
}
