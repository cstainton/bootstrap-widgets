package org.gwtbootstrap3.client.ui.html;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

public class Br extends Widget {
    public Br() {
        setElement(Document.get().createBRElement());
    }
}
