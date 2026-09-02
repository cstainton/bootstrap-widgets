package org.gwtbootstrap5.client.ui;

public class CarouselIndicator extends ElementPanel {

    public CarouselIndicator() {
        super("button");
        getElement().setAttribute("type", "button");
    }

    public CarouselIndicator(String targetId, int slideIndex) {
        this();
        setDataTarget("#" + targetId);
        setDataSlideTo(String.valueOf(slideIndex));
        getElement().setAttribute("aria-label", "Slide " + (slideIndex + 1));
    }

    public void setDataSlideTo(final String dataSlideTo) {
        getElement().setAttribute("data-bs-slide-to", dataSlideTo);
    }

    public String getDataSlideTo() {
        return getElement().getAttribute("data-bs-slide-to");
    }

    public void setActive(boolean active) {
        setStyleName("active", active);
        if (active) {
            getElement().setAttribute("aria-current", "true");
        } else {
            getElement().removeAttribute("aria-current");
        }
    }

    public boolean isActive() {
        return getStyleName().contains("active");
    }
}
