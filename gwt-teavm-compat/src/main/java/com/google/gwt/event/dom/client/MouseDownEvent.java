package com.google.gwt.event.dom.client;

public class MouseDownEvent extends DomEvent<MouseDownHandler> {

    private static final Type<MouseDownHandler> TYPE = new Type<>("mousedown", MouseDownEvent::new);

    public static Type<MouseDownHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<MouseDownHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final MouseDownHandler handler) {
        handler.onMouseDown(this);
    }
}
