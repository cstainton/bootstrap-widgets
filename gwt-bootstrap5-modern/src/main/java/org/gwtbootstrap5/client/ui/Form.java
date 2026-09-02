package org.gwtbootstrap5.client.ui;

public class Form extends ElementPanel {

    public Form() {
        super("form");
    }

    public void setInline(boolean inline) {
        setStyleName("row row-cols-lg-auto g-3 align-items-center", inline);
    }
}
