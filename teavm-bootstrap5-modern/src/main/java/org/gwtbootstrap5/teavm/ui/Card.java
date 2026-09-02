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

    public Card addHeader(final Widget child) {
        getElement().insertBefore(child.getElement(), body.getElement());
        return this;
    }

    public Card addFooter(final Widget child) {
        add(child);
        return this;
    }
}
