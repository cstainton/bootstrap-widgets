package com.google.gwt.resources.client;

/** A packaged image, exposed as a URL plus its intrinsic geometry. */
public interface ImageResource extends ResourcePrototype {

    String getSafeUri();

    String getURL();

    int getWidth();

    int getHeight();

    int getLeft();

    int getTop();

    boolean isAnimated();
}
