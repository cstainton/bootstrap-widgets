package org.gwtbootstrap5.client.ui;

public class CheckBox extends ElementPanel {

    private final Input input = new Input("checkbox");
    private final FormLabel label = new FormLabel();

    public CheckBox() {
        this("");
    }

    public CheckBox(String text) {
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

    public void setValue(boolean value) {
        input.getElement().setPropertyBoolean("checked", value);
    }

    protected Input getInput() {
        return input;
    }
}
