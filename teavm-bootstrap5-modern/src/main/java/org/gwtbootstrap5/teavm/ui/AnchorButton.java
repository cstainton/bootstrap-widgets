package org.gwtbootstrap5.teavm.ui;

public class AnchorButton extends Anchor {

    public AnchorButton() {
        this("");
    }

    public AnchorButton(final String text) {
        this(text, "#", Variant.PRIMARY);
    }

    public AnchorButton(final String text, final String href) {
        this(text, href, Variant.PRIMARY);
    }

    public AnchorButton(final String text, final String href, final Variant variant) {
        super(text, href);
        addStyleName("btn");
        addStyleName("btn-" + (variant == null ? Variant.PRIMARY : variant).cssName());
    }
}
