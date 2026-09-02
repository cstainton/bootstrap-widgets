package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.ValueBoxBase;

public class SuggestBox extends com.google.gwt.user.client.ui.SuggestBox {

    public SuggestBox() {
        super();
        styleInput();
    }

    public SuggestBox(SuggestOracle oracle) {
        super(oracle);
        styleInput();
    }

    public SuggestBox(SuggestOracle oracle, ValueBoxBase<String> box) {
        super(oracle, box);
        styleInput();
    }

    public SuggestBox(SuggestOracle oracle, ValueBoxBase<String> box, SuggestionDisplay display) {
        super(oracle, box, display);
        styleInput();
    }

    private void styleInput() {
        getTextBox().addStyleName("form-control");
    }
}
