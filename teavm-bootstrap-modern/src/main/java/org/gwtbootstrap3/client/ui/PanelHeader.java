package org.gwtbootstrap3.client.ui;

import com.google.gwt.user.client.ui.HasText;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.html.Div;

public class PanelHeader extends Div implements HasText {
    public PanelHeader() {
        setStyleName(Styles.PANEL_HEADING);
    }

    public PanelHeader(final String text) {
        this();
        setText(text);
    }

    @Override
    public String getText() {
        return getElement().getInnerText();
    }

    @Override
    public void setText(final String text) {
        getElement().setInnerText(text);
    }
}
