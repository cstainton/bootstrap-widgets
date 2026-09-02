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

public class Tooltip extends ElementPanel {

    public Tooltip() {
        super("span");
        setStyleName("d-inline-block");
        getElement().setAttribute("data-bs-toggle", "tooltip");
    }

    public Tooltip(String title) {
        this();
        setTitle(title);
    }

    public Tooltip(Widget widget, String title) {
        this(title);
        setWidget(widget);
    }

    public void setWidget(Widget widget) {
        clear();
        add(widget);
    }

    public void setTitle(String title) {
        getElement().setAttribute("title", title == null ? "" : title);
    }

    public void init() {
        init(getElement());
    }

    public void show() {
        show(getElement());
    }

    public void hide() {
        hide(getElement());
    }

    private static native void init(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Tooltip) {
            $wnd.bootstrap.Tooltip.getOrCreateInstance(element);
        }
    }-*/;

    private static native void show(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Tooltip) {
            $wnd.bootstrap.Tooltip.getOrCreateInstance(element).show();
        }
    }-*/;

    private static native void hide(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Tooltip) {
            $wnd.bootstrap.Tooltip.getOrCreateInstance(element).hide();
        }
    }-*/;
}
