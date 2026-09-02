package com.google.gwt.user.client.ui.impl;

import com.google.gwt.dom.client.Element;
import org.teavm.jso.JSBody;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.html.HTMLElement;

/**
 * Wires a form element's submit and its target frame's load back to the host panel.
 */
public class FormPanelImpl {

    /** Hooks the form's submit event and the frame's load event to {@code host}. */
    public void hookEvents(final Element iframe, final Element form, final FormPanelImplHost host) {
        if (form != null) {
            form.unwrap().addEventListener("submit", (Event event) -> {
                if (!host.onFormSubmit()) {
                    event.preventDefault();
                }
            });
        }
        if (iframe != null) {
            iframe.unwrap().addEventListener("load", (Event event) -> host.onFrameLoad());
        }
    }

    public void unhookEvents(final Element iframe, final Element form) {
        // Listeners are bound to the elements' lifetime; nothing to unhook explicitly.
    }

    public String getContents(final Element iframe) {
        return frameText(iframe.unwrap());
    }

    public String getEncoding(final Element form) {
        return form.getPropertyString("enctype");
    }

    public void setEncoding(final Element form, final String encoding) {
        form.setPropertyString("enctype", encoding);
    }

    public void submit(final Element form, final Element iframe) {
        submitForm(form.unwrap());
    }

    @JSBody(params = {"el"}, script =
            "try { return el.contentDocument ? el.contentDocument.body.innerHTML : null; }"
            + " catch (e) { return null; }")
    private static native String frameText(HTMLElement el);

    @JSBody(params = {"el"}, script = "el.submit();")
    private static native void submitForm(HTMLElement el);

    public void reset(final Element form) {
        resetForm(form.unwrap());
    }

    @JSBody(params = {"el"}, script = "el.reset();")
    private static native void resetForm(HTMLElement el);
}
