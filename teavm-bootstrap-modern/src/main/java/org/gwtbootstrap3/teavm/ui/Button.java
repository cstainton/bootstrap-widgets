package org.gwtbootstrap3.teavm.ui;

public class Button extends TextWidget {

    public Button() {
        super("button");
        setAttribute("type", "button");
        addStyleName("btn");
        addStyleName("btn-primary");
    }

    public Button(final String text) {
        this();
        setText(text);
    }

    public Button setEnabled(final boolean enabled) {
        if (enabled) {
            getElement().removeAttribute("disabled");
        } else {
            getElement().setAttribute("disabled", "disabled");
        }
        return this;
    }

    public Button setButtonStyle(final String bootstrapButtonClass) {
        setStyleName("btn " + bootstrapButtonClass);
        return this;
    }
}
