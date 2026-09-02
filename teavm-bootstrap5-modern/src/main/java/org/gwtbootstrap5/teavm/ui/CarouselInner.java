package org.gwtbootstrap5.teavm.ui;

public class CarouselInner extends Panel {

    public CarouselInner() {
        super("div");
        addStyleName("carousel-inner");
    }

    @Override
    public CarouselInner add(final Widget child) {
        super.add(child);
        return this;
    }
}
