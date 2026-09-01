package org.gwtbootstrap3.client.ui.constants;

import com.google.gwt.dom.client.Style;

public enum WellSize implements Style.HasCssName {
    SMALL("p-2"), LARGE("p-5");

    private final String cssName;

    WellSize(final String cssName) {
        this.cssName = cssName;
    }

    @Override
    public String getCssName() {
        return cssName;
    }

    public static WellSize fromStyleName(final String styleName) {
        for (final WellSize value : values()) {
            if (styleName != null && styleName.contains(value.cssName)) {
                return value;
            }
        }
        return null;
    }
}
