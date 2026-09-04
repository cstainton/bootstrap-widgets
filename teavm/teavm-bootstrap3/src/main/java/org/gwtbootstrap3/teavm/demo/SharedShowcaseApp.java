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
package org.gwtbootstrap3.teavm.demo;

import org.gwtbootstrap3.client.Bootstrap3Resources;
import org.gwtbootstrap3.demo.client.GwtBootstrap3DemoEntryPoint;

/**
 * The GWT showcase, run on TeaVM.
 *
 * <p>Not a port: this is the same GwtBootstrap3DemoEntryPoint the GWT build compiles,
 * reached through the same onModuleLoad GWT calls, with its pages built from the same
 * UiBinder templates. All this class supplies is what GWT's module system would have --
 * where the assets live, and the call to start.</p>
 *
 * <p>Forty-one of the fifty-five pages are here. The rest need gwt-bootstrap3-extras,
 * which is not ported; ExtrasPages is where that shows.</p>
 */
public final class SharedShowcaseApp {

    private SharedShowcaseApp() {
    }

    public static void main(final String[] args) {
        // The default, css/, is where the site serves the library's stylesheets;
        // stated rather than assumed, since this is the GWT module's job otherwise.
        Bootstrap3Resources.setBase("css/");
        new GwtBootstrap3DemoEntryPoint().onModuleLoad();
    }
}
