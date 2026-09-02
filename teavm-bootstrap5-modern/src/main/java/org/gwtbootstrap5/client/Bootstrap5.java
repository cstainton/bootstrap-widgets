package org.gwtbootstrap5.client;

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
