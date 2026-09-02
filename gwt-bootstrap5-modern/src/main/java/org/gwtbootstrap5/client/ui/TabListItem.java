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

import org.gwtbootstrap5.client.ui.base.BootstrapComponent;

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
        BootstrapComponent.dispose(anchor.getElement(), "Tab");
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
        BootstrapComponent.call(anchor.getElement(), "Tab", "show");
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
