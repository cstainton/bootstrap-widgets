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

import org.gwtbootstrap5.client.ui.base.HasSize;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.InputGroupSize;

public class InputGroup extends ElementPanel implements HasSize<InputGroupSize> {

    public InputGroup() {
        super("div");
        addStyleName("input-group");
    }

    @Override
    public void setSize(InputGroupSize size) {
        StyleHelper.addUniqueEnumStyleName(this, InputGroupSize.class, size == null ? InputGroupSize.DEFAULT : size);
    }

    @Override
    public InputGroupSize getSize() {
        return InputGroupSize.fromStyleName(getStyleName());
    }

    public void setLarge(boolean large) {
        setSize(large ? InputGroupSize.LARGE : InputGroupSize.DEFAULT);
    }

    public void setSmall(boolean small) {
        setSize(small ? InputGroupSize.SMALL : InputGroupSize.DEFAULT);
    }

    public boolean isLarge() {
        return getSize() == InputGroupSize.LARGE;
    }

    public boolean isSmall() {
        return getSize() == InputGroupSize.SMALL;
    }
}
