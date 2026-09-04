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
package io.instanto.bootstrap5.extras.richtext.client;

import io.instanto.bootstrap5.client.Bootstrap5Resources;

/**
 * Loads Quill on the TeaVM backend, by URL rather than inlined. See
 * {@code MarkdownResources} for the reasoning.
 */
public final class RichTextResources {

    private static final String SCRIPT = "quill-2.0.3.min.cache.js";
    private static final String STYLESHEET = "quill-snow-2.0.3.cache.css";
    private static final String ID_PREFIX = "bootstrap5-richtext-";

    private static boolean injected;

    private RichTextResources() {
    }

    /** Injects the script and stylesheet once; further calls do nothing. */
    public static void ensureInjected() {
        if (injected) {
            return;
        }
        injected = true;
        Bootstrap5Resources.stylesheet(ID_PREFIX + "css", Bootstrap5Resources.cssBase() + STYLESHEET);
        Bootstrap5Resources.script(ID_PREFIX + "js", Bootstrap5Resources.jsBase() + SCRIPT);
    }
}
