package com.google.gwt.editor.client;

import java.util.List;

public interface HasEditorErrors<T> extends Editor<T> {
    void showErrors(List<EditorError> errors);
}
