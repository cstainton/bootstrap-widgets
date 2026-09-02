/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2018 GwtBootstrap3
 * %%
 * Modified from the GwtBootstrap3 original for the TeaVM track of GWT Bootstrap
 * Modern. Identical to the Bootstrap 5 widget of the same name in package, API
 * and behaviour; it exists separately only because that widget reaches
 * Bootstrap's JavaScript through JSNI, which TeaVM cannot compile. The calls go
 * through BootstrapJs instead. If the JSNI moves behind a shared interface, this
 * file collapses back into the one definition.
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
        BootstrapJs.init("Tooltip", getElement());
    }

    public void show() {
        BootstrapJs.call("Tooltip", getElement(), "show");
    }

    public void hide() {
        BootstrapJs.call("Tooltip", getElement(), "hide");
    }



}
