package org.gwtbootstrap5.client.ui;

public class ListBox extends com.google.gwt.user.client.ui.ListBox {

    public ListBox() {
        super();
        addStyleName("form-select");
    }

    public ListBox(boolean isMultipleSelect) {
        this();
        setMultipleSelect(isMultipleSelect);
    }
}
