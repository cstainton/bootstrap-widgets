package com.google.gwt.event.dom.client;

public class KeyDownEvent extends DomEvent<KeyDownHandler> {

    private static final Type<KeyDownHandler> TYPE = new Type<>("keydown", KeyDownEvent::new);

    public static Type<KeyDownHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<KeyDownHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final KeyDownHandler handler) {
        handler.onKeyDown(this);
    }
}
