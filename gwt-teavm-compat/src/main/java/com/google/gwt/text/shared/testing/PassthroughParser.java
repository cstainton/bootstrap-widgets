package com.google.gwt.text.shared.testing;

import com.google.gwt.text.shared.Parser;

/** Parses a string as itself; empty input parses to {@code null}, as in GWT. */
public class PassthroughParser implements Parser<String> {

    private static final PassthroughParser INSTANCE = new PassthroughParser();

    public static PassthroughParser instance() {
        return INSTANCE;
    }

    protected PassthroughParser() {
    }

    @Override
    public String parse(final CharSequence text) {
        return text == null || text.length() == 0 ? null : text.toString();
    }
}
