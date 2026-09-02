package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InlineLabel;
import org.gwtbootstrap5.client.ui.base.HasType;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.AlertType;

public class Alert extends ElementPanel implements HasType<AlertType> {

    private final InlineLabel text = new InlineLabel();
    private final HTML closeButton = new HTML("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>");
    private Variant variant;

    public Alert() {
        super("div");
        addStyleName("alert");
        getElement().setAttribute("role", "alert");
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

    public Alert(String text, AlertType type) {
        this(text);
        setType(type);
    }

    @Override
    public String getText() {
        return text.getText();
    }

    @Override
    public void setText(String text) {
        String effectiveText = text == null ? "" : text;
        this.text.setText(effectiveText);
        if (effectiveText.isEmpty()) {
            this.text.removeFromParent();
        } else if (this.text.getParent() == null) {
            insert(this.text, 0);
        }
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

    @Override
    public void setType(AlertType type) {
        StyleHelper.addUniqueEnumStyleName(this, AlertType.class, type == null ? AlertType.DEFAULT : type);
    }

    @Override
    public AlertType getType() {
        return AlertType.fromStyleName(getStyleName());
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

    public void setDismissable(boolean dismissable) {
        setDismissible(dismissable);
    }

    public boolean isDismissable() {
        return closeButton.getParent() != null;
    }

    public void setFade(boolean fade) {
        setStyleName("fade", fade);
        setStyleName("show", fade);
    }

    public boolean isFade() {
        return getStyleName().contains("fade");
    }

    public void close() {
        removeFromParent();
    }

    private String styleName(Variant variant) {
        return "alert-" + variant.cssName();
    }
}
