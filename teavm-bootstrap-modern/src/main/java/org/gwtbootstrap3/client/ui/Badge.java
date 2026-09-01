package org.gwtbootstrap3.client.ui;

import org.gwtbootstrap3.client.ui.constants.Styles;

public class Badge extends Label {
    public Badge() {
        super();
        setStyleName(Styles.BADGE + " text-bg-secondary");
    }

    public Badge(final String text) {
        this();
        setText(text);
    }
}
