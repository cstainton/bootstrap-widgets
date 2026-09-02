package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasEnabled;
import org.gwtbootstrap5.client.ui.base.HasBadge;
import org.gwtbootstrap5.client.ui.base.HasDataToggle;
import org.gwtbootstrap5.client.ui.base.HasIcon;
import org.gwtbootstrap5.client.ui.base.HasIconPosition;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.base.mixin.DataToggleMixin;
import org.gwtbootstrap5.client.ui.base.mixin.FocusableMixin;
import org.gwtbootstrap5.client.ui.base.mixin.IconTextMixin;
import org.gwtbootstrap5.client.ui.constants.BadgePosition;
import org.gwtbootstrap5.client.ui.constants.ButtonType;
import org.gwtbootstrap5.client.ui.constants.IconFlip;
import org.gwtbootstrap5.client.ui.constants.IconPosition;
import org.gwtbootstrap5.client.ui.constants.IconRotate;
import org.gwtbootstrap5.client.ui.constants.IconSize;
import org.gwtbootstrap5.client.ui.constants.IconType;
import org.gwtbootstrap5.client.ui.constants.Toggle;

public class Anchor extends ElementPanel implements HasEnabled, HasDataToggle, HasIcon, HasIconPosition, HasBadge, Focusable {

    private String targetHistoryToken;
    private Variant buttonVariant;
    private boolean outline;
    private final DataToggleMixin<Anchor> toggleMixin = new DataToggleMixin<Anchor>(this);
    private final FocusableMixin<Anchor> focusableMixin = new FocusableMixin<Anchor>(this);
    private final IconTextMixin<Anchor> iconTextMixin = new IconTextMixin<Anchor>(this);

    public Anchor() {
        this("#");
    }

    public Anchor(String href) {
        super("a");
        setHref(href);
        iconTextMixin.addTextWidgetToParent();
    }

    public Anchor(String text, String href) {
        this(href);
        setText(text);
    }

    public void setHref(String href) {
        AnchorElement.as(getElement()).setHref(href == null ? "#" : href);
    }

    public String getHref() {
        return AnchorElement.as(getElement()).getHref();
    }

    public void setTarget(String target) {
        getElement().setAttribute("target", target == null ? "" : target);
    }

    public String getTarget() {
        return getElement().getAttribute("target");
    }

    public void setTargetHistoryToken(String targetHistoryToken) {
        this.targetHistoryToken = targetHistoryToken;
        setHref(targetHistoryToken == null ? "#" : "#" + History.encodeHistoryToken(targetHistoryToken));
    }

    public String getTargetHistoryToken() {
        return targetHistoryToken;
    }

    @Override
    public void setText(String text) {
        iconTextMixin.setText(text);
    }

    @Override
    public String getText() {
        return iconTextMixin.getText();
    }

    @Override
    public void setIcon(IconType iconType) {
        iconTextMixin.setIcon(iconType);
    }

    @Override
    public IconType getIcon() {
        return iconTextMixin.getIcon();
    }

    @Override
    public void setIconPosition(IconPosition iconPosition) {
        iconTextMixin.setIconPosition(iconPosition);
    }

    @Override
    public IconPosition getIconPosition() {
        return iconTextMixin.getIconPosition();
    }

    @Override
    public void setIconSize(IconSize iconSize) {
        iconTextMixin.setIconSize(iconSize);
    }

    @Override
    public IconSize getIconSize() {
        return iconTextMixin.getIconSize();
    }

    @Override
    public void setIconFlip(IconFlip iconFlip) {
        iconTextMixin.setIconFlip(iconFlip);
    }

    @Override
    public IconFlip getIconFlip() {
        return iconTextMixin.getIconFlip();
    }

    @Override
    public void setIconRotate(IconRotate iconRotate) {
        iconTextMixin.setIconRotate(iconRotate);
    }

    @Override
    public IconRotate getIconRotate() {
        return iconTextMixin.getIconRotate();
    }

    @Override
    public void setIconBordered(boolean iconBordered) {
        iconTextMixin.setIconBordered(iconBordered);
    }

