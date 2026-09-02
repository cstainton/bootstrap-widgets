package com.google.gwt.user.client.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/** Ordered collection of a panel's logical children. */
public class WidgetCollection implements Iterable<Widget> {

    private final List<Widget> widgets = new ArrayList<>();
    private final HasWidgets parent;

    public WidgetCollection(final HasWidgets parent) {
        this.parent = parent;
    }

    public void add(final Widget widget) {
        insert(widget, size());
    }

    public void insert(final Widget widget, final int beforeIndex) {
        if (beforeIndex < 0 || beforeIndex > size()) {
            throw new IndexOutOfBoundsException("beforeIndex=" + beforeIndex + ", size=" + size());
        }
        widgets.add(beforeIndex, widget);
    }

    public boolean contains(final Widget widget) {
        return widgets.contains(widget);
    }

    public int indexOf(final Widget widget) {
        return widgets.indexOf(widget);
    }

    public Widget get(final int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size());
        }
        return widgets.get(index);
    }

    public void remove(final Widget widget) {
        final int index = indexOf(widget);
        if (index == -1) {
            throw new NoSuchElementException();
        }
        remove(index);
    }

    public void remove(final int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size());
        }
        widgets.remove(index);
    }

    public int size() {
        return widgets.size();
    }

    @Override
    public Iterator<Widget> iterator() {
        return widgets.iterator();
    }
}
