package org.gwtbootstrap5.client.ui;

public class Heading extends ElementPanel {

    public Heading(int size) {
        super("h" + clamp(size));
    }

    public Heading(int size, String text) {
        this(size);
        setText(text);
    }

    public Heading(HeadingSize size) {
        this(size == null ? 1 : size.size());
    }

    public Heading(HeadingSize size, String text) {
        this(size);
        setText(text);
    }

    private static int clamp(int size) {
        return Math.max(1, Math.min(6, size));
    }
}
