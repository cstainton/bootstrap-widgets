package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.HasType;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.ProgressBarType;

public class ProgressBar extends ElementPanel implements HasType<ProgressBarType> {

    private Variant variant;

    public ProgressBar() {
        this(0);
    }

    public ProgressBar(int value) {
        super("div");
        addStyleName("progress-bar");
        getElement().setAttribute("role", "progressbar");
        setValue(value);
    }

    public ProgressBar(int value, ProgressBarType type) {
        this(value);
        setType(type);
    }

    public void setValue(int value) {
        int clamped = Math.max(0, Math.min(100, value));
        getElement().getStyle().setProperty("width", clamped + "%");
        getElement().setAttribute("aria-valuenow", String.valueOf(clamped));
        getElement().setAttribute("aria-valuemin", "0");
        getElement().setAttribute("aria-valuemax", "100");
        setText(clamped + "%");
    }

    public void setPercent(double percent) {
        double clamped = Math.max(0, Math.min(100, percent));
        getElement().getStyle().setProperty("width", clamped + "%");
        getElement().setAttribute("aria-valuenow", String.valueOf(clamped));
        getElement().setAttribute("aria-valuemin", "0");
        getElement().setAttribute("aria-valuemax", "100");
    }

    public double getPercent() {
        String width = getElement().getStyle().getWidth();
        if (width == null || !width.endsWith("%")) {
            return 0;
        }
        return Double.valueOf(width.substring(0, width.length() - 1));
    }

    public void setVariant(Variant variant) {
        if (this.variant != null) {
            removeStyleName("bg-" + this.variant.cssName());
        }
        this.variant = variant;
        if (variant != null) {
            addStyleName("bg-" + variant.cssName());
        }
    }

    public Variant getVariant() {
        return variant;
    }

    @Override
    public void setType(ProgressBarType type) {
        StyleHelper.addUniqueEnumStyleName(this, ProgressBarType.class, type == null ? ProgressBarType.DEFAULT : type);
    }

    @Override
    public ProgressBarType getType() {
        return ProgressBarType.fromStyleName(getStyleName());
    }

    public void setSrOnly(boolean srOnly) {
        setStyleName("visually-hidden", srOnly);
    }

    public void setStriped(boolean striped) {
        setStyleName("progress-bar-striped", striped);
    }

    public void setAnimated(boolean animated) {
        setStyleName("progress-bar-animated", animated);
    }
}
