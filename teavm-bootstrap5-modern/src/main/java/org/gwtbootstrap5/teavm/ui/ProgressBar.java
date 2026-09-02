package org.gwtbootstrap5.teavm.ui;

public class ProgressBar extends TextWidget {

    private Variant variant;

    public ProgressBar() {
        this(0);
    }

    public ProgressBar(final int value) {
        super("div");
        addStyleName("progress-bar");
        setAttribute("role", "progressbar");
        setValue(value);
    }

    public ProgressBar setValue(final int value) {
        final int clamped = Math.max(0, Math.min(100, value));
        getElement().getStyle().setProperty("width", clamped + "%");
        setAttribute("aria-valuenow", String.valueOf(clamped));
        setAttribute("aria-valuemin", "0");
        setAttribute("aria-valuemax", "100");
        setText(clamped + "%");
        return this;
    }

    public ProgressBar setVariant(final Variant variant) {
        if (this.variant != null) {
            removeStyleName("bg-" + this.variant.cssName());
        }
        this.variant = variant;
        if (variant != null) {
            addStyleName("bg-" + variant.cssName());
        }
        return this;
    }

    public ProgressBar setStriped(final boolean striped) {
        setStyleName("progress-bar-striped", striped);
        return this;
    }

    public ProgressBar setAnimated(final boolean animated) {
        setStyleName("progress-bar-animated", animated);
        return this;
    }
}
