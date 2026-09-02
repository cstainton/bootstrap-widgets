package com.google.gwt.resources.client;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker for an interface whose methods name packaged resources.
 *
 * <p>In GWT a generator reads each {@link Source} at compile time and inlines the
 * file's contents into the compiled output. The TeaVM build does the same job with an
 * annotation processor, so bundle interfaces and their call sites are unchanged.</p>
 */
public interface ClientBundle {

    /** The classpath location of a resource, relative to the declaring package. */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Source {
        String[] value();
    }
}
