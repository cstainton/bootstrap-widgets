package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.HasType;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.ImageType;
import org.gwtbootstrap5.client.ui.constants.Styles;

public class ImageAnchor extends Anchor implements HasType<ImageType> {

    private final Image image = new Image();

    public ImageAnchor() {
        add(image);
    }

    public ImageAnchor(String href, String imageUrl) {
        this();
        setHref(href == null ? "#" : href);
        setUrl(imageUrl);
    }

    @Override
    public void setType(final ImageType type) {
        StyleHelper.addUniqueEnumStyleName(this, ImageType.class, type == null ? ImageType.DEFAULT : type);
    }

    @Override
    public ImageType getType() {
        return ImageType.fromStyleName(getStyleName());
    }

    public void setResponsive(final boolean responsive) {
        StyleHelper.toggleStyleName(image, responsive, Styles.IMG_RESPONSIVE);
    }

    public void setAsMediaObject(final boolean asMediaObject) {
        StyleHelper.toggleStyleName(image, asMediaObject, Styles.MEDIA_OBJECT);
    }

    public void setUrl(final String url) {
        image.setUrl(url);
    }

    public String getUrl() {
        return image.getUrl();
    }

    public void setAlt(final String alt) {
        image.setAltText(alt);
    }

    public String getAlt() {
        return image.getAltText();
    }

    public Image getImage() {
        return image;
    }
}
