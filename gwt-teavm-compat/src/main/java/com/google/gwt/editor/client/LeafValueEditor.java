package com.google.gwt.editor.client;

public interface LeafValueEditor<T> extends Editor<T> {
    void setValue(T value);

    T getValue();
}
