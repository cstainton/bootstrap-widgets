package org.gwtbootstrap5.client.ui;

public class Badge extends ElementPanel {

    private Variant variant;

    public Badge() {
        super("span");
        setVariant(Variant.SECONDARY);
    }

    public Badge(String text) {
        this();
        setText(text);
    }

    public Badge(String text, Variant variant) {
        this(text);
        setVariant(variant);
    }

    public void setVariant(Variant variant) {
        if (this.variant != null) {
            removeStyleName(styleName(this.variant));
        }
        this.variant = variant == null ? Variant.SECONDARY : variant;
        addStyleName("badge");
        addStyleName(styleName(this.variant));
    }

    public Variant getVariant() {
        return variant;
    }

    public void setPill(boolean pill) {
        setStyleName("rounded-pill", pill);
    }

    private String styleName(Variant variant) {
        return "text-bg-" + variant.cssName();
    }
}
