package org.gwtbootstrap3.client.ui;

import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.constants.ColumnSize;
import org.gwtbootstrap3.client.ui.html.Div;

public class Column extends Div {
    public Column(final ColumnSize size) {
        addStyleName(size.getCssName());
    }

    public Column(final ColumnSize size, final Widget firstWidget, final Widget... otherWidgets) {
        this(size);
        add(firstWidget);
        for (final Widget widget : otherWidgets) {
            add(widget);
        }
    }

    public void addSize(final ColumnSize... sizes) {
        for (final ColumnSize size : sizes) {
            addStyleName(size.getCssName());
        }
    }
}
