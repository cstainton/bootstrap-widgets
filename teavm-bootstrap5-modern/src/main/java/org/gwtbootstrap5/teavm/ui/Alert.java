package org.gwtbootstrap5.teavm.ui;

public class Alert extends TextWidget {

    private final Widget closeButton = new Widget("button");
    private Variant variant;
    private boolean dismissible;

    public Alert() {
        super("div");
        addStyleName("alert");
        setAttribute("role", "alert");
        closeButton.addStyleName("btn-close");
        closeButton.setAttribute("type", "button");
        closeButton.setAttribute("data-bs-dismiss", "alert");
        closeButton.setAttribute("aria-label", "Close");
        setVariant(Variant.WARNING);
    }

    public Alert(final String text) {
        this();
        setText(text);
    }

    public Alert(final String text, final Variant variant) {
        this(text);
        setVariant(variant);
    }

    public Alert setVariant(final Variant variant) {
        if (this.variant != null) {
            removeStyleName(styleName(this.variant));
        }
        this.variant = variant == null ? Variant.WARNING : variant;
        addStyleName(styleName(this.variant));
        return this;
    }

    @Override
    public Alert setText(final String text) {
        super.setText(text);
        if (dismissible) {
            getElement().appendChild(closeButton.getElement());
        }
        return this;
    }

    public Alert setDismissible(final boolean dismissible) {
        this.dismissible = dismissible;
        setStyleName("alert-dismissible", dismissible);
        setStyleName("fade", dismissible);
        setStyleName("show", dismissible);
        if (dismissible && closeButton.getParent() == null) {
            getElement().appendChild(closeButton.getElement());
        } else if (!dismissible) {
            closeButton.removeFromParent();
        }
        return this;
    }

    private String styleName(final Variant variant) {
        return "alert-" + variant.cssName();
    }
}
