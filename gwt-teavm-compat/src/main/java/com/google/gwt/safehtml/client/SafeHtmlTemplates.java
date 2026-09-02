package com.google.gwt.safehtml.client;

public interface SafeHtmlTemplates {

    /** Marks a method whose return value is built from the given template. */
    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
    @interface Template {
        String value();
    }
}
