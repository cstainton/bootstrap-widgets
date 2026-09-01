package org.gwtbootstrap3.client.ui.constants;

import com.google.gwt.dom.client.Style;

public enum ListGroupItemType implements Style.HasCssName {
    DEFAULT(""), SUCCESS("list-group-item-success"), INFO("list-group-item-info"), WARNING("list-group-item-warning"),
    DANGER("list-group-item-danger");

    private final String cssName;

    ListGroupItemType(final String cssName) {
        this.cssName = cssName;
    }

    @Override
    public String getCssName() {
        return cssName;
    }

    public static ListGroupItemType fromStyleName(final String styleName) {
        for (final ListGroupItemType value : values()) {
            if (!value.cssName.isEmpty() && styleName != null && styleName.contains(value.cssName)) {
                return value;
            }
        }
        return DEFAULT;
    }
}
