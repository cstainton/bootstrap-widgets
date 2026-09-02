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
package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;
import org.gwtbootstrap3.client.ui.constants.Styles;

public class FormLabel extends AbstractTextWidget {
    private boolean showRequiredIndicator;

    public FormLabel() {
        super(Document.get().createLabelElement());
        setStyleName(Styles.CONTROL_LABEL);
    }

    public void setFor(final String id) {
        if (id == null) {
            getElement().removeAttribute("for");
        } else {
            getElement().setAttribute("for", id);
        }
    }

    public boolean getShowRequiredIndicator() {
        return showRequiredIndicator;
    }

    public void setShowRequiredIndicator(final boolean showRequiredIndicator) {
        this.showRequiredIndicator = showRequiredIndicator;
    }
}
