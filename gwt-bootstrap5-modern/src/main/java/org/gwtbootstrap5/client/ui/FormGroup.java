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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap5.client.ui.base.HasSize;
import org.gwtbootstrap5.client.ui.base.HasValidationState;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.FormGroupSize;
import org.gwtbootstrap5.client.ui.constants.ValidationState;

public class FormGroup extends ElementPanel implements HasSize<FormGroupSize>, HasValidationState {

    private FormLabel label;
    private Element controlElement;

    public FormGroup() {
        super("div");
        addStyleName("mb-3");
    }

    @Override
    public void add(Widget child) {
        super.add(child);
        if (child instanceof FormLabel) {
            label = (FormLabel) child;
        } else {
            Element candidate = formControlElement(child);
            if (candidate != null && controlElement == null) {
                controlElement = candidate;
            }
        }
        associateLabelAndControl();
    }

    @Override
    public void setSize(FormGroupSize size) {
        StyleHelper.addUniqueEnumStyleName(this, FormGroupSize.class, size);
    }

    @Override
    public FormGroupSize getSize() {
        return FormGroupSize.fromStyleName(getStyleName());
    }

    @Override
    public void setValidationState(ValidationState state) {
        StyleHelper.addUniqueEnumStyleName(this, ValidationState.class, state);
    }

    @Override
    public ValidationState getValidationState() {
        return ValidationState.fromStyleName(getStyleName());
    }

    private Element formControlElement(Widget child) {
        if (child instanceof SuggestBox) {
            return ((SuggestBox) child).getTextBox().getElement();
        }
        Element element = child.getElement();
        String tagName = element.getTagName();
        if ("input".equalsIgnoreCase(tagName)
                || "textarea".equalsIgnoreCase(tagName)
                || "select".equalsIgnoreCase(tagName)) {
            return element;
        }
        return null;
    }

    private void associateLabelAndControl() {
        if (label == null || controlElement == null) {
            return;
        }
        if (controlElement.getId() == null || controlElement.getId().isEmpty()) {
            controlElement.setId(Document.get().createUniqueId());
        }
        label.setFor(controlElement.getId());
    }
}
