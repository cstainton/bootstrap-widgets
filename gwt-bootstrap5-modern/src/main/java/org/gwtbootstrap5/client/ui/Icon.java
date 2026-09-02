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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import org.gwtbootstrap5.client.ui.base.HasEmphasis;
import org.gwtbootstrap5.client.ui.base.HasSize;
import org.gwtbootstrap5.client.ui.base.HasType;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.Emphasis;
import org.gwtbootstrap5.client.ui.constants.IconFlip;
import org.gwtbootstrap5.client.ui.constants.IconRotate;
import org.gwtbootstrap5.client.ui.constants.IconSize;
import org.gwtbootstrap5.client.ui.constants.IconType;
import org.gwtbootstrap5.client.ui.constants.Styles;

public class Icon extends ElementPanel implements HasType<IconType>, HasSize<IconSize>, HasEmphasis, HasClickHandlers {

    private String iconName;

    public Icon() {
        this("");
    }

    public Icon(String iconName) {
        super("i");
        addStyleName("bi");
        setIconName(iconName);
    }

    public Icon(IconType type) {
        this();
        setType(type);
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

    @Override
    public void setType(IconType type) {
        if (type == null) {
            StyleHelper.removeEnumStyleNames(this, IconType.class);
            return;
        }
        StyleHelper.addUniqueEnumStyleName(this, IconType.class, type);
    }

    @Override
    public IconType getType() {
        return IconType.fromStyleName(getStyleName());
    }

    public void setBorder(boolean border) {
        StyleHelper.toggleStyleName(this, border, Styles.ICON_BORDER);
    }

    public boolean isBorder() {
        return StyleHelper.containsStyle(getStyleName(), Styles.ICON_BORDER);
    }

    public void setFixedWidth(boolean fixedWidth) {
        StyleHelper.toggleStyleName(this, fixedWidth, Styles.ICON_FIXED_WIDTH);
    }

    public boolean isFixedWidth() {
        return StyleHelper.containsStyle(getStyleName(), Styles.ICON_FIXED_WIDTH);
    }

    public void setInverse(boolean inverse) {
        StyleHelper.toggleStyleName(this, inverse, Styles.ICON_INVERSE);
    }

    public boolean isInverse() {
        return StyleHelper.containsStyle(getStyleName(), Styles.ICON_INVERSE);
    }

    public void setSpin(boolean spin) {
        StyleHelper.toggleStyleName(this, spin, Styles.ICON_SPIN);
    }

    public boolean isSpin() {
        return StyleHelper.containsStyle(getStyleName(), Styles.ICON_SPIN);
    }

    public void setPulse(boolean pulse) {
        StyleHelper.toggleStyleName(this, pulse, Styles.ICON_PULSE);
    }

    public boolean isPulse() {
        return StyleHelper.containsStyle(getStyleName(), Styles.ICON_PULSE);
    }

    public void setRotate(IconRotate iconRotate) {
        StyleHelper.addUniqueEnumStyleName(this, IconRotate.class, iconRotate == null ? IconRotate.NONE : iconRotate);
    }

    public IconRotate getRotate() {
        return IconRotate.fromStyleName(getStyleName());
    }

    public void setFlip(IconFlip iconFlip) {
        StyleHelper.addUniqueEnumStyleName(this, IconFlip.class, iconFlip == null ? IconFlip.NONE : iconFlip);
    }

    public IconFlip getFlip() {
        return IconFlip.fromStyleName(getStyleName());
    }

    @Override
    public void setSize(IconSize iconSize) {
        StyleHelper.addUniqueEnumStyleName(this, IconSize.class, iconSize == null ? IconSize.NONE : iconSize);
    }

    @Override
    public IconSize getSize() {
        return IconSize.fromStyleName(getStyleName());
    }

    @Override
    public void setEmphasis(Emphasis emphasis) {
        StyleHelper.addUniqueEnumStyleName(this, Emphasis.class, emphasis);
    }

    @Override
    public Emphasis getEmphasis() {
        return Emphasis.fromStyleName(getStyleName());
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }
}
