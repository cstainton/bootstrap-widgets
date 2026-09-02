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

public class Carousel extends ElementPanel {

    private final CarouselInner inner = new CarouselInner();

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
        getElement().setAttribute("data-bs-interval", String.valueOf(intervalMs));
    }

    public void setWrap(boolean wrap) {
        getElement().setAttribute("data-bs-wrap", String.valueOf(wrap));
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
}
