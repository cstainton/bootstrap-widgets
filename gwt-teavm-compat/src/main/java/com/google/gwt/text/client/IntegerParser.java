package com.google.gwt.text.client;

import com.google.gwt.text.shared.Parser;
import java.text.ParseException;

public class IntegerParser implements Parser<Integer> {

    private static final IntegerParser INSTANCE = new IntegerParser();

    public static Parser<Integer> instance() {
        return INSTANCE;
    }

    protected IntegerParser() {
    }

    @Override
    public Integer parse(final CharSequence text) throws ParseException {
        if (text == null || text.toString().trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.valueOf(text.toString().trim());
        } catch (final NumberFormatException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }
}
