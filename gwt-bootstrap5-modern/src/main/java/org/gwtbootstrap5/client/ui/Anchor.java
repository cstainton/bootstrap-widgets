package org.gwtbootstrap5.client.ui;

public class Anchor extends com.google.gwt.user.client.ui.Anchor {

    private Variant buttonVariant;
    private boolean outline;

    public Anchor() {
        super();
    }

    public Anchor(String text) {
        super(text);
    }

    public Anchor(String text, String href) {
        super(text, href);
    }

    public void setButtonVariant(Variant variant) {
        if (buttonVariant != null) {
            removeStyleName(buttonStyle(buttonVariant, outline));
        }
        buttonVariant = variant == null ? Variant.PRIMARY : variant;
        addStyleName("btn");
        addStyleName(buttonStyle(buttonVariant, outline));
    }

    public void setOutline(boolean outline) {
        if (this.outline != outline) {
            if (buttonVariant != null) {
                removeStyleName(buttonStyle(buttonVariant, this.outline));
            }
            this.outline = outline;
            if (buttonVariant != null) {
                addStyleName(buttonStyle(buttonVariant, this.outline));
            }
        }
    }

    private String buttonStyle(Variant variant, boolean outline) {
        return outline ? "btn-outline-" + variant.cssName() : "btn-" + variant.cssName();
    }
}
