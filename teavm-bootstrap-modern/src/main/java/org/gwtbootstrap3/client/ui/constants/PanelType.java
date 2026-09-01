package org.gwtbootstrap3.client.ui.constants;

import com.google.gwt.dom.client.Style;

public enum PanelType implements Style.HasCssName {
    DEFAULT("border-secondary"), PRIMARY("border-primary"), SUCCESS("border-success"), INFO("border-info"),
    WARNING("border-warning"), DANGER("border-danger");

    private final String cssName;

    PanelType(final String cssName) {
        this.cssName = cssName;
    }

    @Override
    public String getCssName() {
        return cssName;
    }

    public static PanelType fromStyleName(final String styleName) {
        for (final PanelType value : values()) {
            if (styleName != null && styleName.contains(value.cssName)) {
                return value;
            }
        }
        return null;
    }
}
