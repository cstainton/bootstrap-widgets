package io.instanto.bootstrap.testing.gwt5.client;

import io.instanto.bootstrap5.client.ui.Button;
import io.instanto.bootstrap5.client.ui.ButtonGroup;
import io.instanto.bootstrap5.client.ui.CheckBoxButton;
import io.instanto.bootstrap5.client.ui.Collapse;
import io.instanto.bootstrap5.client.ui.DropDown;
import io.instanto.bootstrap5.client.ui.DropDownItem;
import io.instanto.bootstrap5.client.ui.RadioButton;
import io.instanto.bootstrap5.client.ui.constants.Toggle;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/** Bootstrap 5 fixtures sharing the browser contract used by the Bootstrap 3 app. */
public final class Bootstrap5BrowserFixturesEntryPoint implements EntryPoint {
    private static final String CLICK_COUNT = "data-click-count";
    private static final String CHANGE_COUNT = "data-change-count";

    @Override
    public void onModuleLoad() {
        RootPanel root = RootPanel.get("fixtures");
        root.add(new HTML("<h1>GWT Bootstrap 5 interaction fixtures</h1>"));
        root.add(toggleFixture());
        root.add(disabledToggleFixture());
        root.add(checkboxFixture());
        root.add(radioFixture());
        root.add(dropdownFixture());
        root.add(collapseFixture());
        root.add(loadingFixture());

        Document.get().getBody().setAttribute("data-fixtures-ready", "true");
        markReady();
    }

    private Widget toggleFixture() {
        Button button = tagged(new Button("Toggle"), "behaviour/toggle-button/basic");
        button.setDataToggle(Toggle.BUTTON);
        countClicks(button, button);
        return fixture("Toggle button", button);
    }

    private Widget disabledToggleFixture() {
        Button button = tagged(new Button("Disabled"), "behaviour/toggle-button/disabled");
        button.setDataToggle(Toggle.BUTTON);
        button.setEnabled(false);
        countClicks(button, button);
        return fixture("Disabled toggle button", button);
    }

    private Widget checkboxFixture() {
        ButtonGroup group = tagged(new ButtonGroup(), "behaviour/check-box-buttons/independent");
        group.setDataToggle(Toggle.BUTTONS);
        addCheckbox(group, "First", "behaviour/check-box-button/first");
        addCheckbox(group, "Second", "behaviour/check-box-button/second");
        addCheckbox(group, "Third", "behaviour/check-box-button/third");
        return fixture("Checkbox buttons", group);
    }

    private Widget radioFixture() {
        ButtonGroup group = tagged(new ButtonGroup(), "behaviour/radio-buttons/exclusive");
        group.setDataToggle(Toggle.BUTTONS);
        group.setName("touch-radio-fixture");

        RadioButton first = tagged(new RadioButton("touch-radio-fixture", "First"),
                "behaviour/radio-button/first");
        RadioButton second = tagged(new RadioButton("touch-radio-fixture", "Second"),
                "behaviour/radio-button/second");
        first.setActive(true);
        second.setActive(false);
        initializeCounter(first.getElement(), CHANGE_COUNT);
        initializeCounter(second.getElement(), CHANGE_COUNT);
        initializeValue(first, true);
        initializeValue(second, false);
        countClicks(first, first);
        countClicks(second, second);

        first.addValueChangeHandler(event -> {
            increment(first.getElement(), CHANGE_COUNT);
            initializeValue(first, first.getValue());
            initializeValue(second, second.getValue());
        });
        second.addValueChangeHandler(event -> {
            increment(second.getElement(), CHANGE_COUNT);
            initializeValue(first, first.getValue());
            initializeValue(second, second.getValue());
        });
        group.add(first);
        group.add(second);
        return fixture("Radio buttons", group);
    }

