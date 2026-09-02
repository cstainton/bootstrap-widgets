package com.google.gwt.regexp.shared;

import java.util.regex.Matcher;

/** The outcome of a successful {@link RegExp} match. */
public class MatchResult {

    private final Matcher matcher;

    MatchResult(final Matcher matcher) {
        this.matcher = matcher;
    }

    public String getGroup(final int index) {
        return matcher.group(index);
    }

    public int getGroupCount() {
        return matcher.groupCount() + 1;
    }

    public int getIndex() {
        return matcher.start();
    }

    public String getInput() {
        return matcher.group();
    }
}
