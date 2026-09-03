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
package io.instanto.bootstrap5.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Attaches Bootstrap 5 widgets to the page.
 *
 * <pre>{@code
 * public final class MyApp {
 *     public static void main(String[] args) {
 *         Container container = new Container();
 *         container.add(new Button("Hello"));
 *         Bootstrap5.mount(container);
 *     }
 * }
 * }</pre>
 *
 * <p>{@link #mount(IsWidget)} attaches to {@code <body>}; {@link #mount(String, IsWidget)}
 * attaches into the element with the given id, which is usually what you want when the
 * widgets are one part of an existing page.</p>
 */
public final class Bootstrap5 {

    private Bootstrap5() {
    }

    /** Attaches {@code widget} to the document body. */
    public static void mount(final IsWidget widget) {
        RootPanel.get().add(asWidget(widget));
    }

    /**
     * Attaches {@code widget} inside the element with the given id.
     *
     * @throws IllegalArgumentException if no element with that id exists
     */
    public static void mount(final String elementId, final IsWidget widget) {
        final RootPanel host = RootPanel.get(elementId);
        if (host == null) {
            throw new IllegalArgumentException("No element with id '" + elementId + "' on the page");
        }
        host.add(asWidget(widget));
    }

    /** Attaches {@code widget} inside {@code host}, replacing nothing already there. */
    public static void mount(final Element host, final IsWidget widget) {
        if (host == null) {
            throw new IllegalArgumentException("host element must not be null");
        }
        final String id = host.getId() == null || host.getId().isEmpty()
                ? assignId(host) : host.getId();
        mount(id, widget);
    }

    /** True when Bootstrap's own JavaScript bundle is present on the page. */
    public static boolean isBootstrapJavaScriptLoaded() {
        return bootstrapPresent();
    }

    private static Widget asWidget(final IsWidget widget) {
        if (widget == null) {
            throw new IllegalArgumentException("widget must not be null");
        }
        return widget.asWidget();
    }

    private static String assignId(final Element host) {
        final String id = Document.get().createUniqueId();
        host.setId(id);
        return id;
    }

    @org.teavm.jso.JSBody(script = "return !!(window.bootstrap && window.bootstrap.Modal);")
    private static native boolean bootstrapPresent();
}
