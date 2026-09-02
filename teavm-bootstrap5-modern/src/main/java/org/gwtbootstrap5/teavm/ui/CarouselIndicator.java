package org.gwtbootstrap5.teavm.ui;

public class CarouselIndicator extends Widget {

    public CarouselIndicator(final String targetId, final int slideIndex) {
        super("button");
        setAttribute("type", "button");
        setAttribute("data-bs-target", "#" + targetId);
        setAttribute("data-bs-slide-to", String.valueOf(slideIndex));
        setAttribute("aria-label", "Slide " + (slideIndex + 1));
    }

    public CarouselIndicator setActive(final boolean active) {
        setStyleName("active", active);
        if (active) {
            setAttribute("aria-current", "true");
        } else {
            getElement().removeAttribute("aria-current");
        }
        return this;
    }
}
