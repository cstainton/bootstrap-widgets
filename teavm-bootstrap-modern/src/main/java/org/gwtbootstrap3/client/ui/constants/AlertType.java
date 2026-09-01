package org.gwtbootstrap3.client.ui.constants;

import com.google.gwt.dom.client.Style;

public enum AlertType implements Style.HasCssName {
    SUCCESS("alert-success"), INFO("alert-info"), WARNING("alert-warning"), DANGER("alert-danger");

    private final String cssName;

    AlertType(final String cssName) {
        this.cssName = cssName;
    }

    @Override
    public String getCssName() {
        return cssName;
    }

    public static AlertType fromStyleName(final String styleName) {
        for (final AlertType value : values()) {
            if (styleName != null && styleName.contains(value.cssName)) {
                return value;
            }
        }
        return null;
    }
}
