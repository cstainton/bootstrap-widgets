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

import org.gwtbootstrap3.teavm.dom.TeaVmDomElement;

/**
 * Base container widget for TeaVM-rendered child widgets.
 */
public class Panel extends Widget {

    protected Panel(final String tagName) {
        super(tagName);
    }

    protected Panel(final TeaVmDomElement element) {
        super(element);
    }

    public Panel add(final Widget child) {
        if (child == null) {
            throw new IllegalArgumentException("child must not be null");
        }
        getElement().appendChild(child.getElement());
        return this;
    }

    public Panel clear() {
        getElement().clear();
        return this;
    }
}
