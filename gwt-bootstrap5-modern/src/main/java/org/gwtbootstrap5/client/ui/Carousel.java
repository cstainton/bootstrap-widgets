/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2018 GwtBootstrap3
 * %%
 * Modified from the GwtBootstrap3 original for the Bootstrap 5 track of
 * GWT Bootstrap Modern: moved to the org.gwtbootstrap5 namespace and re-targeted
 * at Bootstrap 5 markup, class names and JavaScript APIs.
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
        call(getElement(), "cycle");
    }

    public void pauseCarousel() {
        call(getElement(), "pause");
    }

    public void goToPrev() {
        call(getElement(), "prev");
    }

    public void goToNext() {
        call(getElement(), "next");
    }

    public void jumpToSlide(int index) {
        to(getElement(), index);
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
        init(getElement(), interval, pause, wrap);
    }

    @Override
    protected void onUnload() {
        BootstrapEventBridge.unbindAll(getElement());
        dispose(getElement());
        super.onUnload();
    }

    private void reconfigureIfAttached() {
        if (isAttached()) {
            init(getElement(), interval, pause, wrap);
        }
    }

    private static native void init(com.google.gwt.dom.client.Element element, int interval,
                                    String pause, boolean wrap) /*-{
        if (!$wnd.bootstrap || !$wnd.bootstrap.Carousel) {
            return;
        }
        var existing = $wnd.bootstrap.Carousel.getInstance(element);
        if (existing) {
            existing.dispose();
        }
        new $wnd.bootstrap.Carousel(element, {
            interval: interval,
            pause: pause || false,
            wrap: wrap
        });
    }-*/;

    private static native void call(com.google.gwt.dom.client.Element element, String method) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Carousel) {
            var carousel = $wnd.bootstrap.Carousel.getOrCreateInstance(element);
            carousel[method]();
        }
    }-*/;

    private static native void to(com.google.gwt.dom.client.Element element, int index) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Carousel) {
            $wnd.bootstrap.Carousel.getOrCreateInstance(element).to(index);
        }
    }-*/;

    private static native void dispose(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Carousel) {
            var instance = $wnd.bootstrap.Carousel.getInstance(element);
            if (instance) {
                instance.dispose();
            }
        }
    }-*/;
}
