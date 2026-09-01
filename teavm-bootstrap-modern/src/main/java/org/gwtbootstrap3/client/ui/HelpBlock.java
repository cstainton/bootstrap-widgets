package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;
import org.gwtbootstrap3.client.ui.constants.Styles;

public class HelpBlock extends AbstractTextWidget {
    private boolean error;

    public HelpBlock() {
        super(Document.get().createSpanElement());
        setStyleName(Styles.HELP_BLOCK);
    }

    public boolean isError() {
        return error;
    }

    public void clearError() {
        error = false;
        setText("");
    }

    public void setError(final String message) {
        error = true;
        setText(message);
    }
}
