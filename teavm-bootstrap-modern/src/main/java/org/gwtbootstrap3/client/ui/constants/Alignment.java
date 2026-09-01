package org.gwtbootstrap3.client.ui.constants;

import com.google.gwt.dom.client.Style;

public enum Alignment implements Style.HasCssName {
    LEFT("text-start"), CENTER("text-center"), RIGHT("text-end"), JUSTIFIED("text-justify");

    private final String cssName;

    Alignment(final String cssName) {
        this.cssName = cssName;
    }

    @Override
    public String getCssName() {
        return cssName;
    }

    public static Alignment fromStyleName(final String styleName) {
        for (final Alignment value : values()) {
            if (styleName != null && styleName.contains(value.cssName)) {
                return value;
            }
        }
        return null;
    }
}
