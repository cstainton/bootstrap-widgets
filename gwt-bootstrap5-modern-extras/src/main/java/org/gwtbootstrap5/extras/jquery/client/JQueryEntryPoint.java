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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.core.client.Scheduler;

/**
 * Injects jQuery, then bridges Bootstrap 5's components onto it.
 *
 * <p>An application that loads jQuery from its host page or a CDN keeps the one
 * it has: injecting a second copy would replace the first and detach any plugins
 * already registered against it.</p>
 *
 * <p>The bridge is installed from a deferred command so that the core module's
 * own entry point, which injects Bootstrap's JavaScript, has run first.</p>
 */
public class JQueryEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        if (!alreadyLoaded()) {
            ScriptInjector.fromString(JQueryClientBundle.INSTANCE.jquery().getText())
                    .setWindow(ScriptInjector.TOP_WINDOW)
                    .inject();
        }
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                BootstrapJQueryBridge.install();
            }
        });
    }

    private static native boolean alreadyLoaded() /*-{
        return typeof $wnd.jQuery === "function";
    }-*/;
}
