package org.gwtbootstrap5.client.ui;

import java.util.LinkedHashMap;
import java.util.Map;

public class StringRadioGroup extends ElementPanel {

    private final String name;
    private final Map<RadioButton, String> values = new LinkedHashMap<RadioButton, String>();

    public StringRadioGroup(String name) {
        super("div");
        this.name = name;
        addStyleName("vstack gap-2");
    }

    public RadioButton addRadio(String value, String label) {
        RadioButton radio = new RadioButton(label);
        radio.getInput().getElement().setAttribute("name", name);
        values.put(radio, value);
        add(radio);
        return radio;
    }

    public String getValue() {
        for (Map.Entry<RadioButton, String> entry : values.entrySet()) {
            if (entry.getKey().getValue()) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void setValue(String value) {
        for (Map.Entry<RadioButton, String> entry : values.entrySet()) {
            entry.getKey().setValue(value == null ? entry.getValue() == null : value.equals(entry.getValue()));
        }
    }
}
