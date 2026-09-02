package com.google.gwt.text.shared;

import java.text.ParseException;

public interface Parser<T> {
    T parse(CharSequence text) throws ParseException;
}
