package com.google.gwt.text.shared;

import java.io.IOException;

public abstract class AbstractRenderer<T> implements Renderer<T> {
    @Override
    public void render(final T object, final Appendable appendable) throws IOException {
        appendable.append(render(object));
    }
}
