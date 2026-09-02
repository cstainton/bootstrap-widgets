package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.AbstractAnchorListItem;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class AnchorListItem extends AbstractAnchorListItem implements HasText {

    public AnchorListItem() {
    }

    public AnchorListItem(final String text) {
        setText(text);
    }

    public AnchorListItem(final String text, final String href) {
        this(text);
        setHref(href);
    }

    @Override
    public void setText(final String text) {
        anchor.setText(text == null ? "" : text);
    }

    @Override
    public String getText() {
        return anchor.getText();
    }

    @Override
    public void add(Widget child) {
        anchor.add(child);
    }

    public Anchor getAnchor() {
        return anchor;
    }
}
