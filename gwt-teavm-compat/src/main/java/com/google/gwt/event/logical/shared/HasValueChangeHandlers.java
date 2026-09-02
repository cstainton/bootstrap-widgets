package com.google.gwt.event.logical.shared;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasValueChangeHandlers<T> extends HasHandlers {
    HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler);
}
