package com.google.gwt.event.logical.shared;

import com.google.gwt.event.shared.EventHandler;

public interface ValueChangeHandler<T> extends EventHandler {
    void onValueChange(ValueChangeEvent<T> event);
}
