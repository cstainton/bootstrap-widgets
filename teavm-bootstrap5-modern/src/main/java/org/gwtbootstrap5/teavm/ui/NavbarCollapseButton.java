package org.gwtbootstrap5.teavm.ui;

public class NavbarCollapseButton extends Button {

    public NavbarCollapseButton() {
        this("");
    }

    public NavbarCollapseButton(final String targetId) {
        super();
        setStyleName("navbar-toggler");
        setAttribute("type", "button");
        setAttribute("data-bs-toggle", "collapse");
        setTarget(targetId);
        setHtml("<span class=\"navbar-toggler-icon\"></span>");
    }

    public NavbarCollapseButton setTarget(final String targetId) {
        if (targetId != null && !targetId.isEmpty()) {
            setAttribute("data-bs-target", "#" + targetId);
            setAttribute("aria-controls", targetId);
        }
        setAttribute("aria-expanded", "false");
        setAttribute("aria-label", "Toggle navigation");
        return this;
    }
}
