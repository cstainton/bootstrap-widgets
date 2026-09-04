package io.instanto.bootstrap5.teavm.demo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;

import io.instanto.bootstrap5.client.ui.Button;
import io.instanto.bootstrap5.client.ui.PanelBody;
import io.instanto.bootstrap5.client.ui.TextBox;

/**
 * A UiBinder template on the TeaVM backend.
 *
 * <p>The same code a GWT application writes. GWT satisfies the GWT.create below with a
 * compile-time generator reached through deferred binding; TeaVM has no generator SPI, so
 * the build runs the equivalent step earlier and registers the result. Nothing here is
 * TeaVM-specific.</p>
 */
public class UiBinderDemo extends Composite {

    interface Binder extends UiBinder<PanelBody, UiBinderDemo> { }

    private static final Binder BINDER = GWT.create(Binder.class);

    @UiField Button counter;
    @UiField TextBox box;

    private int clicks;

    public UiBinderDemo() {
        initWidget(BINDER.createAndBindUi(this));
    }

    @UiHandler("counter")
    void onCounterClick(ClickEvent event) {
        clicks++;
        counter.setText("Clicked " + clicks + " times");
        box.setValue("the handler ran " + clicks + " times");
    }
}
