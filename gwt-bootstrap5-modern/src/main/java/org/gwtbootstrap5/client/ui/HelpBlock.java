package org.gwtbootstrap5.client.ui;

public class HelpBlock extends ElementPanel {

    public HelpBlock() {
        this("");
    }

    public HelpBlock(String text) {
        super("div");
        addStyleName("form-text");
        setText(text);
    }

    public void setError(String message) {
        setText(message);
        setStyleName("invalid-feedback", message != null && !message.isEmpty());
        setStyleName("d-block", message != null && !message.isEmpty());
    }

    public void clearError() {
        setError("");
    }
}
