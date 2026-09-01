package org.gwtbootstrap3.teavm.bootstrap;

import org.teavm.jso.JSBody;
import org.teavm.jso.dom.html.HTMLElement;

/**
 * Thin TeaVM calls into Bootstrap 5's JavaScript API.
 */
public final class TeaVmBootstrap {

    private TeaVmBootstrap() {
    }

    @JSBody(params = {"element"}, script = "return bootstrap.Modal.getOrCreateInstance(element);")
    private static native BootstrapModal modalFor(HTMLElement element);

    public static void showModal(final HTMLElement element) {
        modalFor(element).show();
    }

    public static void hideModal(final HTMLElement element) {
        modalFor(element).hide();
    }
}
