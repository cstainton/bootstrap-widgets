package com.google.gwt.resources.client;

/** A packaged binary file, exposed as a URL (usually a {@code data:} URI). */
public interface DataResource extends ResourcePrototype {
    String getSafeUri();

    String getUrl();
}
