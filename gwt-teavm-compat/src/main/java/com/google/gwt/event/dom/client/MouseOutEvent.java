package com.google.gwt.event.dom.client;

public class MouseOutEvent extends DomEvent<MouseOutHandler> {

    private static final Type<MouseOutHandler> TYPE = new Type<>("mouseout", MouseOutEvent::new);

    public static Type<MouseOutHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<MouseOutHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final MouseOutHandler handler) {
        handler.onMouseOut(this);
    }
}
