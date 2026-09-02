package org.gwtbootstrap5.client.ui;

public class CarouselIndicator extends ElementPanel {

    public CarouselIndicator(String targetId, int slideIndex) {
        super("button");
        getElement().setAttribute("type", "button");
        getElement().setAttribute("data-bs-target", "#" + targetId);
        getElement().setAttribute("data-bs-slide-to", String.valueOf(slideIndex));
        getElement().setAttribute("aria-label", "Slide " + (slideIndex + 1));
    }

    public void setActive(boolean active) {
        setStyleName("active", active);
        if (active) {
            getElement().setAttribute("aria-current", "true");
        } else {
            getElement().removeAttribute("aria-current");
        }
    }
}
