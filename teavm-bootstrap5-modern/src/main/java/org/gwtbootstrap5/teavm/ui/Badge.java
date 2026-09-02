package org.gwtbootstrap5.teavm.ui;

public class Badge extends TextWidget {

    private Variant variant;

    public Badge() {
        super("span");
        addStyleName("badge");
        setVariant(Variant.SECONDARY);
    }

    public Badge(final String text) {
        this();
        setText(text);
    }

    public Badge(final String text, final Variant variant) {
        this(text);
        setVariant(variant);
    }

    public Badge setVariant(final Variant variant) {
        if (this.variant != null) {
            removeStyleName(styleName(this.variant));
        }
        this.variant = variant == null ? Variant.SECONDARY : variant;
        addStyleName(styleName(this.variant));
        return this;
    }

    public Badge setPill(final boolean pill) {
        setStyleName("rounded-pill", pill);
        return this;
    }

    private String styleName(final Variant variant) {
        return "text-bg-" + variant.cssName();
    }
}
