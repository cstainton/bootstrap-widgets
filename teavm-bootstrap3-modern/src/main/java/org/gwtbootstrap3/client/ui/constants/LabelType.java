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
package org.gwtbootstrap3.client.ui.constants;

import com.google.gwt.dom.client.Style;

public enum LabelType implements Style.HasCssName {
    DEFAULT("label-default"), PRIMARY("label-primary"), SUCCESS("label-success"), INFO("label-info"),
    WARNING("label-warning"), DANGER("label-danger");

    private final String cssName;

    LabelType(final String cssName) {
        this.cssName = cssName;
    }

    @Override
    public String getCssName() {
        return cssName;
    }

    public static LabelType fromStyleName(final String styleName) {
        for (final LabelType value : values()) {
            if (styleName != null && styleName.contains(value.cssName)) {
                return value;
            }
        }
        return null;
    }
}
