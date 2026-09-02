package org.gwtbootstrap5.teavm.ui;

public class Card extends FlowPanel {

    private final FlowPanel body = new FlowPanel();

    public Card() {
        addStyleName("card");
        body.addStyleName("card-body");
        add(body);
    }

    public Card addBody(final Widget child) {
        body.add(child);
        return this;
    }
}
