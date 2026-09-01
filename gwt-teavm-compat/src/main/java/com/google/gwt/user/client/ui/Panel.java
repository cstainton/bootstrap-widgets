package com.google.gwt.user.client.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Panel extends Widget implements HasWidgets {
    private final List<Widget> children = new ArrayList<>();

    @Override
    public void add(final Widget widget) {
        insert(widget, children.size());
    }

    public void insert(final Widget widget, final int beforeIndex) {
        if (widget == null) {
            throw new IllegalArgumentException("widget must not be null");
        }
        widget.removeFromParent();
        final int index = Math.max(0, Math.min(beforeIndex, children.size()));
        children.add(index, widget);
        widget.setParent(this);
        if (index == children.size() - 1) {
            getElement().appendChild(widget.getElement());
        } else {
            getElement().insertBefore(widget.getElement(), children.get(index + 1).getElement());
        }
    }

    @Override
    public void clear() {
        while (!children.isEmpty()) {
            remove(children.get(children.size() - 1));
        }
    }

    @Override
    public boolean remove(final Widget widget) {
        if (widget == null || widget.getParent() != this) {
            return false;
        }
        widget.getElement().removeFromParent();
        widget.setParent(null);
        children.remove(widget);
        return true;
    }

    @Override
    public Iterator<Widget> iterator() {
        return children.iterator();
    }
}
