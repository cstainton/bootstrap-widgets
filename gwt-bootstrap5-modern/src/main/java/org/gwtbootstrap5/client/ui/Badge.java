package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.HasType;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.LabelType;

public class Badge extends ElementPanel implements HasType<LabelType> {

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

    public Badge(String text, LabelType type) {
        this(text);
        setType(type);
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

    @Override
    public void setType(LabelType type) {
        StyleHelper.addUniqueEnumStyleName(this, LabelType.class, type == null ? LabelType.DEFAULT : type);
    }

    @Override
    public LabelType getType() {
        return LabelType.fromStyleName(getStyleName());
    }

    public void setPill(boolean pill) {
        setStyleName("rounded-pill", pill);
    }

    private String styleName(Variant variant) {
        return "text-bg-" + variant.cssName();
    }
}
