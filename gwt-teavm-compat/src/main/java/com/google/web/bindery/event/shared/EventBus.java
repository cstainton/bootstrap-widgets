package com.google.web.bindery.event.shared;

public abstract class EventBus {

    public abstract <H> HandlerRegistration addHandler(Event.Type<H> type, H handler);

    public abstract <H> HandlerRegistration addHandlerToSource(Event.Type<H> type, Object source, H handler);

    public abstract void fireEvent(Event<?> event);

    public abstract void fireEventFromSource(Event<?> event, Object source);
}
