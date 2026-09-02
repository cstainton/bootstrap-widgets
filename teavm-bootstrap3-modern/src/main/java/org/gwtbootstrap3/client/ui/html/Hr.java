package org.gwtbootstrap3.client.ui.html;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

public class Hr extends Widget {
    public Hr() {
        setElement(Document.get().createElement("hr"));
    }
}
