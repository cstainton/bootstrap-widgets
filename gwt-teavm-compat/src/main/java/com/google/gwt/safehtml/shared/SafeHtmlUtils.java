package com.google.gwt.safehtml.shared;

public final class SafeHtmlUtils {

    private SafeHtmlUtils() {
    }

    public static String htmlEscape(final String text) {
        if (text == null) {
            return "";
        }
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    public static SafeHtml fromString(final String text) {
        final String escaped = htmlEscape(text);
        return () -> escaped;
    }

    public static SafeHtml fromTrustedString(final String html) {
        final String value = html == null ? "" : html;
        return () -> value;
    }

    public static SafeHtml fromSafeConstant(final String html) {
        return fromTrustedString(html);
    }

    /** Escapes markup but leaves existing character entities such as {@code &amp;} intact. */
    public static String htmlEscapeAllowEntities(final String text) {
        if (text == null) {
            return "";
        }
        return text.replaceAll("&(?!([a-zA-Z][a-zA-Z0-9]*|#[0-9]+|#[xX][0-9a-fA-F]+);)", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
