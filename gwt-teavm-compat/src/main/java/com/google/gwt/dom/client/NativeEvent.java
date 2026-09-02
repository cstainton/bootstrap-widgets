package com.google.gwt.dom.client;

import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.MouseEvent;

/**
 * Wraps the browser event behind the GWT {@code NativeEvent} surface.
 */
public class NativeEvent {

    private final Event event;

    public NativeEvent(final Event event) {
        this.event = event;
    }

    public Event unwrap() {
        return event;
    }

    public String getType() {
        return event == null ? "" : event.getType();
    }

    public void preventDefault() {
        if (event != null) {
            event.preventDefault();
        }
    }

    public void stopPropagation() {
        if (event != null) {
            event.stopPropagation();
        }
    }

    public Element getEventTarget() {
        if (!(event instanceof MouseEvent)) {
            return null;
        }
        final org.teavm.jso.dom.html.HTMLElement target =
                (org.teavm.jso.dom.html.HTMLElement) ((MouseEvent) event).getTarget();
        return target == null ? null : new Element(target);
    }

    public int getClientX() {
        return event instanceof MouseEvent ? ((MouseEvent) event).getClientX() : 0;
    }

    public int getClientY() {
        return event instanceof MouseEvent ? ((MouseEvent) event).getClientY() : 0;
    }

    public int getKeyCode() {
        return event == null ? 0 : keyCode(event);
    }

    public int getCharCode() {
        return event == null ? 0 : charCode(event);
    }

    public boolean getCtrlKey() {
        return event != null && modifier(event, "ctrlKey");
    }

    public boolean getShiftKey() {
        return event != null && modifier(event, "shiftKey");
    }

    public boolean getAltKey() {
        return event != null && modifier(event, "altKey");
    }

    public boolean getMetaKey() {
        return event != null && modifier(event, "metaKey");
    }

    @org.teavm.jso.JSBody(params = {"e"}, script = "return e.keyCode || e.which || 0;")
    private static native int keyCode(Event e);

    @org.teavm.jso.JSBody(params = {"e"}, script = "return e.charCode || 0;")
    private static native int charCode(Event e);

    @org.teavm.jso.JSBody(params = {"e", "name"}, script = "return !!e[name];")
    private static native boolean modifier(Event e, String name);
}
