package com.google.gwt.event.shared;

/**
 * GWT's handler registration. It extends the bindery type so registrations are
 * interchangeable between the two APIs, exactly as in GWT.
 */
public interface HandlerRegistration extends com.google.web.bindery.event.shared.HandlerRegistration {
    @Override
    void removeHandler();
}
