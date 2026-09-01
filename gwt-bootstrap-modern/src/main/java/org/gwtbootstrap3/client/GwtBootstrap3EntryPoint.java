package org.gwtbootstrap3.client;

/*
 * #%L
 * GWT Bootstrap Modern
 * %%
 * Copyright (C) 2013 - 2015 GwtBootstrap3
 * Copyright (C) 2026 GWT Bootstrap Modern contributors
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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;

/**
 * Injects Bootstrap 5 resources and a temporary jQuery compatibility bridge for legacy widgets.
 */
public class GwtBootstrap3EntryPoint implements EntryPoint {

    private native boolean isBootstrapLoaded() /*-{
        return typeof $wnd.bootstrap !== 'undefined' && typeof $wnd.bootstrap.Modal !== 'undefined';
    }-*/;

    private native boolean isBootstrapCompatibilityLoaded() /*-{
        return $wnd.gwtBootstrap3CompatibilityLoaded === true;
    }-*/;

    private native boolean isjQueryLoaded() /*-{
        return typeof $wnd.jQuery !== 'undefined';
    }-*/;

    @Override
    public void onModuleLoad() {
        if (!isjQueryLoaded()) {
            ScriptInjector.fromString(GwtBootstrap3ClientBundle.INSTANCE.jQuery().getText())
                    .setWindow(ScriptInjector.TOP_WINDOW)
                    .inject();
        }

        if (!isBootstrapLoaded()) {
            ScriptInjector.fromString(GwtBootstrap3ClientBundle.INSTANCE.bootstrap().getText())
                    .setWindow(ScriptInjector.TOP_WINDOW)
                    .inject();
        }

        if (!isBootstrapCompatibilityLoaded()) {
            ScriptInjector.fromString(GwtBootstrap3ClientBundle.INSTANCE.bootstrapCompatibility().getText())
                    .setWindow(ScriptInjector.TOP_WINDOW)
                    .inject();
        }
    }
}
