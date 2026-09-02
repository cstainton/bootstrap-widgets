package com.google.gwt.resources.client;

/** A packaged text file fetched on demand rather than inlined. */
public interface ExternalTextResource extends ResourcePrototype {
    String getUrl();
}
