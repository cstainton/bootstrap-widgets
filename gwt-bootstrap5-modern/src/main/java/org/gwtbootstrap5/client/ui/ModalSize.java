package org.gwtbootstrap5.client.ui;

public enum ModalSize {
    SMALL("modal-sm"),
    DEFAULT(""),
    LARGE("modal-lg"),
    EXTRA_LARGE("modal-xl");

    private final String cssName;

    ModalSize(String cssName) {
        this.cssName = cssName;
    }

    public String cssName() {
        return cssName;
    }
}
