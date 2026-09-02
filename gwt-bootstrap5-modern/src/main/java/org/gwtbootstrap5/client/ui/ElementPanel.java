package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

class ElementPanel extends ComplexPanel implements HasText {

    ElementPanel(String tagName) {
        setElement(Document.get().createElement(tagName));
    }

    @Override
    public void add(Widget child) {
        add(child, getElement());
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
