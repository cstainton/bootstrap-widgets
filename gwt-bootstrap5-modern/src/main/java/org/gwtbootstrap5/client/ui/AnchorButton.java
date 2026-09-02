package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.constants.ButtonType;

public class AnchorButton extends Anchor {

    public AnchorButton() {
        this("");
    }

    public AnchorButton(String text) {
        this(text, "#", Variant.PRIMARY);
    }

    public AnchorButton(String text, String href) {
        this(text, href, Variant.PRIMARY);
    }

    public AnchorButton(String text, String href, Variant variant) {
        super(text, href);
        setButtonVariant(variant);
    }

    public AnchorButton(String text, String href, ButtonType type) {
        super(text, href);
        setButtonType(type);
    }
}
