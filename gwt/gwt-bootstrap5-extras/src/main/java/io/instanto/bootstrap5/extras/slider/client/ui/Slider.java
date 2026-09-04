/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2018 GwtBootstrap3
 * %%
 * Modified from the GwtBootstrap3 original for the Bootstrap 5 track of
 * GWT Bootstrap: moved to the io.instanto.bootstrap5 namespace and
 * re-targeted at Bootstrap 5 markup, class names and JavaScript APIs. The
 * GwtBootstrap3 copyright above is retained as required by the Apache
 * License 2.0; the namespace changed, the attribution did not.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.instanto.bootstrap5.extras.slider.client.ui;

import io.instanto.bootstrap5.client.ui.base.HasId;
import io.instanto.bootstrap5.client.ui.base.mixin.IdMixin;
import io.instanto.bootstrap5.client.ui.html.Div;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasEnabled;

/**
 * A slider with the capabilities a native range input does not have, backed by
 * noUiSlider 15.
 *
 * <p>For a plain single value, prefer {@link io.instanto.bootstrap5.client.ui.Range}:
 * Bootstrap 5 styles the native control and nothing needs shipping. This is for
 * two handles, non-linear scales, tooltips and pips. GwtBootstrap3 wrapped
 * bootstrap-slider, a jQuery plugin; noUiSlider has no dependencies.</p>
 *
 * <p>A slider with one handle reports through {@link #getValue()}; one with two
 * reports through {@link #getLowerValue()} and {@link #getUpperValue()}.</p>
 */
public class Slider extends Div implements HasEnabled, HasId, HasValueChangeHandlers<Double> {

    private final IdMixin<Slider> idMixin = new IdMixin<Slider>(this);

    private double min = 0;

    private double max = 100;

    private double step = 1;

    private double start = 0;

    private double end = 100;

    private boolean range;

    private boolean tooltips;

    private boolean pips;

    private boolean enabled = true;

    private JavaScriptObject slider;

    public Slider() {
        addStyleName("gbm-slider");
        getElement().getStyle().setProperty("margin", "1.5rem 0.5rem");
    }

    public Slider(final double min, final double max) {
        this();
        setMin(min);
        setMax(max);
        setValue(min);
    }

    /** Two handles rather than one. Set before attach. */
    public void setRange(final boolean range) {
        this.range = range;
    }

    public boolean isRange() {
        return range;
    }

    /** Shows the value above each handle. Set before attach. */
    public void setTooltips(final boolean tooltips) {
        this.tooltips = tooltips;
    }

    /** Draws a scale beneath the track. Set before attach. */
    public void setPips(final boolean pips) {
        this.pips = pips;
    }

    public void setMin(final double min) {
        this.min = min;
    }

    public double getMin() {
        return min;
    }

    public void setMax(final double max) {
        this.max = max;
    }

    public double getMax() {
        return max;
    }

    public void setStep(final double step) {
        this.step = step;
    }

    public double getStep() {
        return step;
    }

    /** The handle position of a single-handle slider. */
    public void setValue(final double value) {
        start = value;
        if (slider != null) {
            applyValues(slider, value, value);
        }
    }

    public double getValue() {
        return slider == null ? start : readValue(slider, 0);
    }

    /** The handle positions of a two-handle slider. */
    public void setValues(final double lower, final double upper) {
        start = lower;
        end = upper;
        if (slider != null) {
            applyValues(slider, lower, upper);
        }
    }

    public double getLowerValue() {
        return slider == null ? start : readValue(slider, 0);
    }

    public double getUpperValue() {
        return slider == null ? end : readValue(slider, 1);
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        slider = create(getElement(), min, max, step, start, end, range, tooltips, pips);
        bindChange(slider, this);
        if (!enabled) {
            applyEnabled(getElement(), false);
        }
    }

    @Override
    protected void onUnload() {
        if (slider != null) {
            destroy(slider);
            slider = null;
        }
        super.onUnload();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
        if (slider != null) {
            applyEnabled(getElement(), enabled);
        }
    }

    @Override
    public void setId(final String id) {
        idMixin.setId(id);
    }

    @Override
    public String getId() {
        return idMixin.getId();
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Double> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    /** Called from the noUiSlider update subscription. */
    void onSliderUpdate(final double value) {
        ValueChangeEvent.fire(this, value);
    }

    private static native JavaScriptObject create(com.google.gwt.dom.client.Element element,
            double min, double max, double step, double start, double end,
            boolean range, boolean tooltips, boolean pips) /*-{
        var options = {
            start: range ? [start, end] : [start],
            connect: range ? true : "lower",
            step: step,
            range: { min: min, max: max },
            tooltips: tooltips
        };
        if (pips) {
            options.pips = { mode: "count", values: 5, density: 4 };
        }
        $wnd.noUiSlider.create(element, options);
        return element.noUiSlider;
    }-*/;

    private static native void bindChange(JavaScriptObject slider, Slider widget) /*-{
        slider.on("update", function (values, handle) {
            widget.@io.instanto.bootstrap5.extras.slider.client.ui.Slider::onSliderUpdate(D)(
                    parseFloat(values[handle]));
        });
    }-*/;

    /**
     * noUiSlider returns a string for one handle and an array of strings for
     * two. "instanceof Array" is unreliable from compiled GWT, where the array
     * can come from another realm; a false result there silently parsed
     * "120.00,880.00" as 120, so both handles read the same. Duck-typing the
     * array is what actually holds.
     */
    private static native double readValue(JavaScriptObject slider, int handle) /*-{
        var v = slider.get();
        var isArray = v != null && typeof v !== "string" && typeof v.length === "number";
        return parseFloat(isArray ? v[handle] : v);
    }-*/;

    private static native void applyValues(JavaScriptObject slider, double lower, double upper) /*-{
        var current = slider.get();
        var isArray = current != null && typeof current !== "string"
                && typeof current.length === "number";
        slider.set(isArray ? [lower, upper] : lower);
    }-*/;

    private static native void applyEnabled(com.google.gwt.dom.client.Element element,
            boolean enabled) /*-{
        if (enabled) {
            element.removeAttribute("disabled");
        } else {
            element.setAttribute("disabled", true);
        }
    }-*/;

    private static native void destroy(JavaScriptObject slider) /*-{
        if (typeof slider.destroy === "function") {
            slider.destroy();
        }
    }-*/;
}
