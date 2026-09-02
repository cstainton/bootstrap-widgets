package com.google.gwt.resources.client;

/** A packaged stylesheet. */
public interface CssResource extends ResourcePrototype {

    String getText();

    /** Appends the stylesheet to the document; returns false if already injected. */
    boolean ensureInjected();
}
