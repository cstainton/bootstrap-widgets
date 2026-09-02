/*
 * #%L
 * GWT Bootstrap Modern
 * %%
 * Copyright (C) 2026 Carl Stainton
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

public class TabListItem extends ElementPanel {

    private final Anchor anchor = new Anchor();

    public TabListItem() {
        super("li");
        addStyleName("nav-item");
        anchor.addStyleName("nav-link");
        anchor.getElement().setAttribute("data-bs-toggle", "tab");
        anchor.getElement().setAttribute("role", "tab");
        super.add(anchor);
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
        anchor.setHref("#" + id);
        anchor.getElement().setAttribute("data-bs-target", "#" + id);
    }

    public void setHref(String href) {
        anchor.setHref(href == null ? "#" : href);
        anchor.getElement().setAttribute("data-bs-target", href == null ? "#" : href);
    }

    public String getHref() {
        return anchor.getHref();
    }

    @Override
    public void add(Widget child) {
        anchor.add(child);
    }

    public void setActive(boolean active) {
        anchor.setStyleName("active", active);
        anchor.getElement().setAttribute("aria-selected", active ? "true" : "false");
    }

    public boolean isActive() {
        return anchor.getStyleName().contains("active");
    }

    public void setEnabled(boolean enabled) {
        anchor.setEnabled(enabled);
        anchor.setStyleName("disabled", !enabled);
        anchor.getElement().setAttribute("aria-disabled", enabled ? "false" : "true");
    }

    public void showTab() {
        BootstrapJs.call("Tab", anchor.getElement(), "show");
    }

    public void showTab(boolean fireEvents) {
        showTab();
    }

}
