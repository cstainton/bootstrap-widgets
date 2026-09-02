package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class Card extends FlowPanel {

    private final FlowPanel body = new FlowPanel();

    public Card() {
        setStyleName("card shadow-sm");
        body.addStyleName("card-body");
        add(body);
    }

    public void setTitle(String title) {
        body.insert(new HTML("<h2 class=\"h5 card-title\">" + escape(title) + "</h2>"), 0);
    }

    public void addBody(HTML html) {
        body.add(html);
    }

    public void addBody(Widget widget) {
        body.add(widget);
    }

    private String escape(String value) {
        return value == null ? "" : value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}
