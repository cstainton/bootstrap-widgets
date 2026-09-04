/*
 * #%L
 * GWT Bootstrap
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
package io.instanto.widgets.processor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;

/** Which handler interface an event belongs to, and how a widget takes one. */
final class Handlers {

    static final class Binding {
        final String adder;
        final String handler;
        final String method;
        final boolean generic;

        Binding(final String adder, final String handler, final String method,
                final boolean generic) {
            this.adder = adder;
            this.handler = handler;
            this.method = method;
            this.generic = generic;
        }
    }

    private static final Map<String, Binding> BY_EVENT = new LinkedHashMap<>();

    static {
        dom("ClickEvent", "addClickHandler", "ClickHandler", "onClick");
        dom("DoubleClickEvent", "addDoubleClickHandler", "DoubleClickHandler", "onDoubleClick");
        dom("ChangeEvent", "addChangeHandler", "ChangeHandler", "onChange");
        dom("KeyUpEvent", "addKeyUpHandler", "KeyUpHandler", "onKeyUp");
        dom("KeyDownEvent", "addKeyDownHandler", "KeyDownHandler", "onKeyDown");
        dom("BlurEvent", "addBlurHandler", "BlurHandler", "onBlur");
        dom("FocusEvent", "addFocusHandler", "FocusHandler", "onFocus");
        BY_EVENT.put("com.google.gwt.event.logical.shared.ValueChangeEvent",
                new Binding("addValueChangeHandler",
                        "com.google.gwt.event.logical.shared.ValueChangeHandler",
                        "onValueChange", true));
    }

    private static void dom(final String event, final String adder, final String handler,
            final String method) {
        BY_EVENT.put("com.google.gwt.event.dom.client." + event,
                new Binding(adder, "com.google.gwt.event.dom.client." + handler, method, false));
    }

    private Handlers() {
    }

    static Binding forEvent(final String event) {
        return BY_EVENT.get(event);
    }

    /** The ui:field names a @UiHandler annotation lists. */
    static List<String> namedFields(final AnnotationMirror mirror) {
        final List<String> names = new ArrayList<>();
        for (final Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
                : mirror.getElementValues().entrySet()) {
            if (!entry.getKey().getSimpleName().contentEquals("value")) {
                continue;
            }
            final Object value = entry.getValue().getValue();
            if (value instanceof List) {
                for (final Object item : (List<?>) value) {
                    names.add(String.valueOf(((AnnotationValue) item).getValue()));
                }
            } else {
                names.add(String.valueOf(value));
            }
        }
        return names;
    }
}
