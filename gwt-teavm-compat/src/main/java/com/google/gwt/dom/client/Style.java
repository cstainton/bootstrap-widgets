package com.google.gwt.dom.client;

import org.teavm.jso.dom.html.HTMLElement;

public final class Style {
    public enum Unit {
        PX("px"), PCT("%"), EM("em"), REM("rem");

        private final String suffix;

        Unit(final String suffix) {
            this.suffix = suffix;
        }
    }

    public interface HasCssName {
        String getCssName();
    }

    public enum Display implements HasCssName {
        NONE("none"), BLOCK("block"), INLINE("inline"), INLINE_BLOCK("inline-block");

        private final String cssName;

        Display(final String cssName) {
            this.cssName = cssName;
        }

        @Override
        public String getCssName() {
            return cssName;
        }
    }

    private final HTMLElement element;

    Style(final HTMLElement element) {
        this.element = element;
    }

    public void setMarginTop(final double value, final Unit unit) {
        setProperty("margin-top", value, unit);
    }

    public void setMarginLeft(final double value, final Unit unit) {
        setProperty("margin-left", value, unit);
    }

    public void setMarginRight(final double value, final Unit unit) {
        setProperty("margin-right", value, unit);
    }

    public void setMarginBottom(final double value, final Unit unit) {
        setProperty("margin-bottom", value, unit);
    }

    public void setPaddingTop(final double value, final Unit unit) {
        setProperty("padding-top", value, unit);
    }

    public void setPaddingLeft(final double value, final Unit unit) {
        setProperty("padding-left", value, unit);
    }

    public void setPaddingRight(final double value, final Unit unit) {
        setProperty("padding-right", value, unit);
    }

    public void setPaddingBottom(final double value, final Unit unit) {
        setProperty("padding-bottom", value, unit);
    }

    public void setColor(final String color) {
        setProperty("color", color);
    }

    public void setDisplay(final Display display) {
        setProperty("display", display == null ? "" : display.getCssName());
    }

    public void setProperty(final String name, final String value) {
        element.getStyle().setProperty(name, value == null ? "" : value);
    }

    public void setProperty(final String name, final double value, final Unit unit) {
        setProperty(name, value + unit.suffix);
    }
}
