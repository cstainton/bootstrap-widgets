package com.google.gwt.text.client;

import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;

public class LongRenderer extends AbstractRenderer<Long> {

    private static final LongRenderer INSTANCE = new LongRenderer();

    public static Renderer<Long> instance() {
        return INSTANCE;
    }

    protected LongRenderer() {
    }

    @Override
    public String render(final Long object) {
        return object == null ? "" : object.toString();
    }
}
