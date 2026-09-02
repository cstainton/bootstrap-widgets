package org.gwtbootstrap5.client.ui;

public class PageLink extends Anchor {

    public PageLink() {
        super();
        addStyleName("page-link");
    }

    public PageLink(String text, String href) {
        this();
        setText(text);
        setHref(href);
    }
}
