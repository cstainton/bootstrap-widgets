package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class Modal extends ElementPanel {

    private final ElementPanel dialog = new ElementPanel("div");
    private final ElementPanel content = new ElementPanel("div");
    private final ModalBody body = new ModalBody();

    public Modal() {
        super("div");
        addStyleName("modal");
        getElement().setAttribute("tabindex", "-1");
        dialog.addStyleName("modal-dialog");
        content.addStyleName("modal-content");
        content.add(body);
        dialog.add(content);
        add(dialog);
    }

    public void setTitle(String title) {
        ModalHeader header = new ModalHeader(title);
        content.insert(header, 0);
    }

    public void addToBody(Widget child) {
        body.add(child);
    }

    public void addHeader(ModalHeader header) {
        content.insert(header, 0);
    }

    public void addFooter(ModalFooter footer) {
        content.add(footer);
    }

    public void show() {
        show(getElement());
    }

    public void hide() {
        hide(getElement());
    }

    private static native void show(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Modal) {
            $wnd.bootstrap.Modal.getOrCreateInstance(element).show();
        }
    }-*/;

    private static native void hide(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Modal) {
            $wnd.bootstrap.Modal.getOrCreateInstance(element).hide();
        }
    }-*/;
}
