/*
 * #%L
 * GWT Bootstrap Modern
 * %%
 * Copyright (C) 2026 Carl Stainton
 * %%
 * Reimplements, over TeaVM's JSO libraries, part of the GWT client API. Class,
 * method and package names follow GWT (https://github.com/gwtproject/gwt),
 * Copyright (C) The GWT Project Authors, licensed under the Apache License,
 * Version 2.0. No GWT source is included.
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

/*
 * TeaVM implementation of the Bootstrap 5 widget of the same name.
 *
 * Identical to the GWT widget in package, API and behaviour. It exists separately only
 * because that widget reaches Bootstrap's JavaScript through JSNI, which TeaVM cannot
 * compile; the calls go through BootstrapJs instead. Keep this file in step with the
 * GWT one -- or, better, move the remaining JSNI behind a shared seam so both builds
 * can use a single definition, as BootstrapEventBridge already does for events.
 */
package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.gwtbootstrap5.client.shared.event.ModalHiddenEvent;
import org.gwtbootstrap5.client.shared.event.ModalHiddenHandler;
import org.gwtbootstrap5.client.shared.event.ModalHideEvent;
import org.gwtbootstrap5.client.shared.event.ModalHideHandler;
import org.gwtbootstrap5.client.shared.event.ModalShowEvent;
import org.gwtbootstrap5.client.shared.event.ModalShowHandler;
import org.gwtbootstrap5.client.shared.event.ModalShownEvent;
import org.gwtbootstrap5.client.shared.event.ModalShownHandler;
import org.gwtbootstrap5.client.ui.base.BootstrapEventBridge;
import org.gwtbootstrap5.client.ui.base.BootstrapEventHandler;
import org.gwtbootstrap5.client.ui.constants.ModalBackdrop;

public class Modal extends ElementPanel implements IsClosable {

    private final ElementPanel dialog = new ElementPanel("div");
    private final ElementPanel content = new ElementPanel("div");
    private ModalHeader header = new ModalHeader();
    private final ModalBody body = new ModalBody();
    private ModalSize size = ModalSize.DEFAULT;
    private boolean hideOtherModals;
    private boolean removeOnHide;

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

    @Override
    protected void onLoad() {
        super.onLoad();
        BootstrapEventBridge.bind(getElement(), "show.bs.modal", new BootstrapEventHandler() {
            @Override
            public void onEvent(NativeEvent event) {
                onShow(Event.as(event));
            }
        });
        BootstrapEventBridge.bind(getElement(), "shown.bs.modal", new BootstrapEventHandler() {
            @Override
            public void onEvent(NativeEvent event) {
                onShown(Event.as(event));
            }
        });
        BootstrapEventBridge.bind(getElement(), "hide.bs.modal", new BootstrapEventHandler() {
            @Override
            public void onEvent(NativeEvent event) {
                onHide(Event.as(event));
            }
        });
        BootstrapEventBridge.bind(getElement(), "hidden.bs.modal", new BootstrapEventHandler() {
            @Override
            public void onEvent(NativeEvent event) {
                onHidden(Event.as(event));
            }
        });
    }

    @Override
    protected void onUnload() {
        BootstrapEventBridge.unbindAll(getElement());
        BootstrapJs.dispose("Modal", getElement());
        super.onUnload();
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

    public void setDataBackdrop(ModalBackdrop backdrop) {
        setDataBackdrop(backdrop == null ? null : backdrop.getBackdrop());
    }

    public void setDataKeyboard(boolean keyboard) {
        getElement().setAttribute("data-bs-keyboard", Boolean.toString(keyboard));
    }

    public void setRemoveOnHide(boolean removeOnHide) {
        this.removeOnHide = removeOnHide;
    }

    public void setHideOtherModals(boolean hideOtherModals) {
        this.hideOtherModals = hideOtherModals;
    }

    public void show() {
        if (!isAttached()) {
            RootPanel.get().add(this);
        }
        BootstrapJs.call("Modal", getElement(), "show");
    }

    public void hide() {
        BootstrapJs.call("Modal", getElement(), "hide");
    }

    public void toggle() {
        BootstrapJs.call("Modal", getElement(), "toggle");
    }

    public HandlerRegistration addShowHandler(ModalShowHandler handler) {
        return addHandler(handler, ModalShowEvent.getType());
    }

    public HandlerRegistration addShownHandler(ModalShownHandler handler) {
        return addHandler(handler, ModalShownEvent.getType());
    }

    public HandlerRegistration addHideHandler(ModalHideHandler handler) {
        return addHandler(handler, ModalHideEvent.getType());
    }

    public HandlerRegistration addHiddenHandler(ModalHiddenHandler handler) {
        return addHandler(handler, ModalHiddenEvent.getType());
    }

    protected void onShow(Event event) {
        if (hideOtherModals) {
            BootstrapJs.hideOtherModals(getElement());
        }
        fireEvent(new ModalShowEvent(this, event));
    }

    protected void onShown(Event event) {
        fireEvent(new ModalShownEvent(this, event));
    }

    protected void onHide(Event event) {
        fireEvent(new ModalHideEvent(this, event));
    }

    protected void onHidden(Event event) {
        fireEvent(new ModalHiddenEvent(this, event));
        if (removeOnHide) {
            removeFromParent();
        }
    }





}
