/*
 * #%L
 * GWT Bootstrap Modern
 * %%
 * Copyright (C) 2026 Carl Stainton
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
import com.google.gwt.user.client.ui.UIObject;

public class ScrollSpy {

    private final Element spyOn;

    public static ScrollSpy scrollSpy(String selector) {
        return new ScrollSpy(Document.get().getBody(), selector);
    }

    public static ScrollSpy scrollSpy(UIObject spyOn, String selector) {
        return new ScrollSpy(spyOn.getElement(), selector);
    }

    public static ScrollSpy scrollSpy(Element spyOn, String selector) {
        return new ScrollSpy(spyOn, selector);
    }

    private ScrollSpy(Element spyOn, String selector) {
        this.spyOn = spyOn;
        this.spyOn.setAttribute("data-bs-spy", "scroll");
        this.spyOn.setAttribute("data-bs-target", selector == null ? "" : selector);
        this.spyOn.setAttribute("tabindex", "0");
        BootstrapJs.init("ScrollSpy", this.spyOn);
    }

    public void refresh() {
        BootstrapJs.call("ScrollSpy", spyOn, "refresh");
    }


}
