/*
 * #%L
 * GWT Bootstrap Modern
 * %%
 * Copyright (C) 2026 Carl Stainton
 * %%
 * Reimplements, over TeaVM's JSO libraries, part of the GWT client API. Class,
 * method and package names follow GWT (https://github.com/gwtproject/gwt),
 * Copyright (C) The GWT Project Authors, licensed under the Apache License,
 * Version 2.0. No GWT source is included.
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

/*
 * TeaVM implementation of the Bootstrap 5 widget of the same name.
 *
 * Identical to the GWT widget in package, API and behaviour. It exists separately only
 * because that widget reaches Bootstrap's JavaScript through JSNI, which TeaVM cannot
 * compile; the calls go through BootstrapJs instead. Keep this file in step with the
 * GWT one -- or, better, move the remaining JSNI behind a shared seam so both builds
 * can use a single definition, as BootstrapEventBridge already does for events.
 */
package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import org.gwtbootstrap5.client.shared.event.CarouselSlidEvent;
import org.gwtbootstrap5.client.shared.event.CarouselSlidHandler;
import org.gwtbootstrap5.client.shared.event.CarouselSlideEvent;
import org.gwtbootstrap5.client.shared.event.CarouselSlideHandler;
import org.gwtbootstrap5.client.ui.base.BootstrapEventBridge;
import org.gwtbootstrap5.client.ui.base.BootstrapEventHandler;

public class Carousel extends ElementPanel {

    public static final String HOVER = "hover";
    public static final String CAROUSEL = "carousel";
    public static final String CYCLE = "cycle";
    public static final String PAUSE = "pause";
    public static final String PREV = "prev";
    public static final String NEXT = "next";

    private final CarouselInner inner = new CarouselInner();
    private int interval = 5000;
    private String pause = HOVER;
    private boolean wrap = true;

    public Carousel() {
        super("div");
        setStyleName("carousel slide");
        getElement().setAttribute("data-bs-ride", "carousel");
        add(inner);
    }

    public CarouselInner getInner() {
        return inner;
    }

    public void setInterval(int intervalMs) {
        interval = intervalMs;
        reconfigureIfAttached();
    }

    public void setPause(String pause) {
        this.pause = pause;
        reconfigureIfAttached();
    }

    public void setWrap(boolean wrap) {
        this.wrap = wrap;
        reconfigureIfAttached();
    }

    public void addSlide(CarouselSlide slide) {
        inner.add(slide);
    }

    public void cycleCarousel() {
        BootstrapJs.call("Carousel", getElement(), "cycle");
    }

    public void pauseCarousel() {
        BootstrapJs.call("Carousel", getElement(), "pause");
    }

    public void goToPrev() {
        BootstrapJs.call("Carousel", getElement(), "prev");
    }

    public void goToNext() {
        BootstrapJs.call("Carousel", getElement(), "next");
    }

    public void jumpToSlide(int index) {
        BootstrapJs.call("Carousel", getElement(), "to", index);
    }

    public HandlerRegistration addSlideHandler(CarouselSlideHandler handler) {
        return addHandler(handler, CarouselSlideEvent.getType());
    }

    public HandlerRegistration addSlidHandler(CarouselSlidHandler handler) {
        return addHandler(handler, CarouselSlidEvent.getType());
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        BootstrapEventBridge.bind(getElement(), "slide.bs.carousel", new BootstrapEventHandler() {
            @Override
            public void onEvent(NativeEvent event) {
                fireEvent(new CarouselSlideEvent(Carousel.this, Event.as(event)));
            }
        });
        BootstrapEventBridge.bind(getElement(), "slid.bs.carousel", new BootstrapEventHandler() {
            @Override
            public void onEvent(NativeEvent event) {
                fireEvent(new CarouselSlidEvent(Carousel.this, Event.as(event)));
            }
        });
        BootstrapJs.initCarousel(getElement(), interval, pause, wrap);
    }

    @Override
    protected void onUnload() {
        BootstrapEventBridge.unbindAll(getElement());
        BootstrapJs.dispose("Carousel", getElement());
        super.onUnload();
    }

    private void reconfigureIfAttached() {
        if (isAttached()) {
            BootstrapJs.initCarousel(getElement(), interval, pause, wrap);
        }
    }




}
