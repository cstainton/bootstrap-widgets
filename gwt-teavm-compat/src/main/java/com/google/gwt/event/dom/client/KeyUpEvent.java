package com.google.gwt.event.dom.client;

public class KeyUpEvent extends DomEvent<KeyUpHandler> {

    private static final Type<KeyUpHandler> TYPE = new Type<>("keyup", KeyUpEvent::new);

    public static Type<KeyUpHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<KeyUpHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final KeyUpHandler handler) {
        handler.onKeyUp(this);
    }
}