    @Override
    public boolean isIconBordered() {
        return iconTextMixin.isIconBordered();
    }

    @Override
    public void setIconInverse(boolean iconInverse) {
        iconTextMixin.setIconInverse(iconInverse);
    }

    @Override
    public boolean isIconInverse() {
        return iconTextMixin.isIconInverse();
    }

    @Override
    public void setIconSpin(boolean iconSpin) {
        iconTextMixin.setIconSpin(iconSpin);
    }

    @Override
    public boolean isIconSpin() {
        return iconTextMixin.isIconSpin();
    }

    @Override
    public void setIconPulse(boolean iconPulse) {
        iconTextMixin.setIconPulse(iconPulse);
    }

    @Override
    public boolean isIconPulse() {
        return iconTextMixin.isIconPulse();
    }

    @Override
    public void setIconFixedWidth(boolean iconFixedWidth) {
        iconTextMixin.setIconFixedWidth(iconFixedWidth);
    }

    @Override
    public boolean isIconFixedWidth() {
        return iconTextMixin.isIconFixedWidth();
    }

    @Override
    public void setIconColor(String iconColor) {
        iconTextMixin.setIconColor(iconColor);
    }

    @Override
    public void setBadgeText(String badgeText) {
        iconTextMixin.setBadgeText(badgeText);
    }

    @Override
    public String getBadgeText() {
        return iconTextMixin.getBadgeText();
    }

    @Override
    public void setBadgePosition(BadgePosition badgePosition) {
        iconTextMixin.setBadgePosition(badgePosition);
    }

    @Override
    public BadgePosition getBadgePosition() {
        return iconTextMixin.getBadgePosition();
    }

    @Override
    public void setDataToggle(Toggle toggle) {
        toggleMixin.setDataToggle(toggle);
        setStyleName("dropdown-toggle", toggle == Toggle.DROPDOWN);
    }

    public void setDataToggle(String toggle) {
        if (toggle == null || toggle.isEmpty()) {
            setDataToggle((Toggle) null);
            return;
        }
        getElement().setAttribute("data-bs-toggle", toggle);
        setStyleName("dropdown-toggle", "dropdown".equals(toggle));
    }

    @Override
    public Toggle getDataToggle() {
        return toggleMixin.getDataToggle();
    }

    @Override
    public int getTabIndex() {
        return focusableMixin.getTabIndex();
    }

    @Override
    public void setTabIndex(int index) {
        focusableMixin.setTabIndex(index);
    }

    @Override
    public void setAccessKey(char key) {
        focusableMixin.setAccessKey(key);
    }

    @Override
    public void setFocus(boolean focused) {
        focusableMixin.setFocus(focused);
    }

    @Override
    public void setEnabled(boolean enabled) {
        setStyleName("disabled", !enabled);
        getElement().setAttribute("aria-disabled", enabled ? "false" : "true");
        if (!enabled) {
            getElement().setAttribute("tabindex", "-1");
        } else {
            getElement().removeAttribute("tabindex");
        }
    }

    @Override
    public boolean isEnabled() {
        return !"true".equals(getElement().getAttribute("aria-disabled"));
    }

    public void setButtonVariant(Variant variant) {
        if (buttonVariant != null) {
            removeStyleName(buttonStyle(buttonVariant, outline));
        }
        buttonVariant = variant == null ? Variant.PRIMARY : variant;
        addStyleName("btn");
        addStyleName(buttonStyle(buttonVariant, outline));
    }

    public void setButtonType(ButtonType type) {
        addStyleName("btn");
        StyleHelper.addUniqueEnumStyleName(this, ButtonType.class, type == null ? ButtonType.DEFAULT : type);
    }

    public void setOutline(boolean outline) {
        if (this.outline != outline) {
            if (buttonVariant != null) {
                removeStyleName(buttonStyle(buttonVariant, this.outline));
            }
            this.outline = outline;
            if (buttonVariant != null) {
                addStyleName(buttonStyle(buttonVariant, this.outline));
            }
        }
    }

    private String buttonStyle(Variant variant, boolean outline) {
        return outline ? "btn-outline-" + variant.cssName() : "btn-" + variant.cssName();
    }
}
