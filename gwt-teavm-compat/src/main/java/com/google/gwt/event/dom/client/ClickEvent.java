package com.google.gwt.event.dom.client;

public final class ClickEvent {
    private final org.teavm.jso.dom.events.MouseEvent nativeEvent;

    public ClickEvent(final org.teavm.jso.dom.events.MouseEvent nativeEvent) {
        this.nativeEvent = nativeEvent;
    }

    public org.teavm.jso.dom.events.MouseEvent getNativeEvent() {
        return nativeEvent;
    }
}
