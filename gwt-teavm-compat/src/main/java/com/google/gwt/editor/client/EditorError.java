package com.google.gwt.editor.client;

/** A validation failure attached to an editor. */
public interface EditorError {

    String getAbsolutePath();

    Editor<?> getEditor();

    String getMessage();

    String getPath();

    Object getUserData();

    Object getValue();

    boolean isConsumed();

    void setConsumed(boolean consumed);
}
