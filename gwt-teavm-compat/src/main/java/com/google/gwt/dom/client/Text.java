package com.google.gwt.dom.client;

import org.teavm.jso.JSBody;
import org.teavm.jso.dom.xml.Node;

/**
 * A DOM text node.
 *
 * <p>TeaVM's {@code Text} extends {@code Node} rather than {@code CharacterData},
 * so the data accessors go through the node's {@code data} property directly.</p>
 */
public class Text {

    private final org.teavm.jso.dom.xml.Text node;

    public Text(final org.teavm.jso.dom.xml.Text node) {
        this.node = node;
    }

    public org.teavm.jso.dom.xml.Text unwrap() {
        return node;
    }

    public String getData() {
        return nodeData(node);
    }

    public void setData(final String data) {
        setNodeData(node, data == null ? "" : data);
    }

    public void removeFromParent() {
        final Node parent = node.getParentNode();
        if (parent != null) {
            parent.removeChild(node);
        }
    }

    @JSBody(params = {"n"}, script = "return n.data;")
    private static native String nodeData(org.teavm.jso.dom.xml.Text n);

    @JSBody(params = {"n", "v"}, script = "n.data = v;")
    private static native void setNodeData(org.teavm.jso.dom.xml.Text n, String v);

    /**
     * Views this text node as an {@link Element}, so it can back a widget.
     * A text node is not an element, so element-only operations on the result are
     * meaningless; the widget classes use it only for attach and removal.
     */
    @SuppressWarnings("unchecked")
    public <T> T cast() {
        return (T) new Element(asElement(node));
    }

    @JSBody(params = {"n"}, script = "return n;")
    private static native org.teavm.jso.dom.html.HTMLElement asElement(org.teavm.jso.dom.xml.Text n);
}
