package com.google.gwt.user.client.ui;

import java.util.Iterator;

/** Base class for widgets that contain other widgets. */
public abstract class Panel extends Widget implements HasWidgets {

    @Override
    public abstract void add(Widget widget);

    public void add(final IsWidget widget) {
        add(widget == null ? null : widget.asWidget());
    }

    @Override
    public void clear() {
        final Iterator<Widget> it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    @Override
    public abstract boolean remove(Widget widget);

    public boolean remove(final IsWidget widget) {
        return widget != null && remove(widget.asWidget());
    }

    /** Takes ownership of a child: sets its parent and attaches it if this panel is attached. */
    protected final void adopt(final Widget child) {
        child.setParent(this);
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        for (final Widget child : this) {
            if (!child.isAttached()) {
                child.onAttach();
            }
        }
    }

    @Override
    protected void onDetach() {
        for (final Widget child : this) {
            if (child.isAttached()) {
                child.onDetach();
            }
        }
        super.onDetach();
    }

    /** Releases a child: detaches it and clears its parent. */
    protected final void orphan(final Widget child) {
        if (child.getParent() == this) {
            child.setParent(null);
        }
    }
}
