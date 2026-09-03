/*
 * #%L
 * GWT Bootstrap
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
package org.gwtbootstrap3.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point for a TeaVM application using the Bootstrap 3 widgets, mirroring the
 * Bootstrap 5 track's {@code Bootstrap5}. Mounting through this class rather than
 * {@code RootPanel} directly is what injects the library's stylesheets, so a host page
 * does not have to know which ones the widgets need.
 */
public final class Bootstrap3 {

    private Bootstrap3() {
    }

    /** Attaches {@code widget} to the document body. */
    public static void mount(final IsWidget widget) {
        Bootstrap3Resources.ensureInjected();
        RootPanel.get().add(asWidget(widget));
    }

    /**
     * Attaches {@code widget} inside the element with the given id.
     *
     * @throws IllegalArgumentException if no element with that id exists
     */
    public static void mount(final String elementId, final IsWidget widget) {
        Bootstrap3Resources.ensureInjected();
        final RootPanel host = RootPanel.get(elementId);
        if (host == null) {
            throw new IllegalArgumentException("No element with id '" + elementId + "' on the page");
        }
        host.add(asWidget(widget));
    }

    /** Attaches {@code widget} inside {@code host}. */
    public static void mount(final Element host, final IsWidget widget) {
        if (host == null) {
            throw new IllegalArgumentException("host element must not be null");
        }
        mount(host.getId(), widget);
    }

    private static Widget asWidget(final IsWidget widget) {
        if (widget == null) {
            throw new IllegalArgumentException("widget must not be null");
        }
        return widget.asWidget();
    }
}
