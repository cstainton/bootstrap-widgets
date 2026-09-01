package org.gwtbootstrap3.client.ui;

import org.gwtbootstrap3.client.ui.base.HasType;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.ListGroupItemType;
import org.gwtbootstrap3.client.ui.constants.Styles;

public class ListGroupItem extends ListItem implements HasType<ListGroupItemType> {
    public ListGroupItem() {
        setStyleName(Styles.LIST_GROUP_ITEM);
    }

    public ListGroupItem(final String text) {
        this();
        setText(text);
    }

    @Override
    public ListGroupItemType getType() {
        return ListGroupItemType.fromStyleName(getStyleName());
    }

    @Override
    public void setType(final ListGroupItemType type) {
        StyleHelper.addUniqueEnumStyleName(this, ListGroupItemType.class, type);
    }
}
