package com.google.gwt.event.dom.client;

public class MouseUpEvent extends DomEvent<MouseUpHandler> {

    private static final Type<MouseUpHandler> TYPE = new Type<>("mouseup", MouseUpEvent::new);

    public static Type<MouseUpHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<MouseUpHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final MouseUpHandler handler) {
        handler.onMouseUp(this);
    }
}
