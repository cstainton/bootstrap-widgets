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
package io.instanto.bootstrap5.extras.markdown.client;

import io.instanto.bootstrap5.client.Bootstrap5Resources;

/**
 * Loads the Markdown parser and sanitiser on the TeaVM backend.
 *
 * <p>The GWT module does this from an entry point, inlining both files through a
 * ClientBundle. TeaVM has no module system to run an entry point and no generator to
 * inline a resource, so the scripts are fetched by URL instead, which also keeps 74KB of
 * vendored JavaScript out of the compiled module.</p>
 *
 * <p>Both load asynchronously, so a panel built during startup renders before they
 * arrive. {@code MarkdownPanel} shows the source until {@link Markdown#isReady()} turns
 * true and then renders, which is why nothing here has to be awaited.</p>
 */
public final class MarkdownResources {

    private static final String MARKED = "marked-18.0.11.umd.cache.js";
    private static final String DOM_PURIFY = "dompurify-3.4.14.min.cache.js";
    private static final String ID_PREFIX = "bootstrap5-markdown-";

    private static boolean injected;

    private MarkdownResources() {
    }

    /** Injects both scripts once; further calls do nothing. */
    public static void ensureInjected() {
        if (injected) {
            return;
        }
        injected = true;
        Bootstrap5Resources.script(ID_PREFIX + "marked", Bootstrap5Resources.jsBase() + MARKED);
        Bootstrap5Resources.script(ID_PREFIX + "dompurify", Bootstrap5Resources.jsBase() + DOM_PURIFY);
    }
}
