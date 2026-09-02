package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.UIObject;

public class Affix {

    public static void affix(Element element) {
        affix(element, 0);
    }

    public static void affix(Element element, int offset) {
        element.addClassName("sticky-top");
        if (offset > 0) {
            element.getStyle().setTop(offset, com.google.gwt.dom.client.Style.Unit.PX);
        }
    }

    public static void affix(UIObject object) {
        affix(object.getElement());
    }

    public static void affix(UIObject object, int offset) {
        affix(object.getElement(), offset);
    }
}
