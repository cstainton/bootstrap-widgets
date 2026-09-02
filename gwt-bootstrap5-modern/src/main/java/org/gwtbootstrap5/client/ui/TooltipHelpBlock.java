package org.gwtbootstrap5.client.ui;

public class TooltipHelpBlock extends HelpBlock {

    public TooltipHelpBlock() {
        this("");
    }

    public TooltipHelpBlock(String text) {
        super(text);
        getElement().setAttribute("data-bs-toggle", "tooltip");
        getElement().setAttribute("title", text == null ? "" : text);
    }
}
