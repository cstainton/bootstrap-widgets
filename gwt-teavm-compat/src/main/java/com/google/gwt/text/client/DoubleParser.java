package com.google.gwt.text.client;

import com.google.gwt.text.shared.Parser;
import java.text.ParseException;

public class DoubleParser implements Parser<Double> {

    private static final DoubleParser INSTANCE = new DoubleParser();

    public static Parser<Double> instance() {
        return INSTANCE;
    }

    protected DoubleParser() {
    }

    @Override
    public Double parse(final CharSequence text) throws ParseException {
        if (text == null || text.toString().trim().isEmpty()) {
            return null;
        }
        try {
            return Double.valueOf(text.toString().trim());
        } catch (final NumberFormatException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }
}
