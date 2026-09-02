package org.gwtbootstrap5.teavm.ui;

public class CheckBox extends Panel {

    private final Input input = new Input("checkbox");
    private final FormLabel label = new FormLabel();

    public CheckBox() {
        this("");
    }

    public CheckBox(final String text) {
        super("div");
        addStyleName("form-check");
        input.removeStyleName("form-control");
        input.addStyleName("form-check-input");
        label.removeStyleName("form-label");
        label.addStyleName("form-check-label");
        label.setText(text);
        add(input);
        add(label);
    }

    public boolean getValue() {
        return input.getElement().getPropertyBoolean("checked");
    }

    public CheckBox setValue(final boolean value) {
        input.getElement().setPropertyBoolean("checked", value);
        return this;
    }

    protected Input getInput() {
        return input;
    }
}
