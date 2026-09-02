/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2018 GwtBootstrap3
 * %%
 * Modified from the GwtBootstrap3 original for the Bootstrap 5 track of
 * GWT Bootstrap Modern: moved to the org.gwtbootstrap5 namespace and re-targeted
 * at Bootstrap 5 markup, class names and JavaScript APIs.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class Modal extends ElementPanel {

    private final ElementPanel dialog = new ElementPanel("div");
    private final ElementPanel content = new ElementPanel("div");
    private ModalHeader header = new ModalHeader();
    private final ModalBody body = new ModalBody();
    private ModalSize size = ModalSize.DEFAULT;

    public Modal() {
        super("div");
        addStyleName("modal");
        getElement().setAttribute("tabindex", "-1");
        dialog.addStyleName("modal-dialog");
        content.addStyleName("modal-content");
        content.add(header);
        content.add(body);
        dialog.add(content);
        super.add(dialog);
    }

    public void setTitle(String title) {
        header.setTitle(title);
    }

    public void addToBody(Widget child) {
        body.add(child);
    }

    public void addHeader(ModalHeader header) {
        this.header.removeFromParent();
        this.header = header == null ? new ModalHeader() : header;
        content.insert(this.header, 0);
    }

    public void addFooter(ModalFooter footer) {
        content.add(footer);
    }

    @Override
    public void add(Widget child) {
        if (child instanceof ModalHeader) {
            addHeader((ModalHeader) child);
        } else if (child instanceof ModalFooter) {
            addFooter((ModalFooter) child);
        } else if (child instanceof ModalBody) {
            body.removeFromParent();
            content.add(child);
        } else {
            addToBody(child);
        }
    }

    public void setClosable(boolean closable) {
        header.setClosable(closable);
    }

    public boolean isClosable() {
        return header.isClosable();
    }

    public void setFade(boolean fade) {
        setStyleName("fade", fade);
    }

    public void setWidth(String width) {
        dialog.setWidth(width);
    }

    public void setSize(ModalSize size) {
        if (this.size != null && !this.size.cssName().isEmpty()) {
            dialog.removeStyleName(this.size.cssName());
        }
        this.size = size == null ? ModalSize.DEFAULT : size;
        if (!this.size.cssName().isEmpty()) {
            dialog.addStyleName(this.size.cssName());
        }
    }

    public ModalSize getSize() {
        return size;
    }

    public void setDataBackdrop(String backdrop) {
        if (backdrop == null || backdrop.isEmpty()) {
            getElement().removeAttribute("data-bs-backdrop");
        } else {
            getElement().setAttribute("data-bs-backdrop", backdrop);
        }
    }

    public void setDataKeyboard(boolean keyboard) {
        getElement().setAttribute("data-bs-keyboard", Boolean.toString(keyboard));
    }

    public void setRemoveOnHide(boolean removeOnHide) {
        getElement().setAttribute("data-gbm-remove-on-hide", Boolean.toString(removeOnHide));
    }

    public void setHideOtherModals(boolean hideOtherModals) {
        getElement().setAttribute("data-gbm-hide-other-modals", Boolean.toString(hideOtherModals));
    }

    public void show() {
        show(getElement());
    }

    public void hide() {
        hide(getElement());
    }

    public void toggle() {
        toggle(getElement());
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

    private static native void toggle(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Modal) {
            $wnd.bootstrap.Modal.getOrCreateInstance(element).toggle();
        }
    }-*/;
}
