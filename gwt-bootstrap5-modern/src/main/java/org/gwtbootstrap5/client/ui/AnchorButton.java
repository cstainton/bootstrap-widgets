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

import org.gwtbootstrap5.client.ui.constants.ButtonType;

public class AnchorButton extends Anchor {

    public AnchorButton() {
        this("");
    }

    public AnchorButton(String text) {
        this(text, "#", Variant.PRIMARY);
    }

    public AnchorButton(String text, String href) {
        this(text, href, Variant.PRIMARY);
    }

    public AnchorButton(String text, String href, Variant variant) {
        super(text, href);
        setButtonVariant(variant);
    }

    public AnchorButton(String text, String href, ButtonType type) {
        super(text, href);
        setButtonType(type);
    }
}
