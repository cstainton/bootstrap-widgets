package com.google.gwt.event.logical.shared;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/** Fired when a widget is attached to, or detached from, the document. */
public class AttachEvent extends GwtEvent<AttachEvent.Handler> {

    /** Receives attach and detach notifications. */
    public interface Handler extends EventHandler {
        void onAttachOrDetach(AttachEvent event);
    }

    private static final Type<Handler> TYPE = new Type<>();

    private final boolean attached;

    protected AttachEvent(final boolean attached) {
        this.attached = attached;
    }

    public static Type<Handler> getType() {
        return TYPE;
    }

    public static void fire(final HasHandlers source, final boolean attached) {
        source.fireEvent(new AttachEvent(attached));
    }

    public boolean isAttached() {
        return attached;
    }

    @Override
    public Type<Handler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final Handler handler) {
        handler.onAttachOrDetach(this);
    }
}
