package org.gwtbootstrap5.teavm.ui;

public class CarouselCaption extends Panel {

    public CarouselCaption() {
        super("div");
        setStyleName("carousel-caption d-none d-md-block");
    }

    @Override
    public CarouselCaption add(final Widget child) {
        super.add(child);
        return this;
    }
}
