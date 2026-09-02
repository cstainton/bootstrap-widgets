package org.gwtbootstrap5.teavm.ui;

public class CarouselControl extends TextWidget {

    public CarouselControl(final String targetId, final boolean previous) {
        super("button");
        setStyleName(previous ? "carousel-control-prev" : "carousel-control-next");
        setAttribute("type", "button");
        setAttribute("data-bs-target", "#" + targetId);
        setAttribute("data-bs-slide", previous ? "prev" : "next");
        setHtml("<span class=\"" + (previous ? "carousel-control-prev-icon" : "carousel-control-next-icon") + "\" aria-hidden=\"true\"></span>"
                + "<span class=\"visually-hidden\">" + (previous ? "Previous" : "Next") + "</span>");
    }
}
