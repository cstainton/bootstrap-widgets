package org.gwtbootstrap5.client.ui;

import java.util.List;

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
import org.gwtbootstrap5.client.ui.base.HasDataParent;
import org.gwtbootstrap5.client.ui.base.HasDataTarget;
import org.gwtbootstrap5.client.ui.base.HasDataToggle;
import org.gwtbootstrap5.client.ui.base.mixin.DataParentMixin;
import org.gwtbootstrap5.client.ui.base.mixin.DataTargetMixin;
import org.gwtbootstrap5.client.ui.base.mixin.DataToggleMixin;
import org.gwtbootstrap5.client.ui.constants.Attributes;
import org.gwtbootstrap5.client.ui.constants.Toggle;

class ElementPanel extends ComplexWidget implements HasHTML, HasClickHandlers, HasDoubleClickHandlers, HasDataParent, HasDataTarget, HasDataToggle {

    private final DataParentMixin<ElementPanel> parentMixin = new DataParentMixin<ElementPanel>(this);
    private final DataTargetMixin<ElementPanel> targetMixin = new DataTargetMixin<ElementPanel>(this);
    private final DataToggleMixin<ElementPanel> toggleMixin = new DataToggleMixin<ElementPanel>(this);

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

    @Override
    public void setDataParent(String dataParent) {
        parentMixin.setDataParent(dataParent);
    }

    @Override
    public String getDataParent() {
        return parentMixin.getDataParent();
    }

    @Override
    public void setDataTargetWidget(Widget widget) {
        targetMixin.setDataTargetWidget(widget);
    }

    @Override
    public void setDataTargetWidgets(List<Widget> widgets) {
        targetMixin.setDataTargetWidgets(widgets);
    }

    @Override
    public void setDataTarget(String dataTarget) {
        targetMixin.setDataTarget(dataTarget);
    }

    @Override
    public String getDataTarget() {
        return targetMixin.getDataTarget();
    }

    @Override
    public void setDataToggle(Toggle toggle) {
        toggleMixin.setDataToggle(toggle);
    }

    public void setDataToggle(String toggle) {
        if (toggle == null || toggle.isEmpty()) {
            setDataToggle((Toggle) null);
        } else {
            getElement().setAttribute(Attributes.DATA_TOGGLE, toggle);
        }
    }

    @Override
    public Toggle getDataToggle() {
        return toggleMixin.getDataToggle();
    }
}
