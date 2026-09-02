package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.FormElement;

/** A panel wrapping a {@code form} element. */
public class FormPanel extends SimplePanel {

    /** Standard form encodings. */
    public static final String ENCODING_MULTIPART = "multipart/form-data";
    public static final String ENCODING_URLENCODED = "application/x-www-form-urlencoded";
    public static final String METHOD_GET = "get";
    public static final String METHOD_POST = "post";

    public FormPanel() {
        this(Document.get().createFormElement());
    }

    public FormPanel(final Element element) {
        super(element);
    }

    protected FormElement getFormElement() {
        return FormElement.as(getElement());
    }

    public String getAction() {
        return getFormElement().getAction();
    }

    public void setAction(final String action) {
        getFormElement().setAction(action);
    }

    public String getMethod() {
        return getFormElement().getMethod();
    }

    public void setMethod(final String method) {
        getFormElement().setMethod(method);
    }

    public String getEncoding() {
        return getElement().getPropertyString("enctype");
    }

    public void setEncoding(final String encoding) {
        getElement().setPropertyString("enctype", encoding);
    }

    public void submit() {
        getFormElement().submit();
    }

    public void reset() {
        resetForm(getElement().unwrap());
    }

    @org.teavm.jso.JSBody(params = {"el"}, script = "el.reset();")
    private static native void resetForm(org.teavm.jso.dom.html.HTMLElement el);
}
