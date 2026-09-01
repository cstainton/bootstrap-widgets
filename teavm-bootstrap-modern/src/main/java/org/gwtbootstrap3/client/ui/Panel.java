package org.gwtbootstrap3.client.ui;

import org.gwtbootstrap3.client.ui.base.HasType;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.PanelType;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.html.Div;

public class Panel extends Div implements HasType<PanelType> {
    public Panel() {
        this(PanelType.DEFAULT);
    }

    public Panel(final PanelType type) {
        setStyleName(Styles.PANEL);
        setType(type);
    }

    @Override
    public PanelType getType() {
        return PanelType.fromStyleName(getStyleName());
    }

    @Override
    public void setType(final PanelType type) {
        StyleHelper.addUniqueEnumStyleName(this, PanelType.class, type == null ? PanelType.DEFAULT : type);
    }
}
