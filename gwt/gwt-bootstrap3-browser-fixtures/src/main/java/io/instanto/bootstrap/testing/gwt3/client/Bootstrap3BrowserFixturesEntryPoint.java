package io.instanto.bootstrap.testing.gwt3.client;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.ButtonGroup;
import org.gwtbootstrap3.client.ui.CheckBoxButton;
import org.gwtbootstrap3.client.ui.Collapse;
import org.gwtbootstrap3.client.ui.DropDown;
import org.gwtbootstrap3.client.ui.DropDownMenu;
import org.gwtbootstrap3.client.ui.RadioButton;
import org.gwtbootstrap3.client.ui.VerticalButtonGroup;
import org.gwtbootstrap3.client.ui.constants.ButtonGroupSize;
import org.gwtbootstrap3.client.ui.constants.ButtonSize;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.Toggle;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/** Browser-facing fixtures compiled by GWT and driven through Chrome DevTools. */
public final class Bootstrap3BrowserFixturesEntryPoint implements EntryPoint {
    private static final String CLICK_COUNT = "data-click-count";
    private static final String CHANGE_COUNT = "data-change-count";

    @Override
    public void onModuleLoad() {
        RootPanel root = RootPanel.get("fixtures");
        root.add(new HTML("<h1>GWT Bootstrap 3 interaction fixtures</h1>"));
        root.add(toggleFixture());
        root.add(disabledToggleFixture());
        root.add(checkboxFixture());
        root.add(radioFixture());
        root.add(dropdownFixture());
        root.add(collapseFixture());
        root.add(loadingFixture());
        root.add(programmaticCheckboxFixture());
        root.add(buttonTypesFixture());
        root.add(buttonSizesFixture());
        root.add(basicButtonGroupFixture());
        root.add(buttonGroupSizesFixture());
        root.add(verticalButtonGroupFixture());
        root.add(buttonGroupRemovalFixture());

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
        DropDown dropdown = tagged(new DropDown(), "behaviour/dropdown/basic");
        Anchor toggle = tagged(new Anchor("Touch menu", "#"), "behaviour/dropdown/toggle");
        toggle.setDataToggle(Toggle.DROPDOWN);
        countClicks(toggle, toggle);

        DropDownMenu menu = new DropDownMenu();
        AnchorListItem action = tagged(new AnchorListItem("Action"), "behaviour/dropdown/action");
        initializeCounter(action.getElement(), CLICK_COUNT);
        action.addClickHandler(event -> increment(action.getElement(), CLICK_COUNT));
        menu.add(action);
        dropdown.add(toggle);
        dropdown.add(menu);

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

    private Widget programmaticCheckboxFixture() {
        FlowPanel container = new FlowPanel();
        CheckBoxButton button = tagged(new CheckBoxButton("Programmatic"),
                "behaviour/check-box-button/basic");
        initializeCounter(button.getElement(), CHANGE_COUNT);
        initializeValue(button, false);
        button.addValueChangeHandler(event -> {
            increment(button.getElement(), CHANGE_COUNT);
            initializeValue(button, event.getValue());
        });

        Button silent = tagged(new Button("Set true silently"),
                "behaviour/check-box-button/set-silent");
        silent.addClickHandler(event -> {
            button.setValue(true, false);
            initializeValue(button, button.getValue());
        });
        Button firing = tagged(new Button("Set false with event"),
                "behaviour/check-box-button/set-firing");
        firing.addClickHandler(event -> {
            button.setValue(false, true);
            initializeValue(button, button.getValue());
        });

        container.add(button);
        container.add(silent);
        container.add(firing);
        return fixture("Programmatic checkbox button", container);
    }

    private Widget buttonTypesFixture() {
        FlowPanel buttons = tagged(new FlowPanel(), "behaviour/button/types");
        for (ButtonType type : ButtonType.values()) {
            Button button = new Button(type.name());
            button.setType(type);
            button.getElement().setAttribute("data-assigned-type", type.name());
            button.getElement().setAttribute("data-reported-type", button.getType().name());
            button.getElement().setAttribute("data-expected-class", type.getCssName());
            buttons.add(button);
        }
        return fixture("Button types", buttons);
    }

    private Widget buttonSizesFixture() {
        FlowPanel container = new FlowPanel();
        Button button = tagged(new Button("Sized"), "behaviour/button/sizes");
        button.setSize(ButtonSize.LARGE);
        recordButtonSize(button);
        Button change = tagged(new Button("Use small size"), "behaviour/button/sizes/change");
        change.addClickHandler(event -> {
            button.setSize(ButtonSize.SMALL);
            recordButtonSize(button);
        });
        container.add(button);
        container.add(change);
        return fixture("Button sizes", container);
    }

    private Widget basicButtonGroupFixture() {
        ButtonGroup group = tagged(new ButtonGroup(), "behaviour/button-group/basic");
        group.add(new Button("First"));
        group.add(new Button("Second"));
        group.add(new Button("Third"));
        return fixture("Basic button group", group);
    }

    private Widget buttonGroupSizesFixture() {
        FlowPanel container = new FlowPanel();
        ButtonGroup group = tagged(new ButtonGroup(), "behaviour/button-group/sizes");
        group.setDataToggle(Toggle.BUTTONS);
        group.setSize(ButtonGroupSize.LARGE);
        addPresetCheckbox(group, "First", true);
        addPresetCheckbox(group, "Second", false);
        addPresetCheckbox(group, "Third", true);
        recordButtonGroupSize(group);

        Button change = tagged(new Button("Use small group size"),
                "behaviour/button-group/sizes/change");
        change.addClickHandler(event -> {
            group.setSize(ButtonGroupSize.SMALL);
            recordButtonGroupSize(group);
        });
        container.add(group);
        container.add(change);
        return fixture("Button group sizes", container);
    }

    private Widget verticalButtonGroupFixture() {
        VerticalButtonGroup group = tagged(new VerticalButtonGroup(),
                "behaviour/button-group/vertical");
        group.add(new Button("First"));
        group.add(new Button("Second"));
        group.add(new Button("Third"));
        return fixture("Vertical button group", group);
    }

    private Widget buttonGroupRemovalFixture() {
        FlowPanel container = new FlowPanel();
        ButtonGroup group = tagged(new ButtonGroup(), "behaviour/button-group/removal");
        Button first = new Button("First");
        Button middle = tagged(new Button("Second"), "behaviour/button-group/removal/middle");
        Button third = new Button("Third");
        group.add(first);
        group.add(middle);
        group.add(third);
        group.getElement().setAttribute("data-removed-parent-null", "false");

        Button remove = tagged(new Button("Remove middle"), "behaviour/button-group/removal/action");
        remove.addClickHandler(event -> {
            group.remove(middle);
            group.getElement().setAttribute("data-removed-parent-null",
                    Boolean.toString(middle.getParent() == null));
        });
        container.add(group);
        container.add(remove);
        return fixture("Button group removal", container);
    }

    private void addPresetCheckbox(ButtonGroup group, String label, boolean value) {
        CheckBoxButton button = new CheckBoxButton(label);
        button.setValue(value);
        initializeValue(button, value);
        group.add(button);
    }

    private void recordButtonSize(Button button) {
        button.getElement().setAttribute("data-reported-size", button.getSize().name());
    }

    private void recordButtonGroupSize(ButtonGroup group) {
        group.getElement().setAttribute("data-reported-size", group.getSize().name());
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

    private void countClicks(com.google.gwt.event.dom.client.HasClickHandlers source, Widget state) {
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
