package org.gwtbootstrap5.client.ui;

public class TextArea extends com.google.gwt.user.client.ui.TextArea {

    public TextArea() {
        addStyleName("form-control");
    }

    public TextArea(String text) {
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
