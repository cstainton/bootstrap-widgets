package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.constants.LabelType;

/**
 * Bootstrap 5 removed labels; the equivalent visual primitive is a badge.
 */
public class Label extends Badge {

    public Label() {
        super();
    }

    public Label(String text) {
        super(text);
    }

    public Label(String text, Variant variant) {
        super(text, variant);
    }

    public Label(String text, LabelType type) {
        super(text, type);
    }
}
