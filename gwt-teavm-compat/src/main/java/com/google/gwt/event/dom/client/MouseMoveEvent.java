package com.google.gwt.event.dom.client;

public class MouseMoveEvent extends DomEvent<MouseMoveHandler> {

    private static final Type<MouseMoveHandler> TYPE = new Type<>("mousemove", MouseMoveEvent::new);

    public static Type<MouseMoveHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<MouseMoveHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final MouseMoveHandler handler) {
        handler.onMouseMove(this);
    }
}
