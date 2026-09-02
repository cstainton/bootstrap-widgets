package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;

public class AnchorListItem extends ElementPanel {

    private final Anchor anchor = new Anchor();

    public AnchorListItem() {
        super("li");
        super.add(anchor);
    }

    public AnchorListItem(String text) {
        this();
        setText(text);
    }

    public AnchorListItem(String text, String href) {
        this(text);
        setHref(href);
    }

    @Override
    public String getText() {
        return anchor.getText();
    }

    @Override
    public void setText(String text) {
        anchor.setText(text == null ? "" : text);
    }

    public void setHref(String href) {
        anchor.setHref(href == null ? "#" : href);
    }

    public String getHref() {
        return anchor.getHref();
    }

    public void setTargetHistoryToken(String targetHistoryToken) {
        anchor.setTargetHistoryToken(targetHistoryToken);
    }

    public String getTargetHistoryToken() {
        return anchor.getTargetHistoryToken();
    }

    @Override
    public void add(Widget child) {
        anchor.add(child);
    }

    public Anchor getAnchor() {
        return anchor;
    }
}
