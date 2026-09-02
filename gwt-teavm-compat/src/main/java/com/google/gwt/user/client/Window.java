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
package com.google.gwt.user.client;


/** Browser window geometry and scrolling. */
public final class Window {

    private Window() {
    }

    public static int getClientWidth() {
        return clientWidth();
    }

    public static int getClientHeight() {
        return clientHeight();
    }

    public static int getScrollLeft() {
        return scrollLeft();
    }

    public static int getScrollTop() {
        return scrollTop();
    }

    public static void alert(final String message) {
        org.teavm.jso.browser.Window.alert(message);
    }

    @org.teavm.jso.JSBody(script = "return document.documentElement.clientWidth | 0;")
    private static native int clientWidth();

    @org.teavm.jso.JSBody(script = "return document.documentElement.clientHeight | 0;")
    private static native int clientHeight();

    @org.teavm.jso.JSBody(script = "return (window.pageXOffset || 0) | 0;")
    private static native int scrollLeft();

    @org.teavm.jso.JSBody(script = "return (window.pageYOffset || 0) | 0;")
    private static native int scrollTop();
}
