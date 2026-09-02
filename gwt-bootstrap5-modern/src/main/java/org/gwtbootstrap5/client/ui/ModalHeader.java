package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.HTML;

public class ModalHeader extends ElementPanel {

    public ModalHeader() {
        super("div");
        addStyleName("modal-header");
    }

    public ModalHeader(String title) {
        this();
        setTitle(title);
        addCloseButton();
    }

    public void setTitle(String title) {
        setHTML("<h5 class=\"modal-title\">" + escape(title) + "</h5>");
    }

    public void addCloseButton() {
        add(new HTML("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>"));
    }

    private String escape(String value) {
        return value == null ? "" : value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}
