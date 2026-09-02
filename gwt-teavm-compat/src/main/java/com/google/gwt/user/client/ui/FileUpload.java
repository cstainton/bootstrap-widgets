package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;

/** A file input. */
public class FileUpload extends FocusWidget implements HasName {

    public FileUpload() {
        super(Document.get().createInputElement("file"));
        setStyleName("gwt-FileUpload");
    }

    @Override
    public String getName() {
        return getElement().getPropertyString("name");
    }

    @Override
    public void setName(final String name) {
        getElement().setPropertyString("name", name);
    }

    public String getFilename() {
        return getElement().getPropertyString("value");
    }
}
