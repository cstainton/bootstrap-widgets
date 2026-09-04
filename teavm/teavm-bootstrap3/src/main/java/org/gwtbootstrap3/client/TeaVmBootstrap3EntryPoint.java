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
package org.gwtbootstrap3.client;

import com.google.gwt.core.client.EntryPoint;
import org.teavm.jso.JSBody;

/**
 * TeaVM entry point for the Bootstrap 3 widget set.
 *
 * <p>The GWT entry point injects jQuery and Bootstrap from a {@code ClientBundle}, whose
 * generator has no TeaVM equivalent. Here both are expected on the host page, which is
 * how a TeaVM application loads them; the check is kept so a missing dependency is
 * reported rather than failing obscurely on first use.</p>
 */
public class TeaVmBootstrap3EntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        if (!isJQueryLoaded()) {
            warn("gwtbootstrap3: jQuery is not on the page; interactive widgets will not work.");
        } else if (!isBootstrapLoaded()) {
            warn("gwtbootstrap3: Bootstrap 3's JavaScript is not on the page; "
                    + "modals, tooltips and other plugin-backed widgets will not work.");
        }
    }

    /** True when jQuery is present. */
    public static boolean isJQueryLoaded() {
        return jQueryPresent();
    }

    /** True when Bootstrap 3's jQuery plugins are present. */
    public static boolean isBootstrapLoaded() {
        return bootstrapPresent();
    }

    @JSBody(script = "return typeof window.jQuery !== 'undefined';")
    private static native boolean jQueryPresent();

    @JSBody(script = "return typeof window.jQuery !== 'undefined'"
            + " && typeof window.jQuery.fn.modal !== 'undefined';")
    private static native boolean bootstrapPresent();

    @JSBody(params = {"msg"}, script = "if (window.console) { console.warn(msg); }")
    private static native void warn(String msg);
}
