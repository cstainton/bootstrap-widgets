package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.UIObject;

public class ScrollSpy {

    private final Element spyOn;

    public static ScrollSpy scrollSpy(String selector) {
        return new ScrollSpy(Document.get().getBody(), selector);
    }

    public static ScrollSpy scrollSpy(UIObject spyOn, String selector) {
        return new ScrollSpy(spyOn.getElement(), selector);
    }

    public static ScrollSpy scrollSpy(Element spyOn, String selector) {
        return new ScrollSpy(spyOn, selector);
    }

    private ScrollSpy(Element spyOn, String selector) {
        this.spyOn = spyOn;
        this.spyOn.setAttribute("data-bs-spy", "scroll");
        this.spyOn.setAttribute("data-bs-target", selector == null ? "" : selector);
        this.spyOn.setAttribute("tabindex", "0");
        init(this.spyOn);
    }

    public void refresh() {
        refresh(spyOn);
    }

    private static native void init(Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.ScrollSpy) {
            $wnd.bootstrap.ScrollSpy.getOrCreateInstance(element);
        }
    }-*/;

    private static native void refresh(Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.ScrollSpy) {
            var instance = $wnd.bootstrap.ScrollSpy.getOrCreateInstance(element);
            if (instance && instance.refresh) {
                instance.refresh();
            }
        }
    }-*/;
}
