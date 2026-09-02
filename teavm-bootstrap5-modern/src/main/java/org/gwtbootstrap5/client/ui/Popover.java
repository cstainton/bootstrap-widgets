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

public class Popover extends Tooltip {

    public Popover() {
        super();
        getElement().setAttribute("data-bs-toggle", "popover");
    }

    public Popover(String title, String content) {
        this();
        setTitle(title);
        setContent(content);
    }

    public Popover(Widget widget, String title, String content) {
        this(title, content);
        setWidget(widget);
    }

    public void setContent(String content) {
        getElement().setAttribute("data-bs-content", content == null ? "" : content);
    }

    @Override
    public void init() {
        BootstrapJs.init("Popover", getElement());
    }

    @Override
    public void show() {
        BootstrapJs.call("Popover", getElement(), "show");
    }

    @Override
    public void hide() {
        BootstrapJs.call("Popover", getElement(), "hide");
    }



}
