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

import java.util.List;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.gwtbootstrap5.client.shared.event.TabShowEvent;
import org.gwtbootstrap5.client.shared.event.TabShowHandler;
import org.gwtbootstrap5.client.shared.event.TabShownEvent;
import org.gwtbootstrap5.client.shared.event.TabShownHandler;
import org.gwtbootstrap5.client.ui.base.BootstrapEventBridge;
import org.gwtbootstrap5.client.ui.base.BootstrapEventHandler;
import org.gwtbootstrap5.client.ui.base.HasActive;
import org.gwtbootstrap5.client.ui.base.HasDataTarget;
import org.gwtbootstrap5.client.ui.base.HasHref;
import org.gwtbootstrap5.client.ui.base.mixin.DataTargetMixin;
import org.gwtbootstrap5.client.ui.constants.Toggle;

public class TabListItem extends ElementPanel implements HasActive, HasEnabled, HasHref, HasDataTarget {

    private final Anchor anchor = new Anchor();
    private final DataTargetMixin<Anchor> targetMixin = new DataTargetMixin<Anchor>(anchor);

    public TabListItem() {
        super("li");
        addStyleName("nav-item");
        anchor.addStyleName("nav-link");
        anchor.getElement().setAttribute("data-bs-toggle", "tab");
        anchor.getElement().setAttribute("role", "tab");
        super.add(anchor);
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        BootstrapEventBridge.bind(anchor.getElement(), "show.bs.tab", new BootstrapEventHandler() {
            @Override
            public void onEvent(NativeEvent event) {
                fireEvent(new TabShowEvent(TabListItem.this, Event.as(event)));
            }
        });
        BootstrapEventBridge.bind(anchor.getElement(), "shown.bs.tab", new BootstrapEventHandler() {
            @Override
            public void onEvent(NativeEvent event) {
                fireEvent(new TabShownEvent(TabListItem.this, Event.as(event)));
            }
        });
    }

    @Override
    protected void onUnload() {
        BootstrapEventBridge.unbindAll(anchor.getElement());
        BootstrapJs.dispose("Tab", anchor.getElement());
        super.onUnload();
    }

    public TabListItem(String text, String targetId) {
        this();
        setText(text);
        setTarget(targetId);
    }

    @Override
    public void setText(String text) {
        anchor.setText(text == null ? "" : text);
    }

    @Override
    public String getText() {
        return anchor.getText();
    }

    public void setTarget(String targetId) {
        String id = targetId == null ? "" : targetId;
        setDataTarget("#" + id);
    }

    @Override
    public void setHref(String href) {
        setDataTarget(href == null ? "#" : href);
    }

    @Override
    public String getHref() {
        return getDataTarget();
    }

    @Override
    public void setDataTargetWidgets(List<Widget> widgets) {
        targetMixin.setDataTargetWidgets(widgets);
        anchor.setHref(targetMixin.getDataTarget());
    }

    @Override
    public void setDataTargetWidget(Widget widget) {
        targetMixin.setDataTargetWidget(widget);
        anchor.setHref(targetMixin.getDataTarget());
    }

    @Override
    public void setDataTarget(String dataTarget) {
        targetMixin.setDataTarget(dataTarget);
        anchor.setHref(dataTarget == null ? "#" : dataTarget);
    }

    @Override
    public String getDataTarget() {
        return targetMixin.getDataTarget();
    }

    @Override
    public void add(Widget child) {
        anchor.add(child);
    }

    @Override
    public void setActive(boolean active) {
        anchor.setStyleName("active", active);
        anchor.getElement().setAttribute("aria-selected", active ? "true" : "false");
    }

    @Override
    public boolean isActive() {
        return anchor.getStyleName().contains("active");
    }

    @Override
    public void setEnabled(boolean enabled) {
        anchor.setEnabled(enabled);
        anchor.setStyleName("disabled", !enabled);
        anchor.getElement().setAttribute("aria-disabled", enabled ? "false" : "true");
        anchor.setDataToggle(enabled ? Toggle.TAB : null);
    }

    @Override
    public boolean isEnabled() {
        return anchor.isEnabled();
    }

    public void showTab() {
        BootstrapJs.call("Tab", anchor.getElement(), "show");
    }

    public void showTab(boolean fireEvents) {
        showTab();
    }

    public HandlerRegistration addShowHandler(TabShowHandler handler) {
        return addHandler(handler, TabShowEvent.getType());
    }

    public HandlerRegistration addShownHandler(TabShownHandler handler) {
        return addHandler(handler, TabShownEvent.getType());
    }

    public String getHTML() {
        return anchor.getHTML();
    }

    public void setHTML(String html) {
        anchor.setHTML(html);
    }


}
