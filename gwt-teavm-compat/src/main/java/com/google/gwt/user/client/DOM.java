package com.google.gwt.user.client;

import com.google.gwt.dom.client.Element;

public final class DOM {
    private DOM() {
    }

    public static void appendChild(final Element parent, final Element child) {
        parent.appendChild(child);
    }
}
