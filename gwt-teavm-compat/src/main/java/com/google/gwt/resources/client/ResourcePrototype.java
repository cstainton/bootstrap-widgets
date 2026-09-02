package com.google.gwt.resources.client;

/** Base type for every resource a {@link ClientBundle} can expose. */
public interface ResourcePrototype {

    /** The name of the bundle method that declared this resource. */
    String getName();
}
