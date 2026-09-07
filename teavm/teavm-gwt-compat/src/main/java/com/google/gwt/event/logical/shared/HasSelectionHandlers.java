package com.google.gwt.event.logical.shared;

import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.event.shared.HandlerRegistration;

public interface HasSelectionHandlers<T> extends HasHandlers {
    HandlerRegistration addSelectionHandler(SelectionHandler<T> handler);
}
