/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2018 GwtBootstrap3
 * %%
 * Modified from the GwtBootstrap3 original for the TeaVM track of GWT Bootstrap
 * Modern. Identical to the Bootstrap 5 widget of the same name in package, API
 * and behaviour; it exists separately only because that widget reaches
 * Bootstrap's JavaScript through JSNI, which TeaVM cannot compile. The calls go
 * through BootstrapJs instead. If the JSNI moves behind a shared interface, this
 * file collapses back into the one definition.
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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;
import org.teavm.jso.JSBody;

/**
 * TeaVM port of the Bootstrap 5 entry point.
 *
 * <p>Same contract as the GWT entry point: if the host page has not already supplied
 * Bootstrap's JavaScript, inject it. The GWT build reads the script from a
 * {@code ClientBundle}, whose generator has no TeaVM equivalent; here the script is
 * expected on the page, and injection from a bundle is available once a bundle
 * implementation is registered with {@code GWT.register}.</p>
 */
public class TeaVmBootstrap5EntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        if (isBootstrapLoaded()) {
            return;
        }
        final String script = bundledScript();
        if (script != null && !script.isEmpty()) {
            ScriptInjector.fromString(script).inject();
        }
    }

    /**
     * The Bootstrap bundle source, when a {@link GwtBootstrap5ClientBundle} has been
     * registered. Returns null when none is, in which case the host page is expected
     * to have loaded Bootstrap itself.
     */
    private String bundledScript() {
        try {
            return GwtBootstrap5ClientBundle.INSTANCE.bootstrap().getText();
        } catch (final RuntimeException notRegistered) {
            return null;
        }
    }

    @JSBody(script = "return !!(window.bootstrap && window.bootstrap.Modal);")
    private static native boolean isBootstrapLoaded();
}
