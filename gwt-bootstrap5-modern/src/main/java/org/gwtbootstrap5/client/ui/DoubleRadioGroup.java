package org.gwtbootstrap5.client.ui;

public class DoubleRadioGroup extends StringRadioGroup {

    public DoubleRadioGroup(String name) {
        super(name);
    }

    public RadioButton addRadio(Double value, String label) {
        return super.addRadio(value == null ? null : value.toString(), label);
    }

    public Double getDoubleValue() {
        String value = getValue();
        return value == null || value.isEmpty() ? null : Double.valueOf(value);
    }

    public void setValue(Double value) {
        super.setValue(value == null ? null : value.toString());
    }
}
