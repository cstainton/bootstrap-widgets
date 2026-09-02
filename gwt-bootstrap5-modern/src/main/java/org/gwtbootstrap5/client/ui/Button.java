/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2018 GwtBootstrap3
 * %%
 * Modified from the GwtBootstrap3 original for the Bootstrap 5 track of
 * GWT Bootstrap Modern: moved to the org.gwtbootstrap5 namespace and re-targeted
 * at Bootstrap 5 markup, class names and JavaScript APIs.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.button.AbstractToggleButton;
import org.gwtbootstrap5.client.ui.constants.Attributes;
import org.gwtbootstrap5.client.ui.constants.ButtonType;
import org.gwtbootstrap5.client.ui.constants.IconType;
import org.gwtbootstrap5.client.ui.constants.Toggle;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Button based on a {@code <button>} element. It preserves the GwtBootstrap3
 * widget contract while rendering Bootstrap 5 classes and behaviour.
 */
public class Button extends AbstractToggleButton {

    private Variant variant;
    private boolean outline;

    public Button() {
    }

    public Button(final String text) {
        setText(text);
    }

    public Button(final String text, final ClickHandler handler) {
        this(text);
        addClickHandler(handler);
    }

    public Button(final String text, final IconType iconType, final ClickHandler handler) {
        this(text, handler);
        setIcon(iconType);
    }

    public Button(final String text, final Variant variant) {
        this(text);
        setVariant(variant);
    }

    public Button(final String text, final ButtonType type) {
        super(type);
        setText(text);
    }

    public void setVariant(final Variant variant) {
        removeVariantStyles();
        this.variant = variant == null ? Variant.SECONDARY : variant;
        addStyleName(styleName(this.variant, outline));
    }

    public Variant getVariant() {
        return variant == null ? Variant.SECONDARY : variant;
    }

    public void setOutline(final boolean outline) {
        if (this.outline == outline) {
            return;
        }
        removeVariantStyles();
        this.outline = outline;
        addStyleName(styleName(getVariant(), outline));
    }

    public boolean isOutline() {
        return outline;
    }

    public void setLarge(final boolean large) {
        setStyleName("btn-lg", large);
    }

    public void setSmall(final boolean small) {
        setStyleName("btn-sm", small);
    }

    /** Retained for source compatibility with the initial Bootstrap 5 port. */
    public void setDataToggle(final String toggle) {
        if (toggle == null || toggle.isEmpty()) {
            setDataToggle((Toggle) null);
            return;
        }
        for (Toggle candidate : Toggle.values()) {
            if (candidate.getToggle().equals(toggle)) {
                setDataToggle(candidate);
                return;
            }
        }
        getElement().setAttribute(Attributes.DATA_TOGGLE, toggle);
    }

    @Override
    public void setType(final ButtonType type) {
        removeVariantStyles();
        ButtonType effectiveType = type == null ? ButtonType.DEFAULT : type;
        super.setType(effectiveType);
        variant = variantFor(effectiveType);
    }

    @Override
    protected Element createElement() {
        return Document.get().createPushButtonElement().cast();
    }

    private void removeVariantStyles() {
        for (Variant candidate : Variant.values()) {
            removeStyleName(styleName(candidate, false));
            removeStyleName(styleName(candidate, true));
        }
    }

    private static Variant variantFor(ButtonType type) {
        switch (type) {
            case PRIMARY:
                return Variant.PRIMARY;
            case SUCCESS:
                return Variant.SUCCESS;
            case INFO:
                return Variant.INFO;
            case WARNING:
                return Variant.WARNING;
            case DANGER:
                return Variant.DANGER;
            case LINK:
                return Variant.LINK;
            case DEFAULT:
            default:
                return Variant.SECONDARY;
        }
    }

    private static String styleName(Variant variant, boolean outline) {
        Variant effectiveVariant = variant == null ? Variant.SECONDARY : variant;
        return outline ? "btn-outline-" + effectiveVariant.cssName() : "btn-" + effectiveVariant.cssName();
    }
}
