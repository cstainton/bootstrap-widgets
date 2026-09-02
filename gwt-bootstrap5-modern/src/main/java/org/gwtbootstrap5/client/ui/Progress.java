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
import org.gwtbootstrap5.client.ui.base.HasType;
import org.gwtbootstrap5.client.ui.constants.ProgressType;

public class Progress extends ElementPanel implements HasType<ProgressType> {

    private ProgressType type = ProgressType.DEFAULT;
    private boolean active;

    public Progress() {
        super("div");
        addStyleName("progress");
    }

    public void addBar(ProgressBar bar) {
        add(bar);
    }

    @Override
    public void add(Widget child) {
        decorateProgressBar(child);
        super.add(child);
    }

    @Override
    public void setType(ProgressType type) {
        this.type = type == null ? ProgressType.DEFAULT : type;
        updateBars();
    }

    @Override
    public ProgressType getType() {
        return type;
    }

    public void setActive(boolean active) {
        this.active = active;
        updateBars();
    }

    public boolean isActive() {
        return active;
    }

    private void updateBars() {
        for (int i = 0; i < getWidgetCount(); i++) {
            decorateProgressBar(getWidget(i));
        }
    }

    private void decorateProgressBar(Widget child) {
        if (child instanceof ProgressBar) {
            ProgressBar progressBar = (ProgressBar) child;
            progressBar.setStriped(type == ProgressType.STRIPED);
            progressBar.setAnimated(active);
        }
    }
}
