package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.HasType;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.ImageType;

public class Image extends com.google.gwt.user.client.ui.Image implements HasType<ImageType> {

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

    @Override
    public void setType(ImageType type) {
        StyleHelper.addUniqueEnumStyleName(this, ImageType.class, type == null ? ImageType.DEFAULT : type);
    }

    @Override
    public ImageType getType() {
        return ImageType.fromStyleName(getStyleName());
    }
}
