package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class DropDown extends ElementPanel {

    private final Button toggle;
    private final DropDownMenu menu = new DropDownMenu();

    public DropDown() {
        this("Dropdown");
    }

    public DropDown(String text) {
        super("div");
        addStyleName("dropdown");
        toggle = new Button(text, Variant.SECONDARY);
        toggle.addStyleName("dropdown-toggle");
        toggle.getElement().setAttribute("data-bs-toggle", "dropdown");
        toggle.getElement().setAttribute("aria-expanded", "false");
        add(toggle);
        add(menu);
    }

    public Button getToggle() {
        return toggle;
    }

    public DropDownMenu getMenu() {
        return menu;
    }

    public void addItem(DropDownItem item) {
        menu.add(item);
    }

    public void addMenuWidget(Widget widget) {
        menu.add(widget);
    }
}
