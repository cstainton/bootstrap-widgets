package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.Style;

public class InlineHelpBlock extends HelpBlock {

    public InlineHelpBlock() {
        this("");
    }

    public InlineHelpBlock(String text) {
        super(text);
        Style style = getElement().getStyle();
        style.setDisplay(Style.Display.INLINE_BLOCK);
        style.setMarginTop(0, Style.Unit.PX);
        style.setMarginBottom(0, Style.Unit.PX);
        style.setPaddingLeft(10, Style.Unit.PX);
    }
}
