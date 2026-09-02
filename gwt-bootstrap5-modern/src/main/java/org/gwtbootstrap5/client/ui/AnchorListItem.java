package org.gwtbootstrap5.client.ui;

public class AnchorListItem extends ElementPanel {

    private final Anchor anchor = new Anchor();

    public AnchorListItem() {
        super("li");
        add(anchor);
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

    public Anchor getAnchor() {
        return anchor;
    }
}
