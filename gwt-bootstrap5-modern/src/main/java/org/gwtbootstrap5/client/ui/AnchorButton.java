package org.gwtbootstrap5.client.ui;

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
}
