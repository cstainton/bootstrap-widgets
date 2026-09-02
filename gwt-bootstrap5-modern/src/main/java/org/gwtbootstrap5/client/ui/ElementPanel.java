package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasDoubleClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap5.client.ui.base.ComplexWidget;

class ElementPanel extends ComplexWidget implements HasHTML, HasClickHandlers, HasDoubleClickHandlers {

    ElementPanel(String tagName) {
        setElement(Document.get().createElement(tagName));
    }

    @Override
    public void add(Widget child) {
        add(child, getElement());
    }

    public void insert(Widget child, int beforeIndex) {
        insert(child, getElement(), beforeIndex, true);
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }

    @Override
    public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
        return addDomHandler(handler, DoubleClickEvent.getType());
    }

    @Override
    public String getText() {
        return getElement().getInnerText();
    }

    @Override
    public void setText(String text) {
        getElement().setInnerText(text == null ? "" : text);
    }

    public String getHTML() {
        return getElement().getInnerHTML();
    }

    public void setHTML(String html) {
        getElement().setInnerHTML(html == null ? "" : html);
    }
}
