package com.google.gwt.event.dom.client;

public class ChangeEvent extends DomEvent<ChangeHandler> {

    private static final Type<ChangeHandler> TYPE = new Type<>("change", ChangeEvent::new);

    public static Type<ChangeHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<ChangeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final ChangeHandler handler) {
        handler.onChange(this);
    }
}
