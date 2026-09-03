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
import org.gwtbootstrap5.client.ui.constants.WellSize;


public class Well extends ElementPanel implements HasSize<WellSize> {

    public Well() {
        super("div");
        setStyleName("rounded bg-body-tertiary border");
        setSize(WellSize.DEFAULT);
    }

    private WellSize size = WellSize.DEFAULT;

    /**
     * Bootstrap 5 removed .well-lg and .well-sm; WellSize names the padding
     * utilities that replace them.
     */
    @Override
    public void setSize(final WellSize size) {
        StyleHelper.addUniqueEnumStyleName(this, WellSize.class, size == null ? WellSize.DEFAULT : size);
        this.size = size == null ? WellSize.DEFAULT : size;
    }

    @Override
    public WellSize getSize() {
        return size;
    }

}
