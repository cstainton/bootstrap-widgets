package org.gwtbootstrap3.client.ui.constants;

import com.google.gwt.dom.client.Style;

public enum ButtonType implements Style.HasCssName {
    DEFAULT("btn-default"),
    PRIMARY("btn-primary"),
    SUCCESS("btn-success"),
    INFO("btn-info"),
    WARNING("btn-warning"),
    DANGER("btn-danger"),
    LINK("btn-link");

    private final String cssName;

    ButtonType(final String cssName) {
        this.cssName = cssName;
    }

    @Override
    public String getCssName() {
        return cssName;
    }
}
