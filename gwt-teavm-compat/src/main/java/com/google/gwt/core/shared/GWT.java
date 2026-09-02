package com.google.gwt.core.shared;

public final class GWT {

    private GWT() {
    }

    public static boolean isClient() {
        return true;
    }

    public static boolean isProdMode() {
        return true;
    }

    public static boolean isScript() {
        return true;
    }

    public static void log(final String message) {
        System.out.println(message);
    }

    public static <T> T create(final Class<?> classLiteral) {
        return com.google.gwt.core.client.GWT.create(classLiteral);
    }

    public static <T> void register(final Class<T> type, final T implementation) {
        com.google.gwt.core.client.GWT.register(type, implementation);
    }

    public static String getModuleName() {
        return com.google.gwt.core.client.GWT.getModuleName();
    }
}
