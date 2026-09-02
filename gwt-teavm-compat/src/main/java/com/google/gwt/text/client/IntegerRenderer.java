package com.google.gwt.text.client;

import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;

public class IntegerRenderer extends AbstractRenderer<Integer> {

    private static final IntegerRenderer INSTANCE = new IntegerRenderer();

    public static Renderer<Integer> instance() {
        return INSTANCE;
    }

    protected IntegerRenderer() {
    }

    @Override
    public String render(final Integer object) {
        return object == null ? "" : object.toString();
    }
}
