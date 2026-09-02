package org.gwtbootstrap5.client.ui;

public class Abbreviation extends ElementPanel {

    public Abbreviation() {
        this("");
    }

    public Abbreviation(String text) {
        super("abbr");
        setText(text);
    }

    public void setTitle(String title) {
        getElement().setAttribute("title", title == null ? "" : title);
    }
}
