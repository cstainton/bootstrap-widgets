package com.google.gwt.user.client.ui;

/** A panel that accepts children at a specific index. */
public interface InsertPanel {

    void add(Widget child);

    void insert(Widget child, int beforeIndex);

    /** Adds the {@link IsWidget} overloads, as GWT's sub-interface does. */
    interface ForIsWidget extends InsertPanel {
        void add(IsWidget child);

        void insert(IsWidget child, int beforeIndex);
    }
}
