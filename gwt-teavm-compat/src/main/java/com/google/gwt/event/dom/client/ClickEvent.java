package com.google.gwt.event.dom.client;

public class ClickEvent extends DomEvent<ClickHandler> {

    private static final Type<ClickHandler> TYPE = new Type<>("click", ClickEvent::new);

    public static Type<ClickHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<ClickHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final ClickHandler handler) {
        handler.onClick(this);
    }
}
