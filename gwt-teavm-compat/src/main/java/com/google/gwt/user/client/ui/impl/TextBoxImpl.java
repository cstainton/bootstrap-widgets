package com.google.gwt.user.client.ui.impl;

import com.google.gwt.dom.client.Element;
import org.teavm.jso.JSBody;
import org.teavm.jso.dom.html.HTMLElement;

/**
 * Selection and cursor access for text inputs and textareas. GWT hides these behind
 * a deferred-binding impl for old-IE; on TeaVM one standards-based implementation does.
 */
public class TextBoxImpl {

    public int getCursorPos(final Element element) {
        return selectionStart(element.unwrap());
    }

    public int getSelectionLength(final Element element) {
        return selectionEnd(element.unwrap()) - selectionStart(element.unwrap());
    }

    public int getTextAreaCursorPos(final Element element) {
        return getCursorPos(element);
    }

    public int getTextAreaSelectionLength(final Element element) {
        return getSelectionLength(element);
    }

    public void setSelectionRange(final Element element, final int pos, final int length) {
        select(element.unwrap(), pos, pos + length);
    }

    @JSBody(params = {"el"}, script = "return el.selectionStart == null ? 0 : el.selectionStart;")
    private static native int selectionStart(HTMLElement el);

    @JSBody(params = {"el"}, script = "return el.selectionEnd == null ? 0 : el.selectionEnd;")
    private static native int selectionEnd(HTMLElement el);

    @JSBody(params = {"el", "start", "end"}, script = "el.setSelectionRange(start, end);")
    private static native void select(HTMLElement el, int start, int end);
}
