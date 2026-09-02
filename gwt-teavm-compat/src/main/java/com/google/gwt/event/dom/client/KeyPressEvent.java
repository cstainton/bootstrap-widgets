package com.google.gwt.event.dom.client;

public class KeyPressEvent extends DomEvent<KeyPressHandler> {

    private static final Type<KeyPressHandler> TYPE = new Type<>("keypress", KeyPressEvent::new);

    public static Type<KeyPressHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<KeyPressHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final KeyPressHandler handler) {
        handler.onKeyPress(this);
    }
}
