/*
 * #%L
 * GWT Bootstrap Modern
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
package org.gwtbootstrap3.teavm.ui;

public class Heading extends TextWidget {

    public Heading(final int level) {
        super("h" + normalize(level));
    }

    public Heading(final int level, final String text) {
        this(level);
        setText(text);
    }

    private static int normalize(final int level) {
        if (level < 1 || level > 6) {
            throw new IllegalArgumentException("heading level must be between 1 and 6");
        }
        return level;
    }
}
