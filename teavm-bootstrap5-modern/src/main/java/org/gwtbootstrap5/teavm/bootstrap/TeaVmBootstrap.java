package org.gwtbootstrap5.teavm.bootstrap;

import org.teavm.jso.JSBody;
import org.teavm.jso.dom.html.HTMLElement;

/** Thin TeaVM calls into Bootstrap 5's JavaScript API. */
public final class TeaVmBootstrap {

    private TeaVmBootstrap() {
    }

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Modal) { window.bootstrap.Modal.getOrCreateInstance(element).show(); }")
    public static native void showModal(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Modal) { window.bootstrap.Modal.getOrCreateInstance(element).hide(); }")
    public static native void hideModal(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Collapse) { window.bootstrap.Collapse.getOrCreateInstance(element, {toggle: false}).show(); }")
    public static native void showCollapse(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Collapse) { window.bootstrap.Collapse.getOrCreateInstance(element, {toggle: false}).hide(); }")
    public static native void hideCollapse(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Collapse) { window.bootstrap.Collapse.getOrCreateInstance(element, {toggle: false}).toggle(); }")
    public static native void toggleCollapse(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Tooltip) { window.bootstrap.Tooltip.getOrCreateInstance(element); }")
    public static native void initTooltip(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Tooltip) { window.bootstrap.Tooltip.getOrCreateInstance(element).show(); }")
    public static native void showTooltip(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Tooltip) { window.bootstrap.Tooltip.getOrCreateInstance(element).hide(); }")
    public static native void hideTooltip(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Popover) { window.bootstrap.Popover.getOrCreateInstance(element); }")
    public static native void initPopover(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Popover) { window.bootstrap.Popover.getOrCreateInstance(element).show(); }")
    public static native void showPopover(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Popover) { window.bootstrap.Popover.getOrCreateInstance(element).hide(); }")
    public static native void hidePopover(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Carousel) { window.bootstrap.Carousel.getOrCreateInstance(element).cycle(); }")
    public static native void cycleCarousel(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Carousel) { window.bootstrap.Carousel.getOrCreateInstance(element).pause(); }")
    public static native void pauseCarousel(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Carousel) { window.bootstrap.Carousel.getOrCreateInstance(element).prev(); }")
    public static native void prevCarousel(HTMLElement element);

    @JSBody(params = {"element"}, script = "if (window.bootstrap && window.bootstrap.Carousel) { window.bootstrap.Carousel.getOrCreateInstance(element).next(); }")
    public static native void nextCarousel(HTMLElement element);

    @JSBody(params = {"element", "index"}, script = "if (window.bootstrap && window.bootstrap.Carousel) { window.bootstrap.Carousel.getOrCreateInstance(element).to(index); }")
    public static native void toCarousel(HTMLElement element, int index);
}
