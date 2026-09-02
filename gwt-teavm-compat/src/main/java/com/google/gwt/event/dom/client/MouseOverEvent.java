package com.google.gwt.event.dom.client;

public class MouseOverEvent extends DomEvent<MouseOverHandler> {

    private static final Type<MouseOverHandler> TYPE = new Type<>("mouseover", MouseOverEvent::new);

    public static Type<MouseOverHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<MouseOverHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final MouseOverHandler handler) {
        handler.onMouseOver(this);
    }
}
