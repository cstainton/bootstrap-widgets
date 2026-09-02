package org.gwtbootstrap5.client.ui;

public class ImageAnchor extends ElementPanel {

    private final Image image;

    public ImageAnchor(String href, String imageUrl) {
        super("a");
        getElement().setAttribute("href", href == null ? "#" : href);
        image = new Image(imageUrl);
        add(image);
    }

    public Image getImage() {
        return image;
    }
}
