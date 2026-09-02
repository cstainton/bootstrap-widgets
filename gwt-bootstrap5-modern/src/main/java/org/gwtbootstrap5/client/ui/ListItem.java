package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.AbstractListItem;
import org.gwtbootstrap5.client.ui.constants.Styles;
import org.gwtbootstrap5.client.ui.html.Text;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;

public class ListItem extends AbstractListItem implements HasWidgets, HasText, HasClickHandlers {

    private Text text = null;

    public ListItem() {
    }

    public ListItem(final String text) {
        this();
        setText(text);
    }

    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }

    @Override
    public String getText() {
        return text == null ? "" : text.getText();
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        if (getParent() instanceof MediaList) {
            addStyleName(Styles.MEDIA);
        }
    }

    @Override
    public void setText(final String text) {
        if (this.text == null) {
            this.text = new Text(text == null ? "" : text);
            add(this.text);
        } else {
            this.text.setText(text == null ? "" : text);
        }
    }
}
