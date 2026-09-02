package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.FlowPanel;

public class Column extends FlowPanel {

    public Column() {
        this(12);
    }

    public Column(int span) {
        addStyleName("col-" + Math.max(1, Math.min(12, span)));
    }

    public void setMediumSpan(int span) {
        addStyleName("col-md-" + Math.max(1, Math.min(12, span)));
    }
}
