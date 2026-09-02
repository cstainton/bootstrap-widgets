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
package org.gwtbootstrap3.client.ui.base.helper;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Widget;

public final class StyleHelper {
    private StyleHelper() {
    }

    public static boolean containsStyle(final String style, final String styleName) {
        if (style == null || styleName == null) {
            return false;
        }
        for (final String part : styleName.split("\\s+")) {
            if (style.equals(part)) {
                return true;
            }
        }
        return false;
    }

    public static void toggleStyleName(final Widget widget, final boolean enabled, final String styleName) {
        if (enabled) {
            widget.addStyleName(styleName);
        } else {
            widget.removeStyleName(styleName);
        }
    }

    public static <E extends Enum<E> & Style.HasCssName> void addUniqueEnumStyleName(final Widget widget,
            final Class<E> enumClass, final E value) {
        for (final E candidate : enumClass.getEnumConstants()) {
            widget.removeStyleName(candidate.getCssName());
        }
        if (value != null) {
            widget.addStyleName(value.getCssName());
        }
    }
}
