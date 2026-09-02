package com.google.gwt.editor.ui.client.adapters;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.HasText;

/** Adapts a {@link HasText} widget to the editor framework. */
public class HasTextEditor implements LeafValueEditor<String> {

    private final HasText hasText;

    protected HasTextEditor(final HasText hasText) {
        this.hasText = hasText;
    }

    public static HasTextEditor of(final HasText hasText) {
        return new HasTextEditor(hasText);
    }

    @Override
    public String getValue() {
        return hasText.getText();
    }

    @Override
    public void setValue(final String value) {
        hasText.setText(value);
    }
}
