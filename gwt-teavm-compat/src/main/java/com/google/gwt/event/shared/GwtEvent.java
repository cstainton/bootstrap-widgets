package com.google.gwt.event.shared;

/**
 * Base class for GWT events.
 *
 * <p>As in GWT, this extends {@code com.google.web.bindery.event.shared.Event} so a
 * {@code GwtEvent} and its {@code Type} can be handed to an {@code EventBus}.</p>
 */
public abstract class GwtEvent<H extends EventHandler> extends com.google.web.bindery.event.shared.Event<H> {

    /** Identifies a family of events and the handler interface that receives them. */
    public static class Type<H> extends com.google.web.bindery.event.shared.Event.Type<H> {
    }

    @Override
    public abstract Type<H> getAssociatedType();

    @Override
    protected abstract void dispatch(H handler);

    void doSetSource(final Object source) {
        setSource(source);
    }

    @SuppressWarnings("unchecked")
    void doDispatch(final EventHandler handler) {
        dispatch((H) handler);
    }
}
