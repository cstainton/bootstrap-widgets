package org.gwtbootstrap3.client.ui;

import com.google.gwt.user.client.ui.HasText;
import org.gwtbootstrap3.client.ui.base.HasSubText;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.html.Div;

public class PageHeader extends Div implements HasText, HasSubText {
    private String text = "";
    private String subText = "";

    public PageHeader() {
        setStyleName(Styles.PAGE_HEADER);
    }

    public PageHeader(final String text) {
        this();
        setText(text);
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(final String text) {
        this.text = text == null ? "" : text;
        render();
    }

    @Override
    public String getSubText() {
        return subText;
    }

    @Override
    public void setSubText(final String subText) {
        this.subText = subText == null ? "" : subText;
        render();
    }

    private void render() {
        final String suffix = subText.isEmpty() ? "" : " <small>" + escape(subText) + "</small>";
        getElement().setInnerHTML("<h1>" + escape(text) + suffix + "</h1>");
    }

    private static String escape(final String value) {
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
