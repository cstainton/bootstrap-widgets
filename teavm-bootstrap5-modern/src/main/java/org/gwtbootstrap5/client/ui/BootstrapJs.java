package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.Element;
import org.teavm.jso.JSBody;
import org.teavm.jso.dom.html.HTMLElement;

/**
 * The single seam between the Bootstrap 5 widgets and Bootstrap's own JavaScript.
 *
 * <p>The GWT module reaches Bootstrap through JSNI, which TeaVM cannot compile. Every
 * TeaVM-side widget that drives a Bootstrap component calls through here instead, so
 * the JS bridge exists once rather than once per widget.</p>
 *
 * <p>Each call is a no-op when Bootstrap's bundle has not been loaded, matching the
 * guard the GWT JSNI blocks use.</p>
 */
final class BootstrapJs {

    private BootstrapJs() {
    }

    /** Creates the component instance for {@code element} without invoking anything. */
    static void init(final String component, final Element element) {
        create(component, element.unwrap());
    }

    /** Invokes a no-argument method on the component instance for {@code element}. */
    static void call(final String component, final Element element, final String method) {
        invoke(component, element.unwrap(), method);
    }

    /** Invokes a single-int-argument method, such as the carousel's {@code to}. */
    static void call(final String component, final Element element, final String method,
            final int argument) {
        invokeWithInt(component, element.unwrap(), method, argument);
    }

    /** Creates a Collapse instance with {@code toggle: false}, as the widgets require. */
    static void callCollapse(final Element element, final String method) {
        invokeCollapse(element.unwrap(), method);
    }

    @JSBody(params = {"name", "el"}, script =
            "if (window.bootstrap && window.bootstrap[name]) {"
            + " window.bootstrap[name].getOrCreateInstance(el); }")
    private static native void create(String name, HTMLElement el);

    @JSBody(params = {"name", "el", "method"}, script =
            "if (window.bootstrap && window.bootstrap[name]) {"
            + " var i = window.bootstrap[name].getOrCreateInstance(el);"
            + " if (i && typeof i[method] === 'function') { i[method](); } }")
    private static native void invoke(String name, HTMLElement el, String method);

    @JSBody(params = {"name", "el", "method", "arg"}, script =
            "if (window.bootstrap && window.bootstrap[name]) {"
            + " var i = window.bootstrap[name].getOrCreateInstance(el);"
            + " if (i && typeof i[method] === 'function') { i[method](arg); } }")
    private static native void invokeWithInt(String name, HTMLElement el, String method, int arg);

    @JSBody(params = {"el", "method"}, script =
            "if (window.bootstrap && window.bootstrap.Collapse) {"
            + " var i = window.bootstrap.Collapse.getOrCreateInstance(el, {toggle: false});"
            + " if (i && typeof i[method] === 'function') { i[method](); } }")
    private static native void invokeCollapse(HTMLElement el, String method);

    /** True when Bootstrap's JavaScript bundle is present on the page. */
    @JSBody(script = "return !!(window.bootstrap && window.bootstrap.Modal);")
    static native boolean isLoaded();
}
