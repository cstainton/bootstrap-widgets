package org.gwtbootstrap5.client.ui;

public class Panel extends ElementPanel {

    private Variant variant;

    public Panel() {
        super("div");
        addStyleName("card");
    }

    public Panel(Variant variant) {
        this();
        setVariant(variant);
    }

    public void setVariant(Variant variant) {
        if (this.variant != null) {
            removeStyleName(styleName(this.variant));
        }
        this.variant = variant;
        if (variant != null) {
            addStyleName(styleName(variant));
        }
    }

    public Variant getVariant() {
        return variant;
    }

    public void setType(Variant variant) {
        setVariant(variant);
    }

    public Variant getType() {
        return getVariant();
    }

    private String styleName(Variant variant) {
        return "border-" + variant.cssName();
    }
}
