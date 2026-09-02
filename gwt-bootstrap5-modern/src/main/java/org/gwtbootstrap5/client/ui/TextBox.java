package org.gwtbootstrap5.client.ui;

public class TextBox extends com.google.gwt.user.client.ui.TextBox {

    public TextBox() {
        addStyleName("form-control");
    }

    public TextBox(String text) {
        this();
        setText(text);
    }

    public void clear() {
        setValue(null);
    }

    public void setPlaceholder(String placeholder) {
        getElement().setAttribute("placeholder", placeholder == null ? "" : placeholder);
    }

    public String getPlaceholder() {
        return getElement().getAttribute("placeholder");
    }
}
