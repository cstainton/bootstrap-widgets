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

import com.google.gwt.user.client.ui.HasText;
import org.gwtbootstrap3.client.ui.base.HasType;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.AlertType;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.html.Div;

public class Alert extends Div implements HasText, HasType<AlertType> {
    private boolean dismissable;

    public Alert() {
        setStyleName(Styles.ALERT);
        setType(AlertType.WARNING);
    }

    public Alert(final String text) {
        this();
        setText(text);
    }

    public Alert(final String text, final AlertType type) {
        this(text);
        setType(type);
    }

    @Override
    public String getText() {
        return getElement().getInnerText();
    }

    @Override
    public void setText(final String text) {
        getElement().setInnerText(text);
    }

    @Override
    public AlertType getType() {
        return AlertType.fromStyleName(getStyleName());
    }

    @Override
    public void setType(final AlertType type) {
        StyleHelper.addUniqueEnumStyleName(this, AlertType.class, type == null ? AlertType.WARNING : type);
    }

    public boolean isDismissable() {
        return dismissable;
    }

    public void setDismissable(final boolean dismissable) {
        this.dismissable = dismissable;
        setStyleName("alert-dismissible", dismissable);
        setStyleName("fade", dismissable);
        setStyleName("in", dismissable);
    }
}
