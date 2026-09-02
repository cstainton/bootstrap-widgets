/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2018 GwtBootstrap3
 * %%
 * Modified from the GwtBootstrap3 original for the Bootstrap 5 track of
 * GWT Bootstrap Modern: moved to the org.gwtbootstrap5 namespace and re-targeted
 * at Bootstrap 5 markup, class names and JavaScript APIs.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
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
