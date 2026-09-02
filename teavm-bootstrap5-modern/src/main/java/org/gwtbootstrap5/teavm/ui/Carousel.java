package org.gwtbootstrap5.teavm.ui;

import org.gwtbootstrap5.teavm.bootstrap.TeaVmBootstrap;

public class Carousel extends Panel {

    private final CarouselInner inner = new CarouselInner();

    public Carousel() {
        super("div");
        setStyleName("carousel slide");
        setAttribute("data-bs-ride", "carousel");
        add(inner);
    }

    public CarouselInner getInner() {
        return inner;
    }

    public Carousel setInterval(final int intervalMs) {
        setAttribute("data-bs-interval", String.valueOf(intervalMs));
        return this;
    }

    public Carousel setWrap(final boolean wrap) {
        setAttribute("data-bs-wrap", String.valueOf(wrap));
        return this;
    }

    public Carousel addSlide(final CarouselSlide slide) {
        inner.add(slide);
        return this;
    }

    public void cycleCarousel() {
        TeaVmBootstrap.cycleCarousel(unwrap());
    }

    public void pauseCarousel() {
        TeaVmBootstrap.pauseCarousel(unwrap());
    }

    public void goToPrev() {
        TeaVmBootstrap.prevCarousel(unwrap());
    }

    public void goToNext() {
        TeaVmBootstrap.nextCarousel(unwrap());
    }

    public void jumpToSlide(final int index) {
        TeaVmBootstrap.toCarousel(unwrap(), index);
    }
}
