package com.google.gwt.user.client.ui;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Widget that wraps another widget and hides its API, mirroring GWT's {@code Composite}.
 */
public abstract class Composite extends Widget {

    private Widget widget;

    protected Widget getWidget() {
        return widget;
    }

    protected void initWidget(final Widget widget) {
        if (this.widget != null) {
            throw new IllegalStateException("initWidget() may only be called once");
        }
        if (widget == null) {
            throw new IllegalArgumentException("widget must not be null");
        }
        widget.removeFromParent();
        this.widget = widget;
        setElement(widget.getElement());
        widget.setParent(this);
    }

    @Override
    public void fireEvent(final GwtEvent<?> event) {
        super.fireEvent(event);
        if (widget != null) {
            widget.fireEvent(event);
        }
    }
}
