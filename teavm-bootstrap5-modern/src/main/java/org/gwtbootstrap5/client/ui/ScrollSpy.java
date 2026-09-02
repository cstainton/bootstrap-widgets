/*
 * #%L
 * GWT Bootstrap Modern
 * %%
 * Copyright (C) 2026 Carl Stainton
 * %%
 * Reimplements, over TeaVM's JSO libraries, part of the GWT client API. Class,
 * method and package names follow GWT (https://github.com/gwtproject/gwt),
 * Copyright (C) The GWT Project Authors, licensed under the Apache License,
 * Version 2.0. No GWT source is included.
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

/*
 * TeaVM implementation of the Bootstrap 5 widget of the same name.
 *
 * Identical to the GWT widget in package, API and behaviour. It exists separately only
 * because that widget reaches Bootstrap's JavaScript through JSNI, which TeaVM cannot
 * compile; the calls go through BootstrapJs instead. Keep this file in step with the
 * GWT one -- or, better, move the remaining JSNI behind a shared seam so both builds
 * can use a single definition, as BootstrapEventBridge already does for events.
 */
package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.UIObject;
import org.gwtbootstrap5.client.ui.base.HasId;

public class ScrollSpy {

    private final Element spyOn;

    public static ScrollSpy scrollSpy(String selector) {
        return new ScrollSpy(Document.get().getBody(), selector);
    }

    public static ScrollSpy scrollSpy(UIObject spyOn, String selector) {
        return new ScrollSpy(spyOn.getElement(), selector);
    }

    public static ScrollSpy scrollSpy(HasId target) {
        return new ScrollSpy(Document.get().getBody(), targetSelector(target));
    }

    public static ScrollSpy scrollSpy(UIObject spyOn, HasId target) {
        return new ScrollSpy(spyOn.getElement(), targetSelector(target));
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

    public void dispose() {
        BootstrapJs.dispose("ScrollSpy", spyOn);
    }

    private static String targetSelector(HasId target) {
        String id = target == null ? null : target.getId();
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ScrollSpy target element must have id");
        }
        return "#" + id;
    }



}
