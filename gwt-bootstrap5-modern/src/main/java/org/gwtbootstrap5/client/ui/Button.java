package org.gwtbootstrap5.client.ui;

import com.google.gwt.event.dom.client.ClickHandler;

public class Button extends com.google.gwt.user.client.ui.Button {

    private Variant variant;
    private boolean outline;
    private String normalText = "";
    private String loadingText;

    public Button() {
        this("", Variant.PRIMARY);
    }

    public Button(String text) {
        this(text, Variant.PRIMARY);
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

    public String getLoadingText() {
        return loadingText;
    }

    public void setLoading(boolean loading) {
        if (loading) {
            normalText = getText();
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
    }

    private String styleName(Variant variant, boolean outline) {
        Variant effectiveVariant = variant == null ? Variant.PRIMARY : variant;
        return outline ? "btn-outline-" + effectiveVariant.cssName() : "btn-" + effectiveVariant.cssName();
    }
}
