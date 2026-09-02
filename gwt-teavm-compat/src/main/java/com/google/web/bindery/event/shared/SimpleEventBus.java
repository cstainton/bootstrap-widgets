package com.google.web.bindery.event.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** In-memory event bus, sufficient for the widget library's validation events. */
public class SimpleEventBus extends EventBus {

    private final Map<Event.Type<?>, List<Object>> handlers = new HashMap<>();

    @Override
    public <H> HandlerRegistration addHandler(final Event.Type<H> type, final H handler) {
        handlers.computeIfAbsent(type, key -> new ArrayList<>()).add(handler);
        return () -> {
            final List<Object> registered = handlers.get(type);
            if (registered != null) {
                registered.remove(handler);
            }
        };
    }

    @Override
    public <H> HandlerRegistration addHandlerToSource(final Event.Type<H> type, final Object source, final H handler) {
        return addHandler(type, handler);
    }

    @Override
    public void fireEvent(final Event<?> event) {
        fireEventFromSource(event, null);
    }

    @Override
    public void fireEventFromSource(final Event<?> event, final Object source) {
        if (event == null) {
            return;
        }
        final List<Object> registered = handlers.get(event.getAssociatedType());
        if (registered == null) {
            return;
        }
        for (final Object handler : new ArrayList<>(registered)) {
            event.dispatchUnchecked(handler);
        }
    }
}
