package com.google.gwt.user.client.ui;

/** A panel whose children are addressable by index. */
public interface IndexedPanel {

    Widget getWidget(int index);

    int getWidgetCount();

    int getWidgetIndex(Widget child);

    boolean remove(int index);

    /** Adds the {@link IsWidget} overload, as GWT's sub-interface does. */
    interface ForIsWidget extends IndexedPanel {
        int getWidgetIndex(IsWidget child);
    }
}
