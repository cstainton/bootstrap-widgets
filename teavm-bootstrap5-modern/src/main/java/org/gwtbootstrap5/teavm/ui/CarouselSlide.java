package org.gwtbootstrap5.teavm.ui;

public class CarouselSlide extends Panel {

    public CarouselSlide() {
        super("div");
        addStyleName("carousel-item");
    }

    public CarouselSlide(final Widget child) {
        this();
        add(child);
    }

    @Override
    public CarouselSlide add(final Widget child) {
        super.add(child);
        return this;
    }

    public CarouselSlide setActive(final boolean active) {
        setStyleName("active", active);
        return this;
    }
}
