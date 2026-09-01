package org.gwtbootstrap3.client.ui;

import org.gwtbootstrap3.client.ui.base.HasSize;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.constants.WellSize;
import org.gwtbootstrap3.client.ui.html.Div;

public class Well extends Div implements HasSize<WellSize> {
    public Well() {
        setStyleName(Styles.WELL);
    }

    @Override
    public WellSize getSize() {
        return WellSize.fromStyleName(getStyleName());
    }

    @Override
    public void setSize(final WellSize size) {
        StyleHelper.addUniqueEnumStyleName(this, WellSize.class, size);
    }
}
