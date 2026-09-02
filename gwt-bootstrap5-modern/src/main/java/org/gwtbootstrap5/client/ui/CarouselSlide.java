package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class CarouselSlide extends ElementPanel {

    public CarouselSlide() {
        super("div");
        addStyleName("carousel-item");
    }

    public CarouselSlide(Widget child) {
        this();
        add(child);
    }

    public void setActive(boolean active) {
        setStyleName("active", active);
    }
}
