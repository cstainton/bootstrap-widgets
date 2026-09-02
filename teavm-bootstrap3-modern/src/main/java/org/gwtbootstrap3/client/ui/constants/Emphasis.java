package org.gwtbootstrap3.client.ui.constants;

import com.google.gwt.dom.client.Style;

public enum Emphasis implements Style.HasCssName {
    MUTED("text-muted"), PRIMARY("text-primary"), SUCCESS("text-success"), INFO("text-info"), WARNING("text-warning"),
    DANGER("text-danger");

    private final String cssName;

    Emphasis(final String cssName) {
        this.cssName = cssName;
    }

    @Override
    public String getCssName() {
        return cssName;
    }

    public static Emphasis fromStyleName(final String styleName) {
        for (final Emphasis value : values()) {
            if (styleName != null && styleName.contains(value.cssName)) {
                return value;
            }
        }
        return null;
    }
}
