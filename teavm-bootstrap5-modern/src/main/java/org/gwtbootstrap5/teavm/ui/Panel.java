package org.gwtbootstrap5.teavm.ui;

import org.gwtbootstrap5.teavm.dom.TeaVmDomElement;

/**
 * Base container widget for TeaVM-rendered child widgets.
 */
public class Panel extends Widget {

    protected Panel(final String tagName) {
        super(tagName);
    }

    protected Panel(final TeaVmDomElement element) {
        super(element);
    }

    public Panel add(final Widget child) {
        if (child == null) {
            throw new IllegalArgumentException("child must not be null");
        }
        getElement().appendChild(child.getElement());
        return this;
    }

    public Panel clear() {
        getElement().setInnerHTML("");
        return this;
    }
}
