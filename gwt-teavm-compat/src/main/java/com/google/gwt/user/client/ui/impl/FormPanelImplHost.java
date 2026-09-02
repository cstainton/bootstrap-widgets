package com.google.gwt.user.client.ui.impl;

/** Implemented by a form panel so its impl can report submit lifecycle events. */
public interface FormPanelImplHost {

    boolean onFormSubmit();

    void onFrameLoad();
}
