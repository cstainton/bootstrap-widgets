package org.gwtbootstrap5.client.ui;

public class CarouselIndicators extends ElementPanel {

    public CarouselIndicators() {
        super("div");
        addStyleName("carousel-indicators");
    }

    public void addIndicator(CarouselIndicator indicator) {
        add(indicator);
    }
}
