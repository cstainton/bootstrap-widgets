package com.google.gwt.user.client.ui;

public interface Focusable {
    int getTabIndex();

    void setAccessKey(char key);

    void setFocus(boolean focused);

    void setTabIndex(int index);
}
