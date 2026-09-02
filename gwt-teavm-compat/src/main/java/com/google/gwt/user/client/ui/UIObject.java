package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;

/**
 * Element-backed base class, mirroring the GWT {@code UIObject} surface used by the
 * Bootstrap widget libraries.
 */
public abstract class UIObject {

    /** Style name applied to the primary element, kept first in the class list like GWT. */
    private String primaryStyleName = "";
    private Element element;

    public Element getElement() {
        return element;
    }

    protected void setElement(final Element element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
        this.element = element;
    }

    public void setStyleName(final String styleName) {
        getElement().setClassName(styleName);
    }

    public void setStyleName(final String styleName, final boolean add) {
        if (add) {
            addStyleName(styleName);
        } else {
            removeStyleName(styleName);
        }
    }

    public String getStyleName() {
        return getElement().getClassName();
    }

    /**
     * Adds a style name. Space-separated names are added individually, because
     * {@code classList.add} rejects values containing whitespace.
     */
    public void addStyleName(final String styleName) {
        if (styleName == null) {
            return;
        }
        for (final String part : styleName.trim().split("\\s+")) {
            if (!part.isEmpty()) {
                getElement().addClassName(part);
            }
        }
    }

    public void removeStyleName(final String styleName) {
        if (styleName == null) {
            return;
        }
        for (final String part : styleName.trim().split("\\s+")) {
            if (!part.isEmpty()) {
                getElement().removeClassName(part);
            }
        }
    }

    public void setStylePrimaryName(final String styleName) {
        if (primaryStyleName != null && !primaryStyleName.isEmpty()) {
            removeStyleName(primaryStyleName);
        }
        primaryStyleName = styleName == null ? "" : styleName;
        addStyleName(primaryStyleName);
    }

    public String getStylePrimaryName() {
        return primaryStyleName;
    }

    public Style getStyle() {
        return getElement().getStyle();
    }

    public void setVisible(final boolean visible) {
        getElement().getStyle().setDisplay(visible ? null : Style.Display.NONE);
    }

    public boolean isVisible() {
        return !"none".equals(getElement().getStyle().getProperty("display"));
    }

    public void setTitle(final String title) {
        if (title == null || title.isEmpty()) {
            getElement().removeAttribute("title");
        } else {
            getElement().setAttribute("title", title);
        }
    }

    public String getTitle() {
        return getElement().getAttribute("title");
    }

    public void setWidth(final String width) {
        getElement().getStyle().setProperty("width", width);
    }

    public void setHeight(final String height) {
        getElement().getStyle().setProperty("height", height);
    }

    public void setId(final String id) {
        getElement().setId(id);
    }

    public String getId() {
        return getElement().getId();
    }

    /**
     * Event sinking is a GWT bookkeeping step for its central event dispatcher.
     * Handlers here attach real DOM listeners on registration, so there is nothing
     * to pre-declare; the mask is recorded so {@code getEventsSunk} stays truthful.
     */
    public void sinkEvents(final int eventBitsToAdd) {
        eventsSunk |= eventBitsToAdd;
    }

    public void unsinkEvents(final int eventBitsToRemove) {
        eventsSunk &= ~eventBitsToRemove;
    }

    public int getEventsSunk() {
        return eventsSunk;
    }

    private int eventsSunk;

    public void setAccessKey(final String key) {
        if (key == null || key.isEmpty()) {
            getElement().removeAttribute("accesskey");
        } else {
            getElement().setAttribute("accesskey", key);
        }
    }

    public int getTabIndex() {
        return getElement().getPropertyInt("tabIndex");
    }

    public void setTabIndex(final int index) {
        getElement().setPropertyInt("tabIndex", index);
    }
}
