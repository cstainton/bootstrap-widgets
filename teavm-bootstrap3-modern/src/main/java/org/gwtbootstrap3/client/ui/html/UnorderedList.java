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
package org.gwtbootstrap3.client.ui.html;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.ListItem;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.Styles;

public class UnorderedList extends Div {
    public UnorderedList() {
        setElement(Document.get().createULElement());
    }

    public UnorderedList(final ListItem... widgets) {
        this();
        for (final ListItem item : widgets) {
            add(item);
        }
    }

    public void setUnstyled(final boolean unstyled) {
        setStyleName(Styles.UNSTYLED, unstyled);
    }

    public boolean isUnstyled() {
        return StyleHelper.containsStyle(Styles.UNSTYLED, getStyleName());
    }

    public void setInline(final boolean inline) {
        setStyleName(Styles.LIST_INLINE, inline);
    }

    public boolean isInline() {
        return StyleHelper.containsStyle(Styles.LIST_INLINE, getStyleName());
    }
}
