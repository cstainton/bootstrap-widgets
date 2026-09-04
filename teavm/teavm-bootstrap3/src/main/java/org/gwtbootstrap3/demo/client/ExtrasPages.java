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
package org.gwtbootstrap3.demo.client;

import com.google.gwt.user.client.ui.Widget;

/**
 * The TeaVM half of the extras seam: no extras pages.
 *
 * <p>gwt-bootstrap3-extras reaches the browser through 352 JSNI methods across 51
 * files. JSNI is compiled by GWT alone, so until those are rewritten as JSBody -- the
 * same conversion the Bootstrap 5 extras went through, at seven times the size -- the
 * fourteen pages that use them have nothing to run.</p>
 *
 * <p>Returning null rather than a placeholder is deliberate: the entry point falls
 * through to its own switch, and a token with no page is already handled there. The
 * showcase is short fourteen of its fifty-five pages, and behaves normally otherwise.</p>
 */
final class ExtrasPages {

    private ExtrasPages() {
    }

    /** Always null; no extras page is available on this backend. */
    static Widget forToken(final String token) {
        return null;
    }
}
