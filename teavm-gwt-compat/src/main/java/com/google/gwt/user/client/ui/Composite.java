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
package com.google.gwt.user.client.ui;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Widget that wraps another widget and hides its API, mirroring GWT's {@code Composite}.
 */
public abstract class Composite extends Widget {

    private Widget widget;

    protected Widget getWidget() {
        return widget;
    }

    protected void initWidget(final Widget widget) {
        if (this.widget != null) {
            throw new IllegalStateException("initWidget() may only be called once");
        }
        if (widget == null) {
            throw new IllegalArgumentException("widget must not be null");
        }
        widget.removeFromParent();
        this.widget = widget;
        setElement(widget.getElement());
        widget.setParent(this);
    }

    @Override
    public void fireEvent(final GwtEvent<?> event) {
        super.fireEvent(event);
        if (widget != null) {
            widget.fireEvent(event);
        }
    }
}
