package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.HasType;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.PanelType;

public class Panel extends ElementPanel implements HasType<PanelType> {

    private Variant variant;

    public Panel() {
        super("div");
        addStyleName("card");
    }

    public Panel(Variant variant) {
        this();
        setVariant(variant);
    }

    public Panel(PanelType type) {
        this();
        setType(type);
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

    @Override
    public void setType(PanelType type) {
        StyleHelper.addUniqueEnumStyleName(this, PanelType.class, type == null ? PanelType.DEFAULT : type);
    }

    @Override
    public PanelType getType() {
        return PanelType.fromStyleName(getStyleName());
    }

    private String styleName(Variant variant) {
        return "border-" + variant.cssName();
    }
}
