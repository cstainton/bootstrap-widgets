package com.google.gwt.event.dom.client;

public class FocusEvent extends DomEvent<FocusHandler> {

    private static final Type<FocusHandler> TYPE = new Type<>("focus", FocusEvent::new);

    public static Type<FocusHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<FocusHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final FocusHandler handler) {
        handler.onFocus(this);
    }
}
