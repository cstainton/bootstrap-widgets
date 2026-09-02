package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class Nav extends ElementPanel {

    public Nav() {
        super("ul");
        addStyleName("nav");
    }

    public void addItem(Widget child) {
        ElementPanel item = new ElementPanel("li");
        item.addStyleName("nav-item");
        item.add(child);
        add(item);
    }

    public Anchor addLink(String text, String href) {
        Anchor link = new Anchor(text, href);
        link.addStyleName("nav-link");
        addItem(link);
        return link;
    }
}
