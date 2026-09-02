package org.gwtbootstrap5.client.ui;

import com.google.gwt.text.shared.Renderer;
import com.google.gwt.view.client.ProvidesKey;

public class ValueListBox<T> extends com.google.gwt.user.client.ui.ValueListBox<T> {

    public ValueListBox() {
        super();
        addStyleName("form-select");
    }

    public ValueListBox(Renderer<? super T> renderer) {
        super(renderer);
        addStyleName("form-select");
    }

    public ValueListBox(Renderer<? super T> renderer, ProvidesKey<T> keyProvider) {
        super(renderer, keyProvider);
        addStyleName("form-select");
    }
}
