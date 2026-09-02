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
