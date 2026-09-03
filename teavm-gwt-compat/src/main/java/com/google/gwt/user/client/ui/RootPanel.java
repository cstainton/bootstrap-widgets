/*
 * #%L
 * GWT Bootstrap
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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

public final class RootPanel extends ComplexPanel {
    private static RootPanel bodyRoot;

    private RootPanel(final Element element) {
        setElement(element);
        onAttach();
    }

    /** No-op on TeaVM: there is no window-close detach pass to register with. */
    public static void detachOnWindowClose(final Widget widget) {
    }

    public static RootPanel get() {
        if (bodyRoot == null) {
            bodyRoot = new RootPanel(Document.get().getBody());
        }
        return bodyRoot;
    }

    public static RootPanel get(final String id) {
        final Element element = Document.get().getElementById(id);
        return element == null ? null : new RootPanel(element);
    }
}
