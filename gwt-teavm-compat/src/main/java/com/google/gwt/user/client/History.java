package com.google.gwt.user.client;

/**
 * History-token encoding.
 *
 * <p>The widget library uses exactly one thing from GWT's History: token encoding,
 * for {@code setTargetHistoryToken()} on Anchor and LinkedGroupItem. Navigation,
 * back/forward and history events belong to the application's routing layer, not to
 * a widget library, so they are deliberately not emulated here.</p>
 */
public final class History {

    private History() {
    }

    /**
     * Escapes the characters that are not legal in a URL fragment. Mirrors GWT's
     * encoding: {@code ?}, {@code #}, {@code &}, {@code ;}, {@code +} are escaped,
     * everything else is left alone so tokens stay readable.
     */
    public static String encodeHistoryToken(final String historyToken) {
        if (historyToken == null) {
            return "";
        }
        return historyToken
                .replace("?", "%3F")
                .replace("#", "%23")
                .replace("&", "%26")
                .replace(";", "%3B")
                .replace("+", "%2B");
    }
}
