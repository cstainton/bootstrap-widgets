package com.google.gwt.event.logical.shared;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ValueChangeEvent<T> extends GwtEvent<ValueChangeHandler<T>> {

    private static final Type<ValueChangeHandler<?>> TYPE = new Type<>();

    private final T value;

    protected ValueChangeEvent(final T value) {
        this.value = value;
    }

    /**
     * Fires a value change event on {@code source} only when the value actually changed.
     */
    public static <T> void fireIfNotEqual(final HasValueChangeHandlers<T> source, final T oldValue, final T newValue) {
        if (oldValue == null ? newValue != null : !oldValue.equals(newValue)) {
            fire(source, newValue);
        }
    }

    public static <T> void fire(final HasValueChangeHandlers<T> source, final T value) {
        source.fireEvent(new ValueChangeEvent<>(value));
    }

    public static <T> void fire(final HasHandlers source, final T value) {
        source.fireEvent(new ValueChangeEvent<>(value));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> Type<ValueChangeHandler<T>> getType() {
        return (Type) TYPE;
    }

    public T getValue() {
        return value;
    }

    @Override
    public Type<ValueChangeHandler<T>> getAssociatedType() {
        return getType();
    }

    @Override
    protected void dispatch(final ValueChangeHandler<T> handler) {
        handler.onValueChange(this);
    }
}
