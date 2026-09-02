package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Element;

/** Base for widgets that can take focus and be disabled. */
public class FocusWidget extends Widget implements HasEnabled, Focusable {

    protected FocusWidget() {
    }

    protected FocusWidget(final Element element) {
        setElement(element);
    }

    @Override
    public boolean isEnabled() {
        return !getElement().getPropertyBoolean("disabled");
    }

    @Override
    public void setEnabled(final boolean enabled) {
        getElement().setPropertyBoolean("disabled", !enabled);
    }

    @Override
    public int getTabIndex() {
        return getElement().getPropertyInt("tabIndex");
    }

    @Override
    public void setTabIndex(final int index) {
        getElement().setPropertyInt("tabIndex", index);
    }

    @Override
    public void setAccessKey(final char key) {
        getElement().setAttribute("accesskey", String.valueOf(key));
    }

    @Override
    public void setFocus(final boolean focused) {
        if (focused) {
            getElement().focus();
        } else {
            getElement().blur();
        }
    }
}
