
package com.google.gwt.dom.client;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.dom.html.HTMLElement;

/**
 * A list of DOM nodes, as returned by {@code getElementsByTagName} and
 * {@code querySelectorAll}.
 *
 * @param <T> the node type the list holds
 */
public class NodeList<T> {

    private final JSObject list;

    public NodeList(final JSObject list) {
        this.list = list;
    }

    public int getLength() {
        return list == null ? 0 : length(list);
    }

    @SuppressWarnings("unchecked")
    public T getItem(final int index) {
        if (list == null) {
            return null;
        }
        final HTMLElement item = item(list, index);
        return item == null ? null : (T) new Element(item);
    }

    @JSBody(params = {"list"}, script = "return list.length | 0;")
    private static native int length(JSObject list);

    @JSBody(params = {"list", "index"}, script = "return list[index];")
    private static native HTMLElement item(JSObject list, int index);
}
