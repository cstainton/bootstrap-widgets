package org.gwtbootstrap5.teavm.ui;

import com.google.gwt.dom.client.Element;
import org.gwtbootstrap5.teavm.dom.TeaVmDomElement;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.events.MouseEvent;
import org.teavm.jso.dom.events.Registration;
import org.teavm.jso.dom.html.HTMLElement;

/**
 * Lightweight TeaVM widget facade modelled after the GWT Widget surface.
 */
public class Widget extends com.google.gwt.user.client.ui.Widget {

    protected Widget(final String tagName) {
        this(TeaVmDomElement.create(tagName));
    }

    protected Widget(final TeaVmDomElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
        setElement(new Element(element.unwrap()));
    }

    public HTMLElement unwrap() {
        return getElement().unwrap();
    }

    public Widget setId(final String id) {
        getElement().setId(id);
        return this;
    }

    public Widget setAttribute(final String name, final String value) {
        getElement().setAttribute(name, value);
        return this;
    }

    public Registration onClick(final Runnable handler) {
        if (handler == null) {
            throw new IllegalArgumentException("handler must not be null");
        }
        return unwrap().onClick(new EventListener<MouseEvent>() {
            @Override
            public void handleEvent(final MouseEvent event) {
                handler.run();
            }
        });
    }

}
