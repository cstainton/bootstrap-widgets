package org.gwtbootstrap5.client.ui;

public class ListItem extends ElementPanel {

    public ListItem() {
        this("");
    }

    public ListItem(String text) {
        super("li");
        setText(text);
    }
}
