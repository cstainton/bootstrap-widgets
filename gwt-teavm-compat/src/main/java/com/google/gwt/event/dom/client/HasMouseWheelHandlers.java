package com.google.gwt.event.dom.client;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasMouseWheelHandlers extends HasHandlers {
    HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler);
}
