package org.gwtbootstrap5.client.ui;

public class ThumbnailLink extends Anchor {

    public ThumbnailLink() {
        this("#");
    }

    public ThumbnailLink(String href) {
        super(href == null ? "#" : href);
        setStyleName("card text-decoration-none text-reset");
    }
}
