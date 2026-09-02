package org.gwtbootstrap5.teavm.ui;

public class Heading extends TextWidget {

    public Heading(final int level) {
        super("h" + normalize(level));
    }

    public Heading(final int level, final String text) {
        this(level);
        setText(text);
    }

    private static int normalize(final int level) {
        if (level < 1 || level > 6) {
            throw new IllegalArgumentException("heading level must be between 1 and 6");
        }
        return level;
    }
}
