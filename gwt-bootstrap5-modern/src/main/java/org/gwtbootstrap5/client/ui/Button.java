package org.gwtbootstrap5.client.ui;

public class Button extends com.google.gwt.user.client.ui.Button {

    private Variant variant;
    private boolean outline;

    public Button() {
        this("", Variant.PRIMARY);
    }

    public Button(String text) {
        this(text, Variant.PRIMARY);
    }

    public Button(String text, Variant variant) {
        super(text);
        setVariant(variant);
    }

    public void setVariant(Variant variant) {
        if (this.variant != null) {
            removeStyleName(styleName(this.variant, outline));
        }
        this.variant = variant == null ? Variant.PRIMARY : variant;
        addStyleName("btn");
        addStyleName(styleName(this.variant, outline));
    }

    public void setOutline(boolean outline) {
        if (this.outline != outline) {
            removeStyleName(styleName(variant, this.outline));
            this.outline = outline;
            addStyleName(styleName(variant, this.outline));
        }
    }

    public void setLarge(boolean large) {
        setStyleName("btn-lg", large);
    }

    public void setSmall(boolean small) {
        setStyleName("btn-sm", small);
    }

    private String styleName(Variant variant, boolean outline) {
        Variant effectiveVariant = variant == null ? Variant.PRIMARY : variant;
        return outline ? "btn-outline-" + effectiveVariant.cssName() : "btn-" + effectiveVariant.cssName();
    }
}
