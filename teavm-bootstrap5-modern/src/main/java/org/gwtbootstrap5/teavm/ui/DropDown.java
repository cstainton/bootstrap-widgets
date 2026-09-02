package org.gwtbootstrap5.teavm.ui;

public class DropDown extends Panel {

    private final Button toggle;
    private final DropDownMenu menu = new DropDownMenu();

    public DropDown() {
        this("Dropdown");
    }

    public DropDown(final String text) {
        super("div");
        addStyleName("dropdown");
        toggle = new Button(text);
        toggle.setButtonStyle("btn-secondary dropdown-toggle");
        toggle.setAttribute("data-bs-toggle", "dropdown");
        toggle.setAttribute("aria-expanded", "false");
        add(toggle);
        add(menu);
    }

    public Button getToggle() {
        return toggle;
    }

    public DropDownMenu getMenu() {
        return menu;
    }

    public DropDown addItem(final DropDownItem item) {
        menu.add(item);
        return this;
    }

    public DropDown addMenuWidget(final Widget widget) {
        menu.add(widget);
        return this;
    }
}
