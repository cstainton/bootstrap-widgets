package org.gwtbootstrap3.teavm.bootstrap;

import org.teavm.jso.JSBody;
import org.teavm.jso.dom.html.HTMLElement;

/** Thin TeaVM calls into Bootstrap 3's jQuery plugin API. */
public final class TeaVmBootstrap {

    private TeaVmBootstrap() {
    }

    @JSBody(params = {"element"}, script = "if (window.jQuery) { window.jQuery(element).modal('show'); }")
    public static native void showModal(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.jQuery) { window.jQuery(element).modal('hide'); }")
    public static native void hideModal(HTMLElement element);
}
