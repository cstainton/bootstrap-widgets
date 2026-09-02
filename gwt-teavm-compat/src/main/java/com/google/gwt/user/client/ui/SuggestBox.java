package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import java.util.ArrayList;
import java.util.List;

/**
 * A text box with a dropdown of completions supplied by a {@link SuggestOracle}.
 */
public class SuggestBox extends Composite implements HasValue<String> {

    /** Renders the suggestion list and reports the user's choice. */
    public static class SuggestionDisplay {

        private final FlowPanel popup = new FlowPanel();
        private SuggestBox owner;

        protected SuggestionDisplay() {
            popup.setStyleName("dropdown-menu");
            popup.getElement().getStyle().setProperty("position", "absolute");
            hideSuggestions();
        }

        void attachTo(final SuggestBox box) {
            owner = box;
        }

        Widget asPopup() {
            return popup;
        }

        protected void showSuggestions(final List<SuggestOracle.Suggestion> suggestions) {
            popup.clear();
            if (suggestions.isEmpty()) {
                hideSuggestions();
                return;
            }
            for (final SuggestOracle.Suggestion suggestion : suggestions) {
                final Anchor item = new Anchor(suggestion.getDisplayString());
                item.addStyleName("dropdown-item");
                item.setHref("javascript:;");
                item.addClickHandler(event -> {
                    event.preventDefault();
                    owner.applySuggestion(suggestion);
                });
                popup.add(item);
            }
            popup.addStyleName("show");
            popup.setVisible(true);
        }

        protected void hideSuggestions() {
            popup.removeStyleName("show");
            popup.setVisible(false);
        }

        public boolean isSuggestionListShowing() {
            return popup.isVisible();
        }
    }

    private final FlowPanel container = new FlowPanel();
    private final ValueBoxBase<String> box;
    private final SuggestionDisplay display;
    private SuggestOracle oracle;
    private int limit = 20;

    public SuggestBox() {
        this(new MultiWordSuggestOracle());
    }

    public SuggestBox(final SuggestOracle oracle) {
        this(oracle, new TextBox());
    }

    public SuggestBox(final SuggestOracle oracle, final ValueBoxBase<String> box) {
        this(oracle, box, new SuggestionDisplay());
    }

    public SuggestBox(final SuggestOracle oracle, final ValueBoxBase<String> box,
            final SuggestionDisplay display) {
        this.oracle = oracle;
        this.box = box;
        this.display = display;
        display.attachTo(this);
        container.getElement().getStyle().setProperty("position", "relative");
        container.add(box);
        container.add(display.asPopup());
        initWidget(container);
        setStyleName("gwt-SuggestBox");
        box.addKeyUpHandler(event -> refreshSuggestions());
    }

    private void refreshSuggestions() {
        final String query = box.getValueAsString();
        oracle.requestSuggestions(new SuggestOracle.Request(query, limit),
                (request, response) -> {
                    final List<SuggestOracle.Suggestion> list =
                            new ArrayList<>(response.getSuggestions());
                    display.showSuggestions(list);
                });
    }

    void applySuggestion(final SuggestOracle.Suggestion suggestion) {
        setValue(suggestion.getReplacementString(), true);
        display.hideSuggestions();
    }

    public ValueBoxBase<String> getTextBox() {
        return box;
    }

    public SuggestOracle getSuggestOracle() {
        return oracle;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(final int limit) {
        this.limit = limit;
    }

    public String getText() {
        return box.getValueAsString();
    }

    public void setText(final String text) {
        box.setValue(text);
    }

    @Override
    public String getValue() {
        return box.getValueAsString();
    }

    @Override
    public void setValue(final String value) {
        setValue(value, false);
    }

    @Override
    public void setValue(final String value, final boolean fireEvents) {
        final String oldValue = getValue();
        box.setValue(value);
        if (fireEvents) {
            ValueChangeEvent.fireIfNotEqual(this, oldValue, getValue());
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<String> handler) {
        return addHandler(handler, ValueChangeEvent.<String>getType());
    }
}
