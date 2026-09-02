package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap5.client.ui.constants.ButtonType;

public class DropDown extends ElementPanel {

    private final Button toggle;
    private final DropDownMenu menu = new DropDownMenu();

    public DropDown() {
        this("Dropdown");
    }

    public DropDown(String text) {
        this("div", text);
    }

    protected DropDown(String tagName, String text) {
        super(tagName);
        addStyleName("dropdown");
        toggle = new Button(text, ButtonType.DEFAULT);
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

    @Override
    public String getText() {
        return toggle.getText();
    }

    @Override
    public void setText(String text) {
        toggle.setText(text == null ? "" : text);
    }

    public void addItem(DropDownItem item) {
        menu.add(item);
    }

    public void addMenuWidget(Widget widget) {
        menu.add(widget);
    }

    public void setDropUp(boolean dropUp) {
        setStyleName("dropup", dropUp);
        setStyleName("dropdown", !dropUp);
    }

    public void setDropStart(boolean dropStart) {
        setStyleName("dropstart", dropStart);
        if (dropStart) {
            removeStyleName("dropdown");
            removeStyleName("dropup");
            removeStyleName("dropend");
        } else if (!getStyleName().contains("dropend") && !getStyleName().contains("dropup")) {
            addStyleName("dropdown");
        }
    }

    public void setDropEnd(boolean dropEnd) {
        setStyleName("dropend", dropEnd);
        if (dropEnd) {
            removeStyleName("dropdown");
            removeStyleName("dropup");
            removeStyleName("dropstart");
        } else if (!getStyleName().contains("dropstart") && !getStyleName().contains("dropup")) {
            addStyleName("dropdown");
        }
    }

    public void setMenuEndAligned(boolean endAligned) {
        menu.setEndAligned(endAligned);
    }
}
