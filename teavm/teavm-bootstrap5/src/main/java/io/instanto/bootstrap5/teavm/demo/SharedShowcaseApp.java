package io.instanto.bootstrap5.teavm.demo;

import com.google.gwt.core.client.GWT;

import io.instanto.bootstrap5.client.Bootstrap5;
import io.instanto.bootstrap5.client.Bootstrap5Resources;
import io.instanto.bootstrap5.client.ui.Container;
import io.instanto.bootstrap5.showcase.client.ShowcaseEntryPoint;

/**
 * The GWT showcase, run on TeaVM.
 *
 * <p>Not a port: this is the same ShowcaseEntryPoint the GWT build compiles, reached
 * through the same onModuleLoad GWT calls. The only thing this class supplies is what
 * GWT's module system would have -- where the assets live, and the call to start.</p>
 */
public final class SharedShowcaseApp {

    private SharedShowcaseApp() {
    }

    public static void main(final String[] args) {
        GWT.setModuleBaseURL("teavm5/");
        Bootstrap5Resources.setAssetBase("teavm5/");
        new ShowcaseEntryPoint().onModuleLoad();
        mountUiBinderProof();
    }

    /**
     * One panel the GWT showcase does not have, because it demonstrates something only
     * this backend needs: a UiBinder template working without GWT's generator. Keeping it
     * on the page also keeps it compiled, since TeaVM only compiles what is reached.
     */
    private static void mountUiBinderProof() {
        final Container container = new Container();
        container.addStyleName("pb-5");
        container.add(new UiBinderDemo());
        Bootstrap5.mount(container);
    }
}
