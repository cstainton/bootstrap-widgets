package com.google.gwt.user.client.ui;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;

public interface HasValue<T> extends HasValueChangeHandlers<T> {
    T getValue();

    void setValue(T value);

    void setValue(T value, boolean fireEvents);
}
