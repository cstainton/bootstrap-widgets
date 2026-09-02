package org.gwtbootstrap5.teavm.ui;

public enum Variant {
    PRIMARY("primary"),
    SECONDARY("secondary"),
    SUCCESS("success"),
    DANGER("danger"),
    WARNING("warning"),
    INFO("info"),
    LIGHT("light"),
    DARK("dark"),
    LINK("link");

    private final String cssName;

    Variant(final String cssName) {
        this.cssName = cssName;
    }

    public String cssName() {
        return cssName;
    }
}
