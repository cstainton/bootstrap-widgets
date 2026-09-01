package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.base.HasType;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.LabelType;

public class Label extends Widget implements HasHTML, HasType<LabelType> {
    public Label() {
        setElement(Document.get().createSpanElement());
        setStyleName("badge");
        setType(LabelType.DEFAULT);
    }

    public Label(final String text) {
        this();
        setText(text);
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

    @Override
    public LabelType getType() {
        return LabelType.fromStyleName(getStyleName());
    }

    @Override
    public void setType(final LabelType type) {
        StyleHelper.addUniqueEnumStyleName(this, LabelType.class, type == null ? LabelType.DEFAULT : type);
    }
}
