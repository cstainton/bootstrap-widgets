package org.gwtbootstrap5.teavm.ui;

public class ListBox extends Panel {

    public ListBox() {
        super("select");
        addStyleName("form-select");
    }

    public ListBox(final boolean multipleSelect) {
        this();
        setMultipleSelect(multipleSelect);
    }

    public ListBox setMultipleSelect(final boolean multipleSelect) {
        if (multipleSelect) {
            setAttribute("multiple", "multiple");
        } else {
            getElement().removeAttribute("multiple");
        }
        return this;
    }

    public ListBox addItem(final String text, final String value) {
        final Widget option = new Widget("option");
        option.getElement().setInnerText(text == null ? "" : text);
        option.getElement().setAttribute("value", value == null ? "" : value);
        add(option);
        return this;
    }
}
