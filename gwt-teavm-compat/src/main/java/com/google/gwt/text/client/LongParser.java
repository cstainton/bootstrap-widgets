package com.google.gwt.text.client;

import com.google.gwt.text.shared.Parser;
import java.text.ParseException;

public class LongParser implements Parser<Long> {

    private static final LongParser INSTANCE = new LongParser();

    public static Parser<Long> instance() {
        return INSTANCE;
    }

    protected LongParser() {
    }

    @Override
    public Long parse(final CharSequence text) throws ParseException {
        if (text == null || text.toString().trim().isEmpty()) {
            return null;
        }
        try {
            return Long.valueOf(text.toString().trim());
        } catch (final NumberFormatException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }
}
