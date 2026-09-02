package org.gwtbootstrap5.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;
import org.teavm.jso.JSBody;

/**
 * TeaVM port of the Bootstrap 5 entry point.
 *
 * <p>Same contract as the GWT entry point: if the host page has not already supplied
 * Bootstrap's JavaScript, inject it. The GWT build reads the script from a
 * {@code ClientBundle}, whose generator has no TeaVM equivalent; here the script is
 * expected on the page, and injection from a bundle is available once a bundle
 * implementation is registered with {@code GWT.register}.</p>
 */
public class GwtBootstrap5EntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        if (isBootstrapLoaded()) {
            return;
        }
        final String script = bundledScript();
        if (script != null && !script.isEmpty()) {
            ScriptInjector.fromString(script).inject();
        }
    }

    /**
     * The Bootstrap bundle source, when a {@link GwtBootstrap5ClientBundle} has been
     * registered. Returns null when none is, in which case the host page is expected
     * to have loaded Bootstrap itself.
     */
    private String bundledScript() {
        try {
            return GwtBootstrap5ClientBundle.INSTANCE.bootstrap().getText();
        } catch (final RuntimeException notRegistered) {
            return null;
        }
    }

    @JSBody(script = "return !!(window.bootstrap && window.bootstrap.Modal);")
    private static native boolean isBootstrapLoaded();
}
