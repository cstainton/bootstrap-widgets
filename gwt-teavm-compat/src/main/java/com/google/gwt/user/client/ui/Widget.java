package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.events.MouseEvent;
import org.teavm.jso.dom.events.Registration;

public class Widget implements IsWidget, HasClickHandlers {
    private Element element;
    private Widget parent;

    @Override
    public Widget asWidget() {
        return this;
    }

    public Element getElement() {
        return element;
    }

    protected void setElement(final Element element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
        this.element = element;
    }

    public Widget getParent() {
        return parent;
    }

    void setParent(final Widget parent) {
        this.parent = parent;
    }

    public void removeFromParent() {
        if (parent instanceof HasWidgets) {
            ((HasWidgets) parent).remove(this);
        } else if (element != null) {
            element.removeFromParent();
            parent = null;
        }
    }

    public void setStyleName(final String styleName) {
        element.setClassName(styleName);
    }

    public void setStyleName(final String styleName, final boolean add) {
        if (add) {
            addStyleName(styleName);
        } else {
            removeStyleName(styleName);
        }
    }

    public String getStyleName() {
        return element.getClassName();
    }

    public void addStyleName(final String styleName) {
        element.addClassName(styleName);
    }

    public void removeStyleName(final String styleName) {
        element.removeClassName(styleName);
    }

    public void setVisible(final boolean visible) {
        element.getStyle().setDisplay(visible ? null : com.google.gwt.dom.client.Style.Display.NONE);
    }

    public void setTitle(final String title) {
        element.setAttribute("title", title);
    }

    public void setWidth(final String width) {
        element.getStyle().setProperty("width", width);
    }

    public void setHeight(final String height) {
        element.getStyle().setProperty("height", height);
    }

    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        if (handler == null) {
            throw new IllegalArgumentException("handler must not be null");
        }
        final EventListener<MouseEvent> listener = event -> handler.onClick(new ClickEvent(event));
        final Registration registration = getElement().unwrap().onEvent(MouseEvent.CLICK, listener);
        return registration::dispose;
    }
}
