package com.google.web.bindery.event.shared;

/** Root event type shared by the GWT and bindery event APIs. */
public abstract class Event<H> {

    /** Identifies a family of events and the handler interface that receives them. */
    public static class Type<H> {
    }

    private Object source;

    public abstract Type<H> getAssociatedType();

    protected abstract void dispatch(H handler);

    public Object getSource() {
        return source;
    }

    protected void setSource(final Object source) {
        this.source = source;
    }

    /** Lets an event bus in this package dispatch without widening {@code dispatch}. */
    @SuppressWarnings("unchecked")
    final void dispatchUnchecked(final Object handler) {
        dispatch((H) handler);
    }
}
