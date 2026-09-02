package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InlineLabel;

public class Alert extends ElementPanel {

    private final InlineLabel text = new InlineLabel();
    private final HTML closeButton = new HTML("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>");
    private Variant variant;

    public Alert() {
        super("div");
        addStyleName("alert");
        getElement().setAttribute("role", "alert");
        add(text);
        setVariant(Variant.WARNING);
    }

    public Alert(String text) {
        this();
        setText(text);
    }

    public Alert(String text, Variant variant) {
        this(text);
        setVariant(variant);
    }

    @Override
    public String getText() {
        return text.getText();
    }

    @Override
    public void setText(String text) {
        this.text.setText(text == null ? "" : text);
    }

    public void setVariant(Variant variant) {
        if (this.variant != null) {
            removeStyleName(styleName(this.variant));
        }
        this.variant = variant == null ? Variant.WARNING : variant;
        addStyleName(styleName(this.variant));
    }

    public Variant getVariant() {
        return variant;
    }

    public void setDismissible(boolean dismissible) {
        setStyleName("alert-dismissible", dismissible);
        setStyleName("fade", dismissible);
        setStyleName("show", dismissible);
        if (dismissible) {
            if (closeButton.getParent() == null) {
                add(closeButton);
            }
        } else {
            closeButton.removeFromParent();
        }
    }

    private String styleName(Variant variant) {
        return "alert-" + variant.cssName();
    }
}
