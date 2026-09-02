package org.gwtbootstrap5.client.ui;

public class NavbarCollapseButton extends Button {

    public NavbarCollapseButton() {
        this("");
    }

    public NavbarCollapseButton(String targetId) {
        super("");
        setStyleName("navbar-toggler");
        getElement().setAttribute("type", "button");
        getElement().setAttribute("data-bs-toggle", "collapse");
        setTarget(targetId);
        setHTML("<span class=\"navbar-toggler-icon\"></span>");
    }

    public void setTarget(String targetId) {
        if (targetId != null && !targetId.isEmpty()) {
            getElement().setAttribute("data-bs-target", "#" + targetId);
            getElement().setAttribute("aria-controls", targetId);
        }
        getElement().setAttribute("aria-expanded", "false");
        getElement().setAttribute("aria-label", "Toggle navigation");
    }
}
