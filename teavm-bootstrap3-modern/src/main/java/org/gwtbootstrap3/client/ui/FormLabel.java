package org.gwtbootstrap3.client.ui;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;
import org.gwtbootstrap3.client.ui.constants.Styles;

public class FormLabel extends AbstractTextWidget {
    private boolean showRequiredIndicator;

    public FormLabel() {
        super(Document.get().createLabelElement());
        setStyleName(Styles.CONTROL_LABEL);
    }

    public void setFor(final String id) {
        if (id == null) {
            getElement().removeAttribute("for");
        } else {
            getElement().setAttribute("for", id);
        }
    }

    public boolean getShowRequiredIndicator() {
        return showRequiredIndicator;
    }

    public void setShowRequiredIndicator(final boolean showRequiredIndicator) {
        this.showRequiredIndicator = showRequiredIndicator;
    }
}
