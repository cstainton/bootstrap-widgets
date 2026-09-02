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
        init(this.spyOn);
    }

    public void refresh() {
        refresh(spyOn);
    }

    public void dispose() {
        dispose(spyOn);
    }

    private static String targetSelector(HasId target) {
        String id = target == null ? null : target.getId();
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ScrollSpy target element must have id");
        }
        return "#" + id;
    }

    private static native void init(Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.ScrollSpy) {
            $wnd.bootstrap.ScrollSpy.getOrCreateInstance(element);
        }
    }-*/;

    private static native void refresh(Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.ScrollSpy) {
            var instance = $wnd.bootstrap.ScrollSpy.getOrCreateInstance(element);
            if (instance && instance.refresh) {
                instance.refresh();
            }
        }
    }-*/;

    private static native void dispose(Element element) /*-{
        if ($wnd.bootstrap && $wnd.bootstrap.ScrollSpy) {
            var instance = $wnd.bootstrap.ScrollSpy.getInstance(element);
            if (instance) {
                instance.dispose();
            }
        }
    }-*/;
}
