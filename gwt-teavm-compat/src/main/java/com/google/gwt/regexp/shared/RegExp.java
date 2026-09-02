package com.google.gwt.regexp.shared;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Regular expression, backed by {@code java.util.regex} under TeaVM. */
public class RegExp {

    private final Pattern pattern;

    private RegExp(final Pattern pattern) {
        this.pattern = pattern;
    }

    public static RegExp compile(final String regex) {
        return new RegExp(Pattern.compile(regex));
    }

    public static RegExp compile(final String regex, final String flags) {
        int f = 0;
        if (flags != null) {
            if (flags.indexOf('i') >= 0) {
                f |= Pattern.CASE_INSENSITIVE;
            }
            if (flags.indexOf('m') >= 0) {
                f |= Pattern.MULTILINE;
            }
        }
        return new RegExp(Pattern.compile(regex, f));
    }

    public boolean test(final String input) {
        return input != null && pattern.matcher(input).find();
    }

    public MatchResult exec(final String input) {
        if (input == null) {
            return null;
        }
        final Matcher matcher = pattern.matcher(input);
        return matcher.find() ? new MatchResult(matcher) : null;
    }

    public String getSource() {
        return pattern.pattern();
    }

    public String replace(final String input, final String replacement) {
        return input == null ? null : pattern.matcher(input).replaceAll(replacement);
    }
}
