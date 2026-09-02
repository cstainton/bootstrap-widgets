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
package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.Element;
import org.teavm.jso.JSBody;
import org.teavm.jso.dom.html.HTMLElement;

/**
 * Calls into Bootstrap's own JavaScript.
 *
 * <p>The GWT widgets reach Bootstrap through JSNI, which TeaVM cannot compile. The
 * TeaVM-side widgets call through here instead, so the JS bridge exists once rather
 * than once per widget. Every call is a no-op when Bootstrap's bundle is absent,
 * matching the guard the JSNI blocks use.</p>
 */
public final class BootstrapJs {

    private BootstrapJs() {
    }

    /** Creates the component instance for {@code element} without invoking anything. */
    public static void init(final String component, final Element element) {
        create(component, element.unwrap());
    }

    /** Invokes a no-argument method on the component instance for {@code element}. */
    public static void call(final String component, final Element element, final String method) {
        invoke(component, element.unwrap(), method);
    }

    /** Invokes a single-int-argument method, such as the carousel's {@code to}. */
    public static void call(final String component, final Element element, final String method,
            final int argument) {
        invokeWithInt(component, element.unwrap(), method, argument);
    }

    /** Creates a Collapse instance with {@code toggle: false}, as the widgets require. */
    public static void callCollapse(final Element element, final String method) {
        invokeCollapse(element.unwrap(), method);
    }

    /** Disposes the component instance for {@code element}, if one exists. */
    public static void dispose(final String component, final Element element) {
        disposeInstance(component, element.unwrap());
    }

    /** Hides every open modal except {@code current}. */
    public static void hideOtherModals(final Element current) {
        hideOthers(current.unwrap());
    }

    /** Recreates a Carousel with explicit options, as the widget's own init does. */
    public static void initCarousel(final Element element, final int interval,
            final String pause, final boolean wrap) {
        newCarousel(element.unwrap(), interval, pause, wrap);
    }

    /**
     * Creates a tooltip- or popover-family plugin with explicit options, replacing any
     * instance already on the element. Mirrors the option handling of the GWT widget's
     * own initialiser.
     */
    public static void initializePlugin(final Element element, final String pluginName,
            final boolean animation, final boolean html, final String placement,
            final String trigger, final int showDelay, final int hideDelay,
            final String container, final String selector, final String boundary,
            final int padding, final String title, final String template, final String content) {
        newPlugin(element.unwrap(), pluginName, animation, html, placement, trigger,
                showDelay, hideDelay, container, selector, boundary, padding, title,
                template, content);
    }

    /** True when Bootstrap's JavaScript bundle is present on the page. */
    public static boolean isLoaded() {
        return bootstrapPresent();
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

    @JSBody(params = {"name", "el"}, script =
            "if (window.bootstrap && window.bootstrap[name]) {"
            + " var i = window.bootstrap[name].getInstance(el);"
            + " if (i && typeof i.dispose === 'function') { i.dispose(); } }")
    private static native void disposeInstance(String name, HTMLElement el);

    @JSBody(params = {"current"}, script =
            "if (window.bootstrap && window.bootstrap.Modal) {"
            + " var open = document.querySelectorAll('.modal.show');"
            + " for (var i = 0; i < open.length; i++) {"
            + "   if (open[i] !== current) {"
            + "     var m = window.bootstrap.Modal.getInstance(open[i]);"
            + "     if (m) { m.hide(); } } } }")
    private static native void hideOthers(HTMLElement current);

    @JSBody(params = {"el", "interval", "pause", "wrap"}, script =
            "if (!window.bootstrap || !window.bootstrap.Carousel) { return; }"
            + " var existing = window.bootstrap.Carousel.getInstance(el);"
            + " if (existing) { existing.dispose(); }"
            + " new window.bootstrap.Carousel(el,"
            + "   {interval: interval, pause: pause || false, wrap: wrap});")
    private static native void newCarousel(HTMLElement el, int interval, String pause, boolean wrap);

    @JSBody(params = {"el", "pluginName", "animation", "html", "placement", "trigger",
            "showDelay", "hideDelay", "container", "selector", "boundary", "padding",
            "title", "template", "content"}, script =
            "if (!window.bootstrap || !window.bootstrap[pluginName]) { return; }"
            + " var plugin = window.bootstrap[pluginName];"
            + " var existing = plugin.getInstance(el);"
            + " if (existing) { existing.dispose(); }"
            + " var options = {animation: animation, html: html, placement: placement,"
            + "   trigger: trigger, delay: {show: showDelay, hide: hideDelay},"
            + "   title: title, template: template};"
            + " if (container) { options.container = container; }"
            + " if (selector) { options.selector = selector; }"
            + " if (boundary) { options.boundary = boundary; }"
            + " if (content != null) { options.content = content; }"
            + " if (padding > 0) {"
            + "   options.popperConfig = function(defaultConfig) {"
            + "     var modifiers = defaultConfig.modifiers || [];"
            + "     for (var i = 0; i < modifiers.length; i++) {"
            + "       if (modifiers[i].name === 'preventOverflow') {"
            + "         modifiers[i].options = modifiers[i].options || {};"
            + "         modifiers[i].options.padding = padding; } }"
            + "     return defaultConfig; }; }"
            + " new plugin(el, options);")
    private static native void newPlugin(HTMLElement el, String pluginName, boolean animation,
            boolean html, String placement, String trigger, int showDelay, int hideDelay,
            String container, String selector, String boundary, int padding, String title,
            String template, String content);

    @JSBody(script = "return !!(window.bootstrap && window.bootstrap.Modal);")
    private static native boolean bootstrapPresent();
}
