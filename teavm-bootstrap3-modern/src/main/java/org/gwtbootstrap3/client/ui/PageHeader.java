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
import org.gwtbootstrap3.client.ui.base.HasSubText;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.html.Div;

public class PageHeader extends Div implements HasText, HasSubText {
    private String text = "";
    private String subText = "";

    public PageHeader() {
        setStyleName(Styles.PAGE_HEADER);
    }

    public PageHeader(final String text) {
        this();
        setText(text);
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(final String text) {
        this.text = text == null ? "" : text;
        render();
    }

    @Override
    public String getSubText() {
        return subText;
    }

    @Override
    public void setSubText(final String subText) {
        this.subText = subText == null ? "" : subText;
        render();
    }

    private void render() {
        final String suffix = subText.isEmpty() ? "" : " <small>" + escape(subText) + "</small>";
        getElement().setInnerHTML("<h1>" + escape(text) + suffix + "</h1>");
    }

    private static String escape(final String value) {
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
