package com.google.gwt.event.dom.client;

public class DoubleClickEvent extends DomEvent<DoubleClickHandler> {

    private static final Type<DoubleClickHandler> TYPE = new Type<>("dblclick", DoubleClickEvent::new);

    public static Type<DoubleClickHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<DoubleClickHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final DoubleClickHandler handler) {
        handler.onDoubleClick(this);
    }
}