    private Widget dropdownFixture() {
        FlowPanel container = new FlowPanel();
        DropDown dropdown = tagged(new DropDown("Touch menu"), "behaviour/dropdown/basic");
        tagged(dropdown.getToggle(), "behaviour/dropdown/toggle");
        countClicks(dropdown.getToggle(), dropdown.getToggle());

        DropDownItem action = tagged(new DropDownItem("Action", "#"), "behaviour/dropdown/action");
        initializeCounter(action.getElement(), CLICK_COUNT);
        action.addClickHandler(event -> increment(action.getElement(), CLICK_COUNT));
        dropdown.addItem(action);

        Button outside = tagged(new Button("Outside"), "behaviour/dropdown/outside");
        outside.getElement().getStyle().setProperty("marginTop", "80px");
        container.add(dropdown);
        container.add(outside);
        return fixture("Dropdown", container);
    }

    private Widget collapseFixture() {
        FlowPanel container = new FlowPanel();
        Button target = tagged(new Button("Touch collapse"), "behaviour/collapse/toggle");
        Collapse collapse = tagged(new Collapse(), "behaviour/collapse/basic");
        collapse.getElement().setId("touch-collapse-content");
        collapse.setToggle(false);
        collapse.add(new HTML("<p>Collapsed content</p>"));
        collapse.getElement().setAttribute("data-event-order", "");
        collapse.addShowHandler(event -> appendEvent(collapse.getElement(), "show"));
        collapse.addShownHandler(event -> appendEvent(collapse.getElement(), "shown"));
        collapse.addHideHandler(event -> appendEvent(collapse.getElement(), "hide"));
        collapse.addHiddenHandler(event -> appendEvent(collapse.getElement(), "hidden"));

        target.setDataToggle(Toggle.COLLAPSE);
        target.setDataTargetWidget(collapse);
        countClicks(target, target);
        container.add(target);
        container.add(collapse);
        return fixture("Collapse", container);
    }

    private Widget loadingFixture() {
        Button button = tagged(new Button("Save"), "behaviour/button/loading");
        button.setDataLoadingText("Saving...");
        countClicks(button, button);
        button.addClickHandler(event -> {
            button.state().loading();
            new Timer() {
                @Override
                public void run() {
                    button.state().reset();
                }
            }.schedule(750);
        });
        return fixture("Loading button", button);
    }

    private void addCheckbox(ButtonGroup group, String label, String fixtureId) {
        CheckBoxButton button = tagged(new CheckBoxButton(label), fixtureId);
        initializeCounter(button.getElement(), CHANGE_COUNT);
        initializeValue(button, false);
        countClicks(button, button);
        button.addValueChangeHandler(event -> {
            increment(button.getElement(), CHANGE_COUNT);
            initializeValue(button, event.getValue());
        });
        group.add(button);
    }

    private FlowPanel fixture(String title, Widget content) {
        FlowPanel fixture = new FlowPanel();
        fixture.setStyleName("fixture");
        fixture.add(new HTML("<h2>" + title + "</h2>"));
        fixture.add(content);
        return fixture;
    }

    private <T extends Widget> T tagged(T widget, String fixtureId) {
        widget.getElement().setAttribute("data-testid", fixtureId);
        return widget;
    }

    private void countClicks(HasClickHandlers source, Widget state) {
        initializeCounter(state.getElement(), CLICK_COUNT);
        source.addClickHandler(event -> increment(state.getElement(), CLICK_COUNT));
    }

    private void initializeValue(com.google.gwt.user.client.ui.HasValue<Boolean> widget, boolean value) {
        ((Widget) widget).getElement().setAttribute("data-value", Boolean.toString(value));
    }

    private void initializeCounter(Element element, String attribute) {
        element.setAttribute(attribute, "0");
    }

    private void increment(Element element, String attribute) {
        String current = element.getAttribute(attribute);
        int value = current == null || current.isEmpty() ? 0 : Integer.parseInt(current);
        element.setAttribute(attribute, Integer.toString(value + 1));
    }

    private void appendEvent(Element element, String event) {
        String current = element.getAttribute("data-event-order");
        element.setAttribute("data-event-order", current.isEmpty() ? event : current + "," + event);
    }

    private static native void markReady() /*-{
        $wnd.__bootstrapWidgetFixturesReady = true;
    }-*/;
}
