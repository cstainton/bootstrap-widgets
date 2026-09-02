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

public class Collapse extends ElementPanel {

    public Collapse() {
        super("div");
        addStyleName("collapse");
    }

    public void setShown(boolean shown) {
        setStyleName("show", shown);
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
