package org.gwtbootstrap3.teavm.ui;

import org.gwtbootstrap3.teavm.dom.TeaVmDomElement;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.events.MouseEvent;
import org.teavm.jso.dom.events.Registration;
import org.teavm.jso.dom.html.HTMLElement;

/**
 * Lightweight TeaVM widget facade modelled after the GWT Widget surface.
 */
public class Widget {

    private final TeaVmDomElement element;

    protected Widget(final String tagName) {
        this(TeaVmDomElement.create(tagName));
    }

    protected Widget(final TeaVmDomElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
        this.element = element;
    }

    public TeaVmDomElement getElement() {
        return element;
    }

    public HTMLElement unwrap() {
        return element.unwrap();
    }

    public Widget addStyleName(final String styleName) {
        element.addClass(styleName);
        return this;
    }

    public Widget removeStyleName(final String styleName) {
        element.removeClass(styleName);
        return this;
    }

    public Widget setStyleName(final String styleName) {
        element.setClassName(styleName);
        return this;
    }

    public Widget setVisible(final boolean visible) {
        element.setVisible(visible);
        return this;
    }

    public Widget setTitle(final String title) {
        element.setAttribute("title", title);
        return this;
    }

    public Widget setId(final String id) {
        element.setAttribute("id", id);
        return this;
    }

    public Widget setAttribute(final String name, final String value) {
        element.setAttribute(name, value);
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

    public void removeFromParent() {
        element.removeFromParent();
    }
}
