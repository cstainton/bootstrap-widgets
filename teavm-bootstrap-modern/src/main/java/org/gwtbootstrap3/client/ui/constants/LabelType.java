package org.gwtbootstrap3.client.ui.constants;

import com.google.gwt.dom.client.Style;

public enum LabelType implements Style.HasCssName {
    DEFAULT("text-bg-secondary"), PRIMARY("text-bg-primary"), SUCCESS("text-bg-success"), INFO("text-bg-info"),
    WARNING("text-bg-warning"), DANGER("text-bg-danger");

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
