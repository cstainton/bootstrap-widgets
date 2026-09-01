package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;

public class InlineHelpBlock extends HelpBlock {
    public InlineHelpBlock() {
        getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
        getElement().getStyle().setMarginTop(0, Unit.PX);
        getElement().getStyle().setMarginBottom(0, Unit.PX);
        getElement().getStyle().setPaddingLeft(10, Unit.PX);
    }
}
