package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.text.shared.testing.PassthroughParser;
import com.google.gwt.text.shared.testing.PassthroughRenderer;

public class TextBoxBase extends ValueBoxBase<String> {

    protected TextBoxBase(final Element element) {
        super(element, PassthroughRenderer.instance(), PassthroughParser.instance());
    }
}
