package org.gwtbootstrap5.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import org.gwtbootstrap5.client.ui.base.HasDataToggle;
import org.gwtbootstrap5.client.ui.base.HasSize;
import org.gwtbootstrap5.client.ui.base.HasType;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.Attributes;
import org.gwtbootstrap5.client.ui.constants.ButtonSize;
import org.gwtbootstrap5.client.ui.constants.ButtonType;
import org.gwtbootstrap5.client.ui.constants.Toggle;

public class Button extends com.google.gwt.user.client.ui.Button implements HasType<ButtonType>, HasSize<ButtonSize>, HasDataToggle {

    public class ButtonStateHandler {
        private ButtonStateHandler() {
        }

        public void loading() {
            setLoading(true);
        }

        public void reset() {
            setLoading(false);
        }

        public void reset(String state) {
            setText(state);
            normalText = state == null ? "" : state;
            setEnabled(true);
            getElement().removeAttribute("aria-busy");
            loading = false;
        }
    }

    private final ButtonStateHandler buttonStateHandler = new ButtonStateHandler();
    private Variant variant;
    private boolean outline;
    private String normalText = "";
    private String loadingText;
    private boolean loading;
    private Toggle dataToggle;

    public Button() {
        this("", Variant.SECONDARY);
    }

    public Button(String text) {
        this(text, Variant.SECONDARY);
    }

    public Button(String text, ClickHandler handler) {
        this(text);
        addClickHandler(handler);
    }

    public Button(String text, Variant variant) {
        super(text);
        normalText = text == null ? "" : text;
        setVariant(variant);
    }

    public Button(String text, ButtonType type) {
        super(text);
        normalText = text == null ? "" : text;
        addStyleName("btn");
        setType(type);
    }

    public void setVariant(Variant variant) {
        if (this.variant != null) {
            removeStyleName(styleName(this.variant, outline));
        }
        this.variant = variant == null ? Variant.PRIMARY : variant;
        addStyleName("btn");
        addStyleName(styleName(this.variant, outline));
    }

    public void setOutline(boolean outline) {
        if (this.outline != outline) {
            removeStyleName(styleName(variant, this.outline));
            this.outline = outline;
            addStyleName(styleName(variant, this.outline));
        }
    }

    public void setLarge(boolean large) {
        setStyleName("btn-lg", large);
    }

    public void setSmall(boolean small) {
        setStyleName("btn-sm", small);
    }

    public void setBlock(boolean block) {
        setStyleName("d-block", block);
        setStyleName("w-100", block);
    }

    public void setActive(boolean active) {
        setStyleName("active", active);
        getElement().setAttribute("aria-pressed", Boolean.toString(active));
    }

    public boolean isActive() {
        return getStyleName().contains("active");
    }

    public void setLoadingText(String loadingText) {
        this.loadingText = loadingText;
        getElement().setAttribute("data-loading-text", loadingText == null ? "" : loadingText);
    }

    public void setDataLoadingText(String loadingText) {
        setLoadingText(loadingText);
    }

    public String getLoadingText() {
        return loadingText;
    }

    public void setLoading(boolean loading) {
        if (loading) {
            if (!this.loading) {
                normalText = getText();
            }
            if (loadingText != null && !loadingText.isEmpty()) {
                setText(loadingText);
            }
            setEnabled(false);
            getElement().setAttribute("aria-busy", "true");
        } else {
            setText(normalText);
            setEnabled(true);
            getElement().removeAttribute("aria-busy");
        }
        this.loading = loading;
    }

    public boolean isLoading() {
        return loading;
    }

    public void toggle() {
        setActive(!isActive());
    }

    public ButtonStateHandler state() {
        return buttonStateHandler;
    }

    public void click() {
        NativeEvent event = Document.get().createClickEvent(0, 0, 0, 0, 0, false, false, false, false);
        DomEvent.fireNativeEvent(event, this);
    }

    public void setDataToggle(String toggle) {
        if (toggle == null || toggle.isEmpty()) {
            getElement().removeAttribute(Attributes.DATA_TOGGLE);
            removeStyleName("dropdown-toggle");
        } else {
            getElement().setAttribute(Attributes.DATA_TOGGLE, toggle);
            if ("dropdown".equals(toggle)) {
                addStyleName("dropdown-toggle");
            }
        }
    }

    @Override
    public void setDataToggle(Toggle toggle) {
        dataToggle = toggle;
        setDataToggle(toggle == null ? null : toggle.getToggle());
    }

    @Override
    public Toggle getDataToggle() {
        return dataToggle;
    }

    @Override
    public void setType(ButtonType type) {
        StyleHelper.addUniqueEnumStyleName(this, ButtonType.class, type == null ? ButtonType.DEFAULT : type);
    }

    @Override
    public ButtonType getType() {
        return ButtonType.fromStyleName(getStyleName());
    }

    @Override
    public void setSize(ButtonSize size) {
        StyleHelper.addUniqueEnumStyleName(this, ButtonSize.class, size == null ? ButtonSize.DEFAULT : size);
    }

    @Override
    public ButtonSize getSize() {
        return ButtonSize.fromStyleName(getStyleName());
    }

    private String styleName(Variant variant, boolean outline) {
        Variant effectiveVariant = variant == null ? Variant.PRIMARY : variant;
        return outline ? "btn-outline-" + effectiveVariant.cssName() : "btn-" + effectiveVariant.cssName();
    }
}
