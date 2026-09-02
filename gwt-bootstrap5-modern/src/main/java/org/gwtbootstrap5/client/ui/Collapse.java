package org.gwtbootstrap5.client.ui;

public class Collapse extends ElementPanel {

    public Collapse() {
        super("div");
        addStyleName("collapse");
    }

    public void setShown(boolean shown) {
        setStyleName("show", shown);
    }

    public void setIn(boolean in) {
        setShown(in);
    }

    public boolean isShown() {
        return getStyleName().contains("show");
    }

    public boolean isHidden() {
        return !isShown();
    }

    public boolean isCollapsing() {
        return getStyleName().contains("collapsing");
    }

    public void show() {
        show(getElement());
    }

    public void hide() {
        hide(getElement());
    }

    public void toggle() {
        toggle(getElement());
    }

    private static native void show(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Collapse) {
            $wnd.bootstrap.Collapse.getOrCreateInstance(element, {toggle: false}).show();
        }
    }-*/;

    private static native void hide(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Collapse) {
            $wnd.bootstrap.Collapse.getOrCreateInstance(element, {toggle: false}).hide();
        }
    }-*/;

    private static native void toggle(com.google.gwt.dom.client.Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.Collapse) {
            $wnd.bootstrap.Collapse.getOrCreateInstance(element, {toggle: false}).toggle();
        }
    }-*/;
}
