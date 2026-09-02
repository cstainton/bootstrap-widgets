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
package org.gwtbootstrap3.teavm.ui;

import org.gwtbootstrap3.teavm.bootstrap.TeaVmBootstrap;

public class Modal extends Panel {

    private final FlowPanel dialog = new FlowPanel();
    private final FlowPanel content = new FlowPanel();
    private final FlowPanel body = new FlowPanel();

    public Modal() {
        super("div");
        addStyleName("modal");
        setAttribute("tabindex", "-1");
        dialog.addStyleName("modal-dialog");
        content.addStyleName("modal-content");
        body.addStyleName("modal-body");
        content.add(body);
        dialog.add(content);
        add(dialog);
    }

    public Modal addToBody(final Widget child) {
        body.add(child);
        return this;
    }

    public void show() {
        TeaVmBootstrap.showModal(unwrap());
    }

    public void hide() {
        TeaVmBootstrap.hideModal(unwrap());
    }
}
