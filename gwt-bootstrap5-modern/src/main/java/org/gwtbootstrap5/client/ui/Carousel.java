package org.gwtbootstrap5.client.ui;

public class Carousel extends ElementPanel {

    private final CarouselInner inner = new CarouselInner();

    public Carousel() {
        super("div");
        setStyleName("carousel slide");
        getElement().setAttribute("data-bs-ride", "carousel");
        add(inner);
    }

    public CarouselInner getInner() {
        return inner;
    }

    public void setInterval(int intervalMs) {
        getElement().setAttribute("data-bs-interval", String.valueOf(intervalMs));
    }

    public void setWrap(boolean wrap) {
        getElement().setAttribute("data-bs-wrap", String.valueOf(wrap));
    }

    public void addSlide(CarouselSlide slide) {
        inner.add(slide);
    }

    public void cycleCarousel() {
        call(getElement(), "cycle");
    }

    public void pauseCarousel() {
        call(getElement(), "pause");
    }

    public void goToPrev() {
        call(getElement(), "prev");
    }

    public void goToNext() {
        call(getElement(), "next");
    }

    public void jumpToSlide(int index) {
        to(getElement(), index);
    }

    private static native void call(com.google.gwt.dom.client.Element element, String method) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Carousel) {
            var carousel = $wnd.bootstrap.Carousel.getOrCreateInstance(element);
            carousel[method]();
        }
    }-*/;

    private static native void to(com.google.gwt.dom.client.Element element, int index) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Carousel) {
            $wnd.bootstrap.Carousel.getOrCreateInstance(element).to(index);
        }
    }-*/;
}
