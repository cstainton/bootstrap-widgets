package org.gwtbootstrap5.teavm.ui;

public class Anchor extends TextWidget {

    public Anchor() {
        super("a");
    }

    public Anchor(final String text, final String href) {
        this();
        setText(text);
        setHref(href);
    }

    public Anchor setHref(final String href) {
        setAttribute("href", href);
        return this;
    }
}
