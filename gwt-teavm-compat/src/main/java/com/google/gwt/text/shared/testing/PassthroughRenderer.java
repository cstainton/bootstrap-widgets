package com.google.gwt.text.shared.testing;

import com.google.gwt.text.shared.AbstractRenderer;

/** Renders a string as itself. */
public class PassthroughRenderer extends AbstractRenderer<String> {

    private static final PassthroughRenderer INSTANCE = new PassthroughRenderer();

    public static PassthroughRenderer instance() {
        return INSTANCE;
    }

    protected PassthroughRenderer() {
    }

    @Override
    public String render(final String object) {
        return object == null ? "" : object;
    }
}
