package com.google.gwt.event.dom.client;

public class BlurEvent extends DomEvent<BlurHandler> {

    private static final Type<BlurHandler> TYPE = new Type<>("blur", BlurEvent::new);

    public static Type<BlurHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<BlurHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final BlurHandler handler) {
        handler.onBlur(this);
    }
}
