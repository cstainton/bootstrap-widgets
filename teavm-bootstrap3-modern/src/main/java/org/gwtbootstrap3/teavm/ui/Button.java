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

public class Button extends TextWidget {

    public Button() {
        super("button");
        setAttribute("type", "button");
        addStyleName("btn");
        addStyleName("btn-primary");
    }

    public Button(final String text) {
        this();
        setText(text);
    }

    public Button setEnabled(final boolean enabled) {
        if (enabled) {
            getElement().removeAttribute("disabled");
        } else {
            getElement().setAttribute("disabled", "disabled");
        }
        return this;
    }

    public Button setButtonStyle(final String bootstrapButtonClass) {
        setStyleName("btn " + bootstrapButtonClass);
        return this;
    }
}
