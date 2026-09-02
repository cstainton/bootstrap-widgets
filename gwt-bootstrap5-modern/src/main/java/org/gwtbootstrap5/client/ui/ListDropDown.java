package org.gwtbootstrap5.client.ui;

public class ListDropDown extends DropDown {

    public ListDropDown() {
        this("List dropdown");
    }

    public ListDropDown(String text) {
        super("li", text);
        addStyleName("nav-item");
        getToggle().removeStyleName("btn");
        getToggle().removeStyleName("btn-secondary");
        getToggle().addStyleName("nav-link");
        getToggle().addStyleName("border-0");
        getToggle().addStyleName("bg-transparent");
    }
}
