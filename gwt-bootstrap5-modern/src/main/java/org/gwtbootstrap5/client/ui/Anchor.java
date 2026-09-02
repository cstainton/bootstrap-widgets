package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasEnabled;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.ButtonType;

public class Anchor extends ElementPanel implements HasEnabled {

    private String targetHistoryToken;
    private Variant buttonVariant;
    private boolean outline;

    public Anchor() {
        this("#");
    }

    public Anchor(String href) {
        super("a");
        setHref(href);
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
        setHref("#" + History.encodeHistoryToken(targetHistoryToken));
    }

    public String getTargetHistoryToken() {
        return targetHistoryToken;
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
