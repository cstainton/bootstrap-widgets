package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.constants.ButtonType;

public class Button extends Widget implements HasHTML {
    public Button() {
        setElement(Document.get().createPushButtonElement());
        setType(ButtonType.DEFAULT);
        addStyleName("btn");
        getElement().setAttribute("type", "button");
    }

    public Button(final String text) {
        this();
        setText(text);
    }

    public Button(final String text, final ClickHandler handler) {
        this(text);
        addClickHandler(handler);
    }

    public void setType(final ButtonType type) {
        for (final ButtonType value : ButtonType.values()) {
            removeStyleName(value.getCssName());
        }
        addStyleName((type == null ? ButtonType.DEFAULT : type).getCssName());
    }

    @Override
    public String getText() {
        return getElement().getInnerText();
    }

    @Override
    public void setText(final String text) {
        getElement().setInnerText(text);
    }

    @Override
    public String getHTML() {
        return getElement().getInnerHTML();
    }

    @Override
    public void setHTML(final String html) {
        getElement().setInnerHTML(html);
    }
}
