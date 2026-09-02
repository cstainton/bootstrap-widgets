package com.google.gwt.event.dom.client;

public class MouseWheelEvent extends DomEvent<MouseWheelHandler> {

    private static final Type<MouseWheelHandler> TYPE = new Type<>("wheel", MouseWheelEvent::new);

    public static Type<MouseWheelHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<MouseWheelHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final MouseWheelHandler handler) {
        handler.onMouseWheel(this);
    }
}
