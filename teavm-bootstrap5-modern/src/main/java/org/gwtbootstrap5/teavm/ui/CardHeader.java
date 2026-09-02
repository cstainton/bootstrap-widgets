package org.gwtbootstrap5.teavm.ui;

public class CardHeader extends TextWidget {

    public CardHeader() {
        super("div");
        addStyleName("card-header");
    }

    public CardHeader(final String text) {
        this();
        setText(text);
    }
}
