package org.gwtbootstrap5.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;

/** Injects Bootstrap 5 JavaScript when the host page has not already supplied it. */
public class GwtBootstrap5EntryPoint implements EntryPoint {

    private native boolean isBootstrapLoaded() /*-{
        return typeof $wnd.bootstrap !== 'undefined' && typeof $wnd.bootstrap.Modal !== 'undefined';
    }-*/;

    @Override
    public void onModuleLoad() {
        if (!isBootstrapLoaded()) {
            ScriptInjector.fromString(GwtBootstrap5ClientBundle.INSTANCE.bootstrap().getText())
                    .setWindow(ScriptInjector.TOP_WINDOW)
                    .inject();
        }
    }
}
