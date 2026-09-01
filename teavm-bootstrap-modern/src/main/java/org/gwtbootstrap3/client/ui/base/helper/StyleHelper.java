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
