package com.google.gwt.event.dom.client;

import com.google.gwt.event.shared.HandlerRegistration;

public interface HasClickHandlers {
    HandlerRegistration addClickHandler(ClickHandler handler);
}
