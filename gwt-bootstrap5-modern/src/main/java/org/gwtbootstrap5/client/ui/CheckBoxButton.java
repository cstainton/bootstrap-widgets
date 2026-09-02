package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import org.gwtbootstrap5.client.ui.base.HasActive;
import org.gwtbootstrap5.client.ui.base.HasIcon;
import org.gwtbootstrap5.client.ui.base.HasIconPosition;
import org.gwtbootstrap5.client.ui.base.HasSize;
import org.gwtbootstrap5.client.ui.base.HasType;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.ButtonSize;
import org.gwtbootstrap5.client.ui.constants.ButtonType;
import org.gwtbootstrap5.client.ui.constants.IconFlip;
import org.gwtbootstrap5.client.ui.constants.IconPosition;
import org.gwtbootstrap5.client.ui.constants.IconRotate;
import org.gwtbootstrap5.client.ui.constants.IconSize;
import org.gwtbootstrap5.client.ui.constants.IconType;

/**
 * A checkbox rendered using Bootstrap 5's native {@code .btn-check + label.btn}
 * pattern. The real checkbox remains the source of truth for touch, keyboard,
 * form and accessibility behaviour.
 */
public class CheckBoxButton extends CheckBox implements HasActive,
        HasType<ButtonType>, HasSize<ButtonSize>, HasIcon, HasIconPosition {

    private Icon icon;
    private IconPosition iconPosition = IconPosition.LEFT;

    public CheckBoxButton() {
        this("");
    }

    public CheckBoxButton(String text) {
        super(text);
        initialiseButtonControl();
    }

    public CheckBoxButton(String text, boolean asHtml) {
        this();
        if (asHtml) {
            setHTML(text);
        } else {
            setText(text);
        }
    }

    public CheckBoxButton(SafeHtml html) {
        this(html == null ? "" : html.asString(), true);
    }

    private void initialiseButtonControl() {
        removeStyleName("form-check");
        addStyleName("d-inline-block");

        getInput().removeStyleName("form-check-input");
        getInput().addStyleName("btn-check");
        getInput().getElement().setAttribute("autocomplete", "off");

        String inputId = Document.get().createUniqueId();
        getInput().getElement().setId(inputId);
        getLabel().setFor(inputId);
        getLabel().removeStyleName("form-check-label");
        getLabel().addStyleName("btn");
        setType(ButtonType.DEFAULT);
    }

    public void setText(String text) {
        getLabel().setText(text == null ? "" : text);
        renderIcon();
    }

    public String getText() {
        return getLabel().getText();
    }

    public void setHTML(String html) {
        getLabel().setHTML(html == null ? "" : html);
        renderIcon();
    }

    public String getHTML() {
        return getLabel().getHTML();
    }

    @Override
    public void setValue(Boolean value, boolean fireEvents) {
        boolean oldValue = Boolean.TRUE.equals(getValue());
        boolean effectiveValue = Boolean.TRUE.equals(value);
        super.setValue(effectiveValue, false);
        getLabel().setStyleName("active", effectiveValue);
        getLabel().getElement().setAttribute("aria-pressed", Boolean.toString(effectiveValue));
        if (fireEvents && oldValue != effectiveValue) {
            ValueChangeEvent.fire(this, effectiveValue);
        }
    }

    @Override
    public void setActive(boolean active) {
        setValue(active);
    }

    @Override
    public boolean isActive() {
        return Boolean.TRUE.equals(getValue());
    }

    @Override
    public void setType(ButtonType type) {
        StyleHelper.addUniqueEnumStyleName(getLabel(), ButtonType.class,
                type == null ? ButtonType.DEFAULT : type);
    }

    @Override
    public ButtonType getType() {
        return ButtonType.fromStyleName(getLabel().getStyleName());
    }

    @Override
    public void setSize(ButtonSize size) {
        StyleHelper.addUniqueEnumStyleName(getLabel(), ButtonSize.class,
                size == null ? ButtonSize.DEFAULT : size);
    }

    @Override
    public ButtonSize getSize() {
        return ButtonSize.fromStyleName(getLabel().getStyleName());
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        getLabel().setStyleName("disabled", !enabled);
        getLabel().getElement().setAttribute("aria-disabled", Boolean.toString(!enabled));
    }

    @Override
    public void setIconPosition(IconPosition iconPosition) {
        this.iconPosition = iconPosition == null ? IconPosition.LEFT : iconPosition;
        renderIcon();
    }

    @Override
    public IconPosition getIconPosition() {
        return iconPosition;
    }

    @Override
    public void setIcon(IconType iconType) {
        getActualIcon().setType(iconType);
        renderIcon();
    }

    @Override
    public IconType getIcon() {
        return icon == null ? null : icon.getType();
    }

    @Override
    public void setIconSize(IconSize iconSize) {
        getActualIcon().setSize(iconSize);
        renderIcon();
    }

    @Override
    public IconSize getIconSize() {
        return icon == null ? IconSize.NONE : icon.getSize();
    }

    @Override
    public void setIconFlip(IconFlip iconFlip) {
        getActualIcon().setFlip(iconFlip);
        renderIcon();
    }

    @Override
    public IconFlip getIconFlip() {
        return icon == null ? IconFlip.NONE : icon.getFlip();
    }

    @Override
    public void setIconRotate(IconRotate iconRotate) {
        getActualIcon().setRotate(iconRotate);
        renderIcon();
    }

    @Override
    public IconRotate getIconRotate() {
        return icon == null ? IconRotate.NONE : icon.getRotate();
    }

    @Override
    public void setIconBordered(boolean iconBordered) {
        getActualIcon().setBorder(iconBordered);
        renderIcon();
    }

    @Override
    public boolean isIconBordered() {
        return icon != null && icon.isBorder();
    }

    @Override
    public void setIconInverse(boolean iconInverse) {
        getActualIcon().setInverse(iconInverse);
        renderIcon();
    }

    @Override
    public boolean isIconInverse() {
        return icon != null && icon.isInverse();
    }

    @Override
    public void setIconSpin(boolean iconSpin) {
        getActualIcon().setSpin(iconSpin);
        renderIcon();
    }

    @Override
    public boolean isIconSpin() {
        return icon != null && icon.isSpin();
    }

    @Override
    public void setIconPulse(boolean iconPulse) {
        getActualIcon().setPulse(iconPulse);
        renderIcon();
    }

    @Override
    public boolean isIconPulse() {
        return icon != null && icon.isPulse();
    }

    @Override
    public void setIconFixedWidth(boolean iconFixedWidth) {
        getActualIcon().setFixedWidth(iconFixedWidth);
        renderIcon();
    }

    @Override
    public boolean isIconFixedWidth() {
        return icon != null && icon.isFixedWidth();
    }

    @Override
    public void setIconColor(String iconColor) {
        getActualIcon().setColor(iconColor);
        renderIcon();
    }

    private Icon getActualIcon() {
        if (icon == null) {
            icon = new Icon();
        }
        return icon;
    }

    private void renderIcon() {
        if (icon == null) {
            return;
        }
        if (icon.getElement().hasParentElement()) {
            icon.getElement().removeFromParent();
        }
        if (iconPosition == IconPosition.LEFT) {
            getLabel().getElement().insertFirst(icon.getElement());
            icon.removeStyleName("ms-1");
            icon.addStyleName("me-1");
        } else {
            getLabel().getElement().appendChild(icon.getElement());
            icon.removeStyleName("me-1");
            icon.addStyleName("ms-1");
        }
    }
}
