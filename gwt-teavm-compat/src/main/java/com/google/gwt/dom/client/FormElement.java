package com.google.gwt.dom.client;

import org.teavm.jso.dom.html.HTMLElement;

/** Typed view of a {@code <Form>}-family element. */
public class FormElement extends Element {

    public FormElement(final HTMLElement element) {
        super(element);
    }

    public static FormElement as(final Element element) {
        return element == null ? null : new FormElement(element.unwrap());
    }

    public String getAction() {
        return getPropertyString("action");
    }

    public void setAction(final String action) {
        setPropertyString("action", action);
    }

    public String getMethod() {
        return getPropertyString("method");
    }

    public void setMethod(final String method) {
        setPropertyString("method", method);
    }

    public String getTarget() {
        return getPropertyString("target");
    }

    public void setTarget(final String target) {
        setPropertyString("target", target);
    }

    public void submit() {
        submitForm(unwrap());
    }

    @org.teavm.jso.JSBody(params = {"el"}, script = "el.submit();")
    private static native void submitForm(org.teavm.jso.dom.html.HTMLElement el);

    public static final String TAG = "form";

    /** True when the element is a {@code <form>}. */
    public static boolean is(final Element element) {
        if (element == null) {
            return false;
        }
        final String tag = element.getTagName();
        return "form".equalsIgnoreCase(tag);
    }

    public void setAction(final com.google.gwt.safehtml.shared.SafeUri url) {
        setAction(url == null ? "" : url.asString());
    }
}
