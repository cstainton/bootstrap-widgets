package com.google.gwt.text.client;

import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;

public class DoubleRenderer extends AbstractRenderer<Double> {

    private static final DoubleRenderer INSTANCE = new DoubleRenderer();

    public static Renderer<Double> instance() {
        return INSTANCE;
    }

    protected DoubleRenderer() {
    }

    @Override
    public String render(final Double object) {
        return object == null ? "" : object.toString();
    }
}
