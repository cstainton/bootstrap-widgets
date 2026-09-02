/*
 * TeaVM port of the Bootstrap 5 widget of the same name.
 *
 * Identical to the GWT widget in package, API and behaviour; the only difference is
 * that Bootstrap's JavaScript is reached through {@link BootstrapJs} (TeaVM @JSBody)
 * rather than JSNI, which TeaVM cannot compile. When the GWT module moves its JSNI
 * behind a shared interface, this file collapses back into that one definition.
 */
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
        BootstrapJs.call("Carousel", getElement(), "cycle");
    }

    public void pauseCarousel() {
        BootstrapJs.call("Carousel", getElement(), "pause");
    }

    public void goToPrev() {
        BootstrapJs.call("Carousel", getElement(), "prev");
    }

    public void goToNext() {
        BootstrapJs.call("Carousel", getElement(), "next");
    }

    public void jumpToSlide(int index) {
        BootstrapJs.call("Carousel", getElement(), "to", index);
    }


}
