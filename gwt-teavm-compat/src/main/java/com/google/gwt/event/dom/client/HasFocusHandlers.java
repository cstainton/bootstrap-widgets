package com.google.gwt.event.dom.client;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasFocusHandlers extends HasHandlers {
    HandlerRegistration addFocusHandler(FocusHandler handler);
}
