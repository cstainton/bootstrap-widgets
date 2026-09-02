package org.gwtbootstrap5.teavm.bootstrap;

import org.teavm.jso.JSBody;
import org.teavm.jso.dom.html.HTMLElement;

/** Thin TeaVM calls into Bootstrap 5's JavaScript API. */
public final class TeaVmBootstrap {

    private TeaVmBootstrap() {
    }

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Modal) { window.bootstrap.Modal.getOrCreateInstance(element).show(); }")
    public static native void showModal(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Modal) { window.bootstrap.Modal.getOrCreateInstance(element).hide(); }")
    public static native void hideModal(HTMLElement element);
}
