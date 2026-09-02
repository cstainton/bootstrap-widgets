package org.gwtbootstrap5.client.ui;

public class Icon extends ElementPanel {

    private String iconName;

    public Icon() {
        this("");
    }

    public Icon(String iconName) {
        super("i");
        addStyleName("bi");
        setIconName(iconName);
    }

    public void setIconName(String iconName) {
        if (this.iconName != null && !this.iconName.isEmpty()) {
            removeStyleName("bi-" + this.iconName);
        }
        this.iconName = normalize(iconName);
        if (!this.iconName.isEmpty()) {
            addStyleName("bi-" + this.iconName);
        }
    }

    private String normalize(String iconName) {
        if (iconName == null) {
            return "";
        }
        return iconName.startsWith("bi-") ? iconName.substring(3) : iconName;
    }
}
