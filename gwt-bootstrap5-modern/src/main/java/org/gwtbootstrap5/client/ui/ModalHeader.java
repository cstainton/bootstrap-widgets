package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.HTML;

public class ModalHeader extends ElementPanel {

    private final HTML titleWidget = new HTML();
    private final HTML closeButton = new HTML("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");

    public ModalHeader() {
        super("div");
        addStyleName("modal-header");
        titleWidget.addStyleName("modal-title h5");
        add(titleWidget);
    }

    public ModalHeader(String title) {
        this();
        setTitle(title);
        addCloseButton();
    }

    public void setTitle(String title) {
        titleWidget.setHTML(escape(title));
    }

    public void addCloseButton() {
        setClosable(true);
    }

    public void setClosable(boolean closable) {
        if (closable) {
            if (closeButton.getParent() == null) {
                add(closeButton);
            }
        } else {
            closeButton.removeFromParent();
        }
    }

    public boolean isClosable() {
        return closeButton.getParent() != null;
    }

    private String escape(String value) {
        return value == null ? "" : value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}
