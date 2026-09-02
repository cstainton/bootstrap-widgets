package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class Tooltip extends ElementPanel {

    public Tooltip() {
        super("span");
        setStyleName("d-inline-block");
        getElement().setAttribute("data-bs-toggle", "tooltip");
    }

    public Tooltip(String title) {
        this();
        setTitle(title);
    }

    public Tooltip(Widget widget, String title) {
        this(title);
        setWidget(widget);
    }

    public void setWidget(Widget widget) {
        clear();
        add(widget);
    }

    public void setTitle(String title) {
        getElement().setAttribute("title", title == null ? "" : title);
    }

    public void init() {
        init(getElement());
    }

    public void show() {
        show(getElement());
    }

    public void hide() {
        hide(getElement());
    }

    private static native void init(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Tooltip) {
            $wnd.bootstrap.Tooltip.getOrCreateInstance(element);
        }
    }-*/;

    private static native void show(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Tooltip) {
            $wnd.bootstrap.Tooltip.getOrCreateInstance(element).show();
        }
    }-*/;

    private static native void hide(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Tooltip) {
            $wnd.bootstrap.Tooltip.getOrCreateInstance(element).hide();
        }
    }-*/;
}
