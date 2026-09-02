package org.gwtbootstrap5.client.ui;

public class Image extends com.google.gwt.user.client.ui.Image {

    public Image() {
        addStyleName("img-fluid");
    }

    public Image(String url) {
        super(url);
        addStyleName("img-fluid");
    }

    public void setRounded(boolean rounded) {
        setStyleName("rounded", rounded);
    }

    public void setCircle(boolean circle) {
        setStyleName("rounded-circle", circle);
    }

    public void setThumbnail(boolean thumbnail) {
        setStyleName("img-thumbnail", thumbnail);
    }
}
