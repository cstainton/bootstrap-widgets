package org.gwtbootstrap3.client.ui.html;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.ListItem;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.Styles;

public class UnorderedList extends Div {
    public UnorderedList() {
        setElement(Document.get().createULElement());
    }

    public UnorderedList(final ListItem... widgets) {
        this();
        for (final ListItem item : widgets) {
            add(item);
        }
    }

    public void setUnstyled(final boolean unstyled) {
        setStyleName(Styles.UNSTYLED, unstyled);
    }

    public boolean isUnstyled() {
        return StyleHelper.containsStyle(Styles.UNSTYLED, getStyleName());
    }

    public void setInline(final boolean inline) {
        setStyleName(Styles.LIST_INLINE, inline);
    }

    public boolean isInline() {
        return StyleHelper.containsStyle(Styles.LIST_INLINE, getStyleName());
    }
}
