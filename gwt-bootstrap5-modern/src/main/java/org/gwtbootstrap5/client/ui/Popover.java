package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class Popover extends Tooltip {

    public Popover() {
        super();
        getElement().setAttribute("data-bs-toggle", "popover");
    }

    public Popover(String title, String content) {
        this();
        setTitle(title);
        setContent(content);
    }

    public Popover(Widget widget, String title, String content) {
        this(title, content);
        setWidget(widget);
    }

    public void setContent(String content) {
        getElement().setAttribute("data-bs-content", content == null ? "" : content);
    }

    @Override
    public void init() {
        init(getElement());
    }

    @Override
    public void show() {
        show(getElement());
    }

    @Override
    public void hide() {
        hide(getElement());
    }

    private static native void init(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Popover) {
            $wnd.bootstrap.Popover.getOrCreateInstance(element);
        }
    }-*/;

    private static native void show(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Popover) {
            $wnd.bootstrap.Popover.getOrCreateInstance(element).show();
        }
    }-*/;

    private static native void hide(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Popover) {
            $wnd.bootstrap.Popover.getOrCreateInstance(element).hide();
        }
    }-*/;
}
