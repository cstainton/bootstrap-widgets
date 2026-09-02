package org.gwtbootstrap5.client.ui;

public class IntegerRadioGroup extends StringRadioGroup {

    public IntegerRadioGroup(String name) {
        super(name);
    }

    public RadioButton addRadio(Integer value, String label) {
        return super.addRadio(value == null ? null : value.toString(), label);
    }

    public Integer getIntegerValue() {
        String value = getValue();
        return value == null || value.isEmpty() ? null : Integer.valueOf(value);
    }

    public void setValue(Integer value) {
        super.setValue(value == null ? null : value.toString());
    }
}
