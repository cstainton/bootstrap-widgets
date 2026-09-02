package com.google.gwt.safehtml.shared;

public class SafeHtmlBuilder {

    private final StringBuilder sb = new StringBuilder();

    public SafeHtmlBuilder append(final SafeHtml html) {
        if (html != null) {
            sb.append(html.asString());
        }
        return this;
    }

    public SafeHtmlBuilder appendEscaped(final String text) {
        sb.append(SafeHtmlUtils.htmlEscape(text));
        return this;
    }

    public SafeHtmlBuilder appendHtmlConstant(final String html) {
        if (html != null) {
            sb.append(html);
        }
        return this;
    }

    public SafeHtml toSafeHtml() {
        final String value = sb.toString();
        return () -> value;
    }
}
