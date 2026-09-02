package com.google.gwt.text.shared;

import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class SimpleSafeHtmlRenderer extends AbstractRenderer<String> {

    private static final SimpleSafeHtmlRenderer INSTANCE = new SimpleSafeHtmlRenderer();

    public static SimpleSafeHtmlRenderer getInstance() {
        return INSTANCE;
    }

    protected SimpleSafeHtmlRenderer() {
    }

    @Override
    public String render(final String object) {
        return object == null ? "" : SafeHtmlUtils.htmlEscape(object);
    }
}
