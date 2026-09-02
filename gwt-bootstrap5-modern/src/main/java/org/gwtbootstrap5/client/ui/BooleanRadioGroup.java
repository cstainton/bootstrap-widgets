package org.gwtbootstrap5.client.ui;

public class BooleanRadioGroup extends StringRadioGroup {

    public BooleanRadioGroup(String name) {
        super(name);
    }

    public RadioButton addRadio(Boolean value, String label) {
        return super.addRadio(value == null ? null : value.toString(), label);
    }

    public Boolean getBooleanValue() {
        String value = getValue();
        return value == null || value.isEmpty() ? null : Boolean.valueOf(value);
    }

    public void setValue(Boolean value) {
        super.setValue(value == null ? null : value.toString());
    }
}
