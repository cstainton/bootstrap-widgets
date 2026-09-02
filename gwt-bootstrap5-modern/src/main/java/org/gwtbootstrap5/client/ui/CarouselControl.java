package org.gwtbootstrap5.client.ui;

public class CarouselControl extends ElementPanel {

    public CarouselControl(String targetId, boolean previous) {
        super("button");
        setStyleName(previous ? "carousel-control-prev" : "carousel-control-next");
        getElement().setAttribute("type", "button");
        getElement().setAttribute("data-bs-target", "#" + targetId);
        getElement().setAttribute("data-bs-slide", previous ? "prev" : "next");
        setHTML("<span class=\"" + (previous ? "carousel-control-prev-icon" : "carousel-control-next-icon") + "\" aria-hidden=\"true\"></span>"
                + "<span class=\"visually-hidden\">" + (previous ? "Previous" : "Next") + "</span>");
    }
}
