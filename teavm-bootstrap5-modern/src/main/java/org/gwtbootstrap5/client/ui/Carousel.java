/*
 * #%L
 * GWT Bootstrap Modern
 * %%
 * Copyright (C) 2026 Carl Stainton
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


}
