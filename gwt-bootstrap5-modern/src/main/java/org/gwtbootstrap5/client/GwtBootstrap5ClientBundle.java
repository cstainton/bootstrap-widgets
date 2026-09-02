package org.gwtbootstrap5.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/** Client-side script resources for the Bootstrap 5-native widget set. */
public interface GwtBootstrap5ClientBundle extends ClientBundle {

    GwtBootstrap5ClientBundle INSTANCE = GWT.create(GwtBootstrap5ClientBundle.class);

    @Source("resource/js/bootstrap-5.3.8.bundle.min.cache.js")
    TextResource bootstrap();
}
