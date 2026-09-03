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
package com.google.gwt.user.client;

/**
 * History-token encoding.
 *
 * <p>The widget library uses exactly one thing from GWT's History: token encoding,
 * for {@code setTargetHistoryToken()} on Anchor and LinkedGroupItem. Navigation,
 * back/forward and history events belong to the application's routing layer, not to
 * a widget library, so they are deliberately not emulated here.</p>
 */
public final class History {

    private History() {
    }

    /**
     * Escapes the characters that are not legal in a URL fragment. Mirrors GWT's
     * encoding: {@code ?}, {@code #}, {@code &}, {@code ;}, {@code +} are escaped,
     * everything else is left alone so tokens stay readable.
     */
    public static String encodeHistoryToken(final String historyToken) {
        if (historyToken == null) {
            return "";
        }
        return historyToken
                .replace("?", "%3F")
                .replace("#", "%23")
                .replace("&", "%26")
                .replace(";", "%3B")
                .replace("+", "%2B");
    }

    /** Sets the history token, which for a hash-based app navigates the page. */
    public static void newItem(final String token) {
        newItem(token, true);
    }

    public static void newItem(final String token, final boolean issueEvent) {
        setHash(token == null ? "" : token);
    }

    public static String getToken() {
        final String hash = currentHash();
        return hash == null || hash.isEmpty() ? "" : hash.substring(1);
    }

    @org.teavm.jso.JSBody(params = {"t"}, script = "window.location.hash = t;")
    private static native void setHash(String t);

    @org.teavm.jso.JSBody(script = "return window.location.hash;")
    private static native String currentHash();
}
