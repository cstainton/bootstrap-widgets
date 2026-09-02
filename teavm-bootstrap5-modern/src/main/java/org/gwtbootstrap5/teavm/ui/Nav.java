package org.gwtbootstrap5.teavm.ui;

public class Nav extends Panel {

    public Nav() {
        super("ul");
        addStyleName("nav");
    }

    @Override
    public Nav add(final Widget child) {
        super.add(child);
        return this;
    }

    public Nav addItem(final Widget child) {
        final Panel item = new Panel("li");
        item.addStyleName("nav-item");
        item.add(child);
        add(item);
        return this;
    }

    public Anchor addLink(final String text, final String href) {
        final Anchor link = new Anchor(text, href);
        link.addStyleName("nav-link");
        addItem(link);
        return link;
    }
}
