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
package org.gwtbootstrap5.extras.jquery.client;

/**
 * Registers Bootstrap 5's components as jQuery plugins.
 *
 * <p>Bootstrap 3 and 4 shipped a jQuery bridge, so a plugin could write
 * {@code $(el).modal("show")}. Bootstrap 5 removed it: the components are only
 * reachable as {@code bootstrap.Modal} and friends. Third-party plugins written
 * against the old surface therefore fail on Bootstrap 5, Bootbox among them --
 * it refuses to run with "$.fn.modal is not defined".</p>
 *
 * <p>This installs the missing bridge. Each {@code $.fn.<name>} forwards to the
 * matching Bootstrap 5 class: an options object constructs the component, a
 * string invokes that method on it, and no argument constructs it with
 * defaults. {@code Constructor.VERSION} is set too, because plugins read it to
 * decide which Bootstrap they are talking to.</p>
 *
 * <p>Installed by {@link JQueryEntryPoint}, so any extra inheriting the jQuery
 * module gets it. It is idempotent and never overwrites a bridge that is
 * already there.</p>
 */
public final class BootstrapJQueryBridge {

    private BootstrapJQueryBridge() {
    }

    /** Installs the bridge if jQuery and Bootstrap are both present and it is absent. */
    public static native void install() /*-{
        var $ = $wnd.jQuery;
        var bootstrap = $wnd.bootstrap;
        if (!$ || !$.fn || !bootstrap || !bootstrap.Modal) {
            return;
        }

        var version = bootstrap.Modal.VERSION || "5.3.8";
        var components = ["Alert", "Button", "Carousel", "Collapse", "Dropdown", "Modal",
                          "Offcanvas", "Popover", "ScrollSpy", "Tab", "Toast", "Tooltip"];

        for (var i = 0; i < components.length; i++) {
            var name = components[i];
            var Component = bootstrap[name];
            if (!Component) {
                continue;
            }
            var pluginName = name.charAt(0).toLowerCase() + name.slice(1);
            if ($.fn[pluginName]) {
                continue;
            }
            $.fn[pluginName] = (function (Ctor) {
                return function (config) {
                    var args = Array.prototype.slice.call(arguments, 1);
                    var result = this;
                    this.each(function () {
                        var instance = Ctor.getOrCreateInstance(this,
                                typeof config === "object" ? config : {});
                        if (typeof config === "string") {
                            if (typeof instance[config] !== "function") {
                                throw new TypeError("No method named \"" + config + "\"");
                            }
                            var value = instance[config].apply(instance, args);
                            if (value !== undefined && result === this) {
                                result = value;
                            }
                        }
                    });
                    return result;
                };
            })(Component);
            $.fn[pluginName].Constructor = Component;
            $.fn[pluginName].Constructor.VERSION = version;
        }
    }-*/;

    /** Whether the bridge is in place. */
    public static native boolean isInstalled() /*-{
        return !!($wnd.jQuery && $wnd.jQuery.fn && $wnd.jQuery.fn.modal);
    }-*/;
}
