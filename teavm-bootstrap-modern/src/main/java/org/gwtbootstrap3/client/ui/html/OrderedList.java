package org.gwtbootstrap3.client.ui.html;

import com.google.gwt.dom.client.Document;
import org.gwtbootstrap3.client.ui.ListItem;
import org.gwtbootstrap3.client.ui.constants.Styles;

public class OrderedList extends Div {
    public OrderedList() {
        setElement(Document.get().createOLElement());
    }

    public OrderedList(final ListItem... widgets) {
        this();
        for (final ListItem item : widgets) {
            add(item);
        }
    }

    public void setUnstyled(final boolean unstyled) {
        setStyleName(Styles.UNSTYLED, unstyled);
    }

    public void setInline(final boolean inline) {
        setStyleName(Styles.LIST_INLINE, inline);
    }
}
