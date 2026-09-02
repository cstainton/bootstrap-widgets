package org.gwtbootstrap5.client.ui;

public class ThumbnailLink extends ElementPanel {

    public ThumbnailLink() {
        this("#");
    }

    public ThumbnailLink(String href) {
        super("a");
        setStyleName("card text-decoration-none text-reset");
        getElement().setAttribute("href", href == null ? "#" : href);
    }
}
