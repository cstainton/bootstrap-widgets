package org.gwtbootstrap5.teavm.ui;

public class CardFooter extends TextWidget {

    public CardFooter() {
        super("div");
        addStyleName("card-footer");
    }

    public CardFooter(final String text) {
        this();
        setText(text);
    }
}
