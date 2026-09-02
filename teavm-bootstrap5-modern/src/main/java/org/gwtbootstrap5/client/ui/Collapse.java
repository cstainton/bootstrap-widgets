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
import com.google.gwt.event.shared.HandlerRegistration;
import org.gwtbootstrap5.client.shared.event.HiddenEvent;
import org.gwtbootstrap5.client.shared.event.HiddenHandler;
import org.gwtbootstrap5.client.shared.event.HideEvent;
import org.gwtbootstrap5.client.shared.event.HideHandler;
import org.gwtbootstrap5.client.shared.event.ShowEvent;
import org.gwtbootstrap5.client.shared.event.ShowHandler;
import org.gwtbootstrap5.client.shared.event.ShownEvent;
import org.gwtbootstrap5.client.shared.event.ShownHandler;
import org.gwtbootstrap5.client.ui.base.BootstrapEventBridge;
import org.gwtbootstrap5.client.ui.base.BootstrapEventHandler;

public class Collapse extends ElementPanel {

    private boolean toggle = true;

    public Collapse() {
        super("div");
        addStyleName("collapse");
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        if (toggle) {
            setShown(true);
        }
        BootstrapEventBridge.bind(getElement(), "show.bs.collapse", new BootstrapEventHandler() {
            @Override
            public void onEvent(NativeEvent event) {
                fireEvent(new ShowEvent(event));
            }
        });
        BootstrapEventBridge.bind(getElement(), "shown.bs.collapse", new BootstrapEventHandler() {
            @Override
            public void onEvent(NativeEvent event) {
                fireEvent(new ShownEvent(event));
            }
        });
        BootstrapEventBridge.bind(getElement(), "hide.bs.collapse", new BootstrapEventHandler() {
            @Override
            public void onEvent(NativeEvent event) {
                fireEvent(new HideEvent(event));
            }
        });
        BootstrapEventBridge.bind(getElement(), "hidden.bs.collapse", new BootstrapEventHandler() {
            @Override
            public void onEvent(NativeEvent event) {
                fireEvent(new HiddenEvent(event));
            }
        });
    }

    @Override
    protected void onUnload() {
        BootstrapEventBridge.unbindAll(getElement());
        BootstrapJs.dispose("Collapse", getElement());
        super.onUnload();
    }

    public void setShown(boolean shown) {
        setStyleName("show", shown);
    }

    public void setToggle(boolean toggle) {
        this.toggle = toggle;
    }

    public void setIn(boolean in) {
        setShown(in);
    }

    public boolean isShown() {
        return getStyleName().contains("show");
    }

    public boolean isHidden() {
        return !isShown();
    }

    public boolean isCollapsing() {
        return getStyleName().contains("collapsing");
    }

    public HandlerRegistration addShowHandler(ShowHandler handler) {
        return addHandler(handler, ShowEvent.getType());
    }

    public HandlerRegistration addShownHandler(ShownHandler handler) {
        return addHandler(handler, ShownEvent.getType());
    }

    public HandlerRegistration addHideHandler(HideHandler handler) {
        return addHandler(handler, HideEvent.getType());
    }

    public HandlerRegistration addHiddenHandler(HiddenHandler handler) {
        return addHandler(handler, HiddenEvent.getType());
    }

    public void show() {
        BootstrapJs.callCollapse(getElement(), "show");
    }

    public void hide() {
        BootstrapJs.callCollapse(getElement(), "hide");
    }

    public void toggle() {
        BootstrapJs.callCollapse(getElement(), "toggle");
    }




}
