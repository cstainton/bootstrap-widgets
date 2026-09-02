package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class Breadcrumbs extends ElementPanel {

    public Breadcrumbs() {
        super("ol");
        addStyleName("breadcrumb");
    }

    public Breadcrumbs(Widget... widgets) {
        this();
        for (Widget widget : widgets) {
            add(widget);
        }
    }

    @Override
    public void add(Widget child) {
        child.addStyleName("breadcrumb-item");
        super.add(child);
    }
}
