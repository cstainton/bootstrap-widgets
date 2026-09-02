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
package org.gwtbootstrap5.client.ui;

public class Heading extends ElementPanel {

    public Heading(int size) {
        super("h" + clamp(size));
    }

    public Heading(int size, String text) {
        this(size);
        setText(text);
    }

    public Heading(HeadingSize size) {
        this(size == null ? 1 : size.size());
    }

    public Heading(HeadingSize size, String text) {
        this(size);
        setText(text);
    }

    private static int clamp(int size) {
        return Math.max(1, Math.min(6, size));
    }
}
