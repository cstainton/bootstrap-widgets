package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.constants.IconPosition;
import org.gwtbootstrap5.client.ui.constants.IconSize;
import org.gwtbootstrap5.client.ui.constants.IconType;
import org.gwtbootstrap5.client.ui.constants.Styles;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class Pager extends Pagination {

    private final AnchorListItem previous;
    private final AnchorListItem next;

    public Pager() {
        previous = new AnchorListItem("Previous", "#");
        next = new AnchorListItem("Next", "#");
        add(previous);
        add(next);
    }

    public void setAlignToSides(boolean alignToSides) {
        setStyleName("justify-content-between", alignToSides);
        previous.setStyleName(Styles.PREVIOUS, alignToSides);
        next.setStyleName(Styles.NEXT, alignToSides);
    }

    public HandlerRegistration addPreviousClickHandler(final ClickHandler clickHandler) {
        return previous.addClickHandler(clickHandler);
    }

    public HandlerRegistration addNextClickHandler(final ClickHandler clickHandler) {
        return next.addClickHandler(clickHandler);
    }

    public void setPreviousText(final String text) {
        previous.setText(text);
    }

    public void setPreviousIcon(final IconType icon) {
        previous.setIcon(icon);
    }

    public void setPreviousIconSize(final IconSize iconSize) {
        previous.setIconSize(iconSize);
    }

    public void setPreviousEnabled(final boolean enabled) {
        previous.setEnabled(enabled);
    }

    public void setPreviousVisible(final boolean visible) {
        previous.setVisible(visible);
    }

    public void setNextText(final String text) {
        next.setText(text);
    }

    public void setNextIcon(final IconType icon) {
        next.setIcon(icon);
        next.setIconPosition(IconPosition.RIGHT);
    }

    public void setNextIconSize(final IconSize iconSize) {
        next.setIconSize(iconSize);
    }

    public void setNextEnabled(final boolean enabled) {
        next.setEnabled(enabled);
    }

    public void setNextVisible(final boolean visible) {
        next.setVisible(visible);
    }
}
