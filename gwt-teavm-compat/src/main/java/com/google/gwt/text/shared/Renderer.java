package com.google.gwt.text.shared;

import java.io.IOException;

public interface Renderer<T> {
    String render(T object);

    void render(T object, Appendable appendable) throws IOException;
}
