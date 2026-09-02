package org.gwtbootstrap5.teavm.ui;

public class CarouselIndicators extends Panel {

    public CarouselIndicators() {
        super("div");
        addStyleName("carousel-indicators");
    }

    public CarouselIndicators addIndicator(final CarouselIndicator indicator) {
        add(indicator);
        return this;
    }
}
