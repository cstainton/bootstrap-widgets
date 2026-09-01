package com.google.gwt.user.client.ui;

public interface HasWidgets extends Iterable<Widget> {
    void add(Widget widget);

    void clear();

    boolean remove(Widget widget);
}
