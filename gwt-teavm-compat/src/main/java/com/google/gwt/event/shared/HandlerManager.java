package com.google.gwt.event.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dispatches {@link GwtEvent}s to registered handlers.
 *
 * <p>Handlers are copied before dispatch so a handler may add or remove handlers
 * while an event is being delivered, matching GWT's documented behaviour.</p>
 */
public class HandlerManager implements HasHandlers {

    private final Map<GwtEvent.Type<?>, List<EventHandler>> handlers = new HashMap<>();
    private final Object source;

    public HandlerManager(final Object source) {
        this.source = source;
    }

    public <H extends EventHandler> HandlerRegistration addHandler(final GwtEvent.Type<H> type, final H handler) {
        if (type == null) {
            throw new IllegalArgumentException("type must not be null");
        }
        if (handler == null) {
            throw new IllegalArgumentException("handler must not be null");
        }
        handlers.computeIfAbsent(type, key -> new ArrayList<>()).add(handler);
        return () -> removeHandler(type, handler);
    }

    public <H extends EventHandler> void removeHandler(final GwtEvent.Type<H> type, final H handler) {
        final List<EventHandler> registered = handlers.get(type);
        if (registered != null) {
            registered.remove(handler);
            if (registered.isEmpty()) {
                handlers.remove(type);
            }
        }
    }

    public int getHandlerCount(final GwtEvent.Type<?> type) {
        final List<EventHandler> registered = handlers.get(type);
        return registered == null ? 0 : registered.size();
    }

    @Override
    public void fireEvent(final GwtEvent<?> event) {
        if (event == null) {
            return;
        }
        final List<EventHandler> registered = handlers.get(event.getAssociatedType());
        if (registered == null || registered.isEmpty()) {
            return;
        }
        if (event.getSource() == null) {
            event.doSetSource(source);
        }
        for (final EventHandler handler : new ArrayList<>(registered)) {
            event.doDispatch(handler);
        }
    }
}
