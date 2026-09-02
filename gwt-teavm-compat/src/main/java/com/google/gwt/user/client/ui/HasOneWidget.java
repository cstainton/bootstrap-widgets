package com.google.gwt.user.client.ui;

public interface HasOneWidget {
    Widget getWidget();

    void setWidget(Widget widget);

    void setWidget(IsWidget widget);
}
