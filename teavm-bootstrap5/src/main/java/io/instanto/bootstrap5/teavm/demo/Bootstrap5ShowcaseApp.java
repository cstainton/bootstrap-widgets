/*
 * #%L
 * GWT Bootstrap
 * %%
 * Copyright (C) 2026 Carl Stainton
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.instanto.bootstrap5.teavm.demo;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import io.instanto.bootstrap5.client.Bootstrap5;
import io.instanto.bootstrap5.client.ui.Alert;
import io.instanto.bootstrap5.client.ui.AnchorListItem;
import io.instanto.bootstrap5.client.ui.Badge;
import io.instanto.bootstrap5.client.ui.Breadcrumbs;
import io.instanto.bootstrap5.client.ui.Button;
import io.instanto.bootstrap5.client.ui.ButtonGroup;
import io.instanto.bootstrap5.client.ui.CheckBox;
import io.instanto.bootstrap5.client.ui.CheckBoxButton;
import io.instanto.bootstrap5.client.ui.Column;
import io.instanto.bootstrap5.client.ui.Container;
import io.instanto.bootstrap5.client.ui.Dialogs;
import io.instanto.bootstrap5.client.ui.Divider;
import io.instanto.bootstrap5.client.ui.DropDown;
import io.instanto.bootstrap5.client.ui.DropDownHeader;
import io.instanto.bootstrap5.client.ui.DropDownItem;
import io.instanto.bootstrap5.client.ui.FormGroup;
import io.instanto.bootstrap5.client.ui.FormLabel;
import io.instanto.bootstrap5.client.ui.Heading;
import io.instanto.bootstrap5.client.ui.HelpBlock;
import io.instanto.bootstrap5.client.ui.Icon;
import io.instanto.bootstrap5.client.ui.Input;
import io.instanto.bootstrap5.client.ui.InputGroup;
import io.instanto.bootstrap5.client.ui.InputGroupAddon;
import io.instanto.bootstrap5.client.ui.InputGroupButton;
import io.instanto.bootstrap5.client.ui.ListBox;
import io.instanto.bootstrap5.client.ui.ListGroup;
import io.instanto.bootstrap5.client.ui.ListGroupItem;
import io.instanto.bootstrap5.client.ui.Modal;
import io.instanto.bootstrap5.client.ui.ModalFooter;
import io.instanto.bootstrap5.client.ui.NavTabs;
import io.instanto.bootstrap5.client.ui.PageHeader;
import io.instanto.bootstrap5.client.ui.Pagination;
import io.instanto.bootstrap5.client.ui.Panel;
import io.instanto.bootstrap5.client.ui.PanelBody;
import io.instanto.bootstrap5.client.ui.PanelFooter;
import io.instanto.bootstrap5.client.ui.PanelHeader;
import io.instanto.bootstrap5.client.ui.Popover;
import io.instanto.bootstrap5.client.ui.Pre;
import io.instanto.bootstrap5.client.ui.Progress;
import io.instanto.bootstrap5.client.ui.ProgressBar;
import io.instanto.bootstrap5.client.ui.Radio;
import io.instanto.bootstrap5.client.ui.RadioButton;
import io.instanto.bootstrap5.client.ui.Range;
import io.instanto.bootstrap5.client.ui.Row;
import io.instanto.bootstrap5.client.ui.TabContent;
import io.instanto.bootstrap5.client.ui.TabListItem;
import io.instanto.bootstrap5.client.ui.TabPane;
import io.instanto.bootstrap5.client.ui.TabPanel;
import io.instanto.bootstrap5.client.ui.TextArea;
import io.instanto.bootstrap5.client.ui.TextBox;
import io.instanto.bootstrap5.client.ui.Tooltip;
import io.instanto.bootstrap5.client.ui.constants.AlertType;
import io.instanto.bootstrap5.client.ui.constants.ButtonSize;
import io.instanto.bootstrap5.client.ui.constants.ButtonType;
import io.instanto.bootstrap5.client.ui.constants.IconSize;
import io.instanto.bootstrap5.client.ui.constants.IconType;
import io.instanto.bootstrap5.client.ui.constants.PaginationSize;
import io.instanto.bootstrap5.client.ui.constants.Placement;
import io.instanto.bootstrap5.client.ui.constants.ProgressBarType;
import io.instanto.bootstrap5.client.ui.constants.ValidationState;
import io.instanto.bootstrap5.client.ui.html.Paragraph;
import io.instanto.bootstrap5.client.ui.theme.ColorMode;
import io.instanto.bootstrap5.client.ui.theme.ColorModes;
import io.instanto.bootstrap5.client.ui.theme.StandardThemes;
import io.instanto.bootstrap5.client.ui.theme.Theme;
import io.instanto.bootstrap5.client.ui.theme.Themes;
import io.instanto.bootstrap5.themes.client.BootswatchThemes;

/**
 * Showcase for the TeaVM build of the Bootstrap 5 widgets.
 *
 * <p>Laid out like its Bootstrap 3 counterpart: a page header, then one panel per section
 * with the live example in the panel body and the code that produced it in the footer.
 * Every widget here is compiled from the same sources the GWT build uses; nothing in this
 * file is a TeaVM-specific reimplementation.</p>
 */
public final class Bootstrap5ShowcaseApp {

    /** The compiled GWT showcase sits at bootstrap5/, and its stylesheets come with it. */
    private static final String CSS = "bootstrap5/GwtBootstrap5Showcase/css/";

    private static final Paragraph THEME_STATUS = new Paragraph();

    public static void main(final String[] args) {
        Themes.register(StandardThemes.all(CSS));
        Themes.register(BootswatchThemes.all(CSS));
        Themes.restore(StandardThemes.bootstrap(CSS));
        ColorModes.restore(ColorMode.LIGHT);

        final Container container = new Container();
        container.addStyleName("py-4");
        container.add(page());
        Bootstrap5.mount(container);
    }

    private static Row page() {
        final Row row = new Row();
        final Column column = new Column(12);

        final PageHeader header = new PageHeader();
        header.setText("Bootstrap 5 on TeaVM");
        header.setSubText("the same widgets, compiled to JavaScript by TeaVM");
        column.add(header);

        column.add(panel("Theme and colour mode", themes(),
                "Themes.register(BootswatchThemes.all(\"css/\"));\n"
                + "Themes.apply(\"darkly\");\n\n"
                + "// Bootstrap 5's own light/dark switch, separate from the theme\n"
                + "ColorModes.apply(ColorMode.DARK);   // sets data-bs-theme\n"
                + "ColorModes.toggle();"));
        column.add(panel("Buttons", buttons(),
                "new Button(\"Primary\", ButtonType.PRIMARY);\n"
                + "button.setSize(ButtonSize.LARGE);\n"
                + "ButtonGroup group = new ButtonGroup();"));
        column.add(panel("Toggle buttons", toggles(),
                "// Bootstrap 5 drops .btn-group[data-toggle] for .btn-check\n"
                + "// plus a sibling label, so these are real inputs\n"
                + "new CheckBoxButton(\"Bold\");\n"
                + "new RadioButton(\"align\", \"Left\");"));
        column.add(panel("Alerts", alerts(),
                "new Alert(\"...\", AlertType.SUCCESS);\n"
                + "alert.setDismissable(true);"));
        column.add(panel("Badges", badges(),
                "new Badge(\"42\");\n"
                + "badge.addStyleName(\"text-bg-primary rounded-pill\");"));
        column.add(panel("Icons", icons(),
                "// Bootstrap Icons, not Font Awesome: 2078 constants\n"
                + "new Icon(IconType.HEART);\n"
                + "icon.setSize(IconSize.XX_LARGE);\n"
                + "icon.setSpin(true);"));
        column.add(panel("Forms", forms(),
                "TextBox box = new TextBox();\nbox.setPlaceholder(\"...\");\n"
                + "ListBox select = new ListBox();   // renders .form-select\n"
                + "new CheckBox(\"Clicking the label toggles this\");"));
        column.add(panel("Validation", validation(),
                "FormGroup group = new FormGroup();\n"
                + "group.setValidationState(ValidationState.ERROR);\n"
                + "// .is-invalid on the control, .invalid-feedback on the help block"));
        column.add(panel("Range", range(),
                "Range volume = new Range(0, 100);\nvolume.setValue(50d);\n"
                + "volume.setContinuous(true);   // fires while dragging"));
        column.add(panel("Input groups", inputGroups(),
                "InputGroup group = new InputGroup();\n"
                + "group.add(new InputGroupAddon(\"@\"));\n"
                + "group.add(new InputGroupButton(new Button(\"Go\")));"));
        column.add(panel("Progress and list groups", status(),
                "Progress progress = new Progress();\n"
                + "progress.add(new ProgressBar(60));\n"
                + "new ListGroup();"));
        column.add(panel("Tabs", tabs(),
                "TabPanel tabPanel = new TabPanel();\n"
                + "tabPanel.getTabs().add(new TabListItem(\"First\", \"firstPane\"));\n"
                + "tabPanel.getContent().add(firstPane);"));
        column.add(panel("Dropdowns", dropdowns(),
                "DropDown dropDown = new DropDown(\"Toggle\");\n"
                + "dropDown.addItem(new DropDownItem(\"Action\", \"#\"));\n"
                + "dropDown.setDropUp(true);"));
        column.add(panel("Pagination and breadcrumbs", navigation(),
                "Pagination pagination = new Pagination();\n"
                + "pagination.setPaginationSize(PaginationSize.SMALL);\n"
                + "new Breadcrumbs();"));
        column.add(panel("Tooltips and popovers", overlays(),
                "new Tooltip(button, \"Tooltip text\");\n"
                + "new Popover(button, \"Title\", \"Content\");\n"
                + "// reaching Bootstrap's JavaScript through @JSBody, not JSNI"));
        column.add(panel("Modals and dialogs", modals(),
                "Modal modal = new Modal();\nmodal.show();\n\n"
                + "// native replacements for window.alert/confirm/prompt,\n"
                + "// which is what Bootbox did before jQuery was dropped\n"
                + "Dialogs.confirm(\"Delete?\", result -> ...);"));

        row.add(column);
        return row;
    }

    private static Widget themes() {
        final PanelBody body = new PanelBody();

        final ListBox picker = new ListBox();
        for (final Theme theme : Themes.getThemes()) {
            picker.addItem(theme.getDisplayName(), theme.getName());
        }
        picker.addChangeHandler(event -> {
            Themes.apply(picker.getSelectedValue());
            showTheme();
        });

        final Button mode = new Button("Toggle light/dark", ButtonType.SECONDARY);
        mode.addClickHandler(event -> {
            ColorModes.toggle();
            showTheme();
        });

        showTheme();
        body.add(picker);
        body.add(mode);
        body.add(THEME_STATUS);
        return body;
    }

    private static void showTheme() {
        final Theme active = Themes.getCurrent();
        THEME_STATUS.setText("Theme: " + (active == null ? "none" : active.getDisplayName())
                + " · colour mode: " + (ColorModes.isDark() ? "dark" : "light"));
    }

    private static Widget buttons() {
        final PanelBody body = new PanelBody();
        body.addStyleName("d-flex flex-wrap gap-2 align-items-center");
        body.add(new Button("Default"));
        body.add(button("Primary", ButtonType.PRIMARY));
        body.add(button("Secondary", ButtonType.SECONDARY));
        body.add(button("Success", ButtonType.SUCCESS));
        body.add(button("Danger", ButtonType.DANGER));
        body.add(button("Warning", ButtonType.WARNING));
        body.add(button("Info", ButtonType.INFO));
        body.add(button("Light", ButtonType.LIGHT));
        body.add(button("Dark", ButtonType.DARK));
        body.add(button("Link", ButtonType.LINK));

        final Button large = new Button("Large", ButtonType.PRIMARY);
        large.setSize(ButtonSize.LARGE);
        final Button small = new Button("Small", ButtonType.PRIMARY);
        small.setSize(ButtonSize.SMALL);
        body.add(large);
        body.add(small);

        final ButtonGroup group = new ButtonGroup();
        group.add(new Button("Left"));
        group.add(new Button("Middle"));
        group.add(new Button("Right"));
        body.add(group);
        return body;
    }

    private static Button button(final String text, final ButtonType type) {
        return new Button(text, type);
    }

    private static Widget toggles() {
        final PanelBody body = new PanelBody();
        body.addStyleName("d-flex flex-wrap gap-3 align-items-center");

        final ButtonGroup formatting = new ButtonGroup();
        formatting.add(new CheckBoxButton("Bold"));
        formatting.add(new CheckBoxButton("Italic"));
        formatting.add(new CheckBoxButton("Underline"));

        final ButtonGroup alignment = new ButtonGroup();
        final RadioButton left = new RadioButton("align", "Left");
        left.setValue(true);
        alignment.add(left);
        alignment.add(new RadioButton("align", "Centre"));
        alignment.add(new RadioButton("align", "Right"));

        body.add(formatting);
        body.add(alignment);
        return body;
    }

    private static Widget alerts() {
        final PanelBody body = new PanelBody();
        body.add(alert("A success alert.", AlertType.SUCCESS, false));
        body.add(alert("An informational alert.", AlertType.INFO, false));
        body.add(alert("A warning alert.", AlertType.WARNING, false));
        body.add(alert("A dismissable danger alert.", AlertType.DANGER, true));
        return body;
    }

    private static Alert alert(final String text, final AlertType type, final boolean dismissable) {
        final Alert alert = new Alert(text, type);
        alert.setDismissable(dismissable);
        return alert;
    }

    private static Widget badges() {
        final PanelBody body = new PanelBody();
        body.addStyleName("d-flex flex-wrap gap-2 align-items-center");
        for (final String variant : new String[] {"primary", "secondary", "success", "danger",
                "warning", "info"}) {
            final Badge badge = new Badge(variant);
            badge.addStyleName("text-bg-" + variant);
            body.add(badge);
        }
        final Badge pill = new Badge("42");
        pill.addStyleName("text-bg-primary rounded-pill");
        body.add(pill);
        return body;
    }

    private static Widget icons() {
        final PanelBody body = new PanelBody();
        body.addStyleName("d-flex flex-wrap gap-3 align-items-center");
        body.add(new Icon(IconType.HOUSE));
        body.add(new Icon(IconType.HEART));
        body.add(new Icon(IconType.STAR));
        body.add(new Icon(IconType.GEAR));
        body.add(new Icon(IconType.ENVELOPE));

        final Icon large = new Icon(IconType.CAMERA);
        large.setSize(IconSize.XX_LARGE);
        body.add(large);

        final Icon spinner = new Icon(IconType.ARROW_CLOCKWISE);
        spinner.setSize(IconSize.X_LARGE);
        spinner.setSpin(true);
        body.add(spinner);
        return body;
    }

    private static Widget forms() {
        final PanelBody body = new PanelBody();

        final FormGroup text = new FormGroup();
        text.add(new FormLabel("Text"));
        final TextBox textBox = new TextBox();
        textBox.setPlaceholder("A TextBox from the shared widget source");
        text.add(textBox);

        final FormGroup area = new FormGroup();
        area.add(new FormLabel("Textarea"));
        final TextArea textArea = new TextArea();
        textArea.setVisibleLines(3);
        textArea.setPlaceholder("A TextArea");
        area.add(textArea);

        final FormGroup select = new FormGroup();
        select.add(new FormLabel("Select"));
        final ListBox listBox = new ListBox();
        listBox.addItem("First");
        listBox.addItem("Second");
        listBox.addItem("Third");
        select.add(listBox);

        final FormGroup checks = new FormGroup();
        final CheckBox checkBox = new CheckBox("Clicking this label toggles the box");
        final Radio one = new Radio("choice", "One");
        one.setValue(true);
        checks.add(checkBox);
        checks.add(one);
        checks.add(new Radio("choice", "Two"));

        body.add(text);
        body.add(area);
        body.add(select);
        body.add(checks);
        return body;
    }

    private static Widget validation() {
        final PanelBody body = new PanelBody();

        final FormGroup group = new FormGroup();
        group.add(new FormLabel("Email"));
        final TextBox email = new TextBox();
        email.setPlaceholder("someone@example.com");
        final HelpBlock help = new HelpBlock();
        help.setText("Enter an address containing @ to clear the error.");
        group.add(email);
        group.add(help);
        group.setValidationState(ValidationState.ERROR);

        email.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(final ValueChangeEvent<String> event) {
                final boolean valid = event.getValue() != null && event.getValue().contains("@");
                group.setValidationState(valid ? ValidationState.SUCCESS : ValidationState.ERROR);
                help.setText(valid ? "That will do." : "Enter an address containing @.");
            }
        });

        body.add(group);
        return body;
    }

    private static Widget range() {
        final PanelBody body = new PanelBody();
        final HTML echo = new HTML("<p class='mb-0'>Volume: <strong>50</strong></p>");

        final FormGroup group = new FormGroup();
        group.add(new FormLabel("Volume"));
        final Range volume = new Range(0, 100);
        volume.setValue(50d);
        volume.setContinuous(true);
        volume.addValueChangeHandler(new ValueChangeHandler<Double>() {
            @Override
            public void onValueChange(final ValueChangeEvent<Double> event) {
                echo.setHTML("<p class='mb-0'>Volume: <strong>"
                        + (long) event.getValue().doubleValue() + "</strong></p>");
            }
        });
        group.add(volume);

        final FormGroup stepped = new FormGroup();
        stepped.add(new FormLabel("Stepped, 0 to 10 in twos"));
        final Range steps = new Range(0, 10);
        steps.setStep(2);
        steps.setValue(4d);
        stepped.add(steps);

        body.add(group);
        body.add(echo);
        body.add(stepped);
        return body;
    }

    private static Widget inputGroups() {
        final PanelBody body = new PanelBody();

        final InputGroup at = new InputGroup();
        at.addStyleName("mb-3");
        at.add(new InputGroupAddon("@"));
        final Input username = new Input("text");
        username.setPlaceholder("Username");
        at.add(username);

        final InputGroup go = new InputGroup();
        final Input search = new Input("text");
        search.setPlaceholder("Search");
        go.add(search);
        go.add(new InputGroupButton(new Button("Go", ButtonType.PRIMARY)));

        body.add(at);
        body.add(go);
        return body;
    }

    private static Widget status() {
        final PanelBody body = new PanelBody();

        final Progress progress = new Progress();
        progress.add(new ProgressBar(60));

        final Progress striped = new Progress();
        final ProgressBar bar = new ProgressBar(40);
        bar.setType(ProgressBarType.SUCCESS);
        striped.add(bar);

        final ListGroup listGroup = new ListGroup();
        listGroup.add(new ListGroupItem("Widgets: io.instanto.bootstrap5.client.ui"));
        listGroup.add(new ListGroupItem("DOM and events: TeaVM JSO"));
        listGroup.add(new ListGroupItem("Bootstrap JS: reached through BootstrapJs"));
        listGroup.add(new ListGroupItem("Messages: ResourceBundle, resolved at runtime"));

        body.add(progress);
        body.add(striped);
        body.add(listGroup);
        return body;
    }

    private static Widget tabs() {
        final PanelBody body = new PanelBody();

        final TabPanel tabPanel = new TabPanel();
        final TabListItem first = new TabListItem("First", "teavmFirstPane");
        first.setActive(true);
        tabPanel.getTabs().add(first);
        tabPanel.getTabs().add(new TabListItem("Second", "teavmSecondPane"));
        tabPanel.getTabs().add(new TabListItem("Third", "teavmThirdPane"));

        final TabContent content = tabPanel.getContent();
        content.add(pane("teavmFirstPane", "First tab content, shown by Bootstrap's own JavaScript.", true));
        content.add(pane("teavmSecondPane", "Second tab content.", false));
        content.add(pane("teavmThirdPane", "Third tab content.", false));

        final NavTabs plain = new NavTabs();
        plain.add(new AnchorListItem("Nav item"));
        plain.add(new AnchorListItem("Another"));

        body.add(tabPanel);
        body.add(plain);
        return body;
    }

    private static TabPane pane(final String id, final String text, final boolean active) {
        final TabPane pane = new TabPane();
        pane.getElement().setId(id);
        pane.setActive(active);
        pane.add(new Paragraph(text));
        return pane;
    }

    private static Widget dropdowns() {
        final PanelBody body = new PanelBody();
        body.addStyleName("d-flex flex-wrap gap-2 align-items-center");

        final DropDown dropDown = new DropDown("Click to toggle");
        dropDown.addMenuWidget(new DropDownHeader("Header"));
        final DropDownItem action = new DropDownItem("Action", "#");
        action.add(new Icon(IconType.CAMERA));
        dropDown.addItem(action);
        dropDown.addMenuWidget(new Divider());
        final DropDownItem disabled = new DropDownItem("Disabled", "#");
        disabled.setDisabled(true);
        dropDown.addItem(disabled);

        final DropDown dropUp = new DropDown("Dropup");
        dropUp.setDropUp(true);
        dropUp.addItem(new DropDownItem("Action", "#"));
        dropUp.addItem(new DropDownItem("Another action", "#"));

        body.add(dropDown);
        body.add(dropUp);
        return body;
    }

    private static Widget navigation() {
        final PanelBody body = new PanelBody();

        final Pagination pagination = new Pagination();
        pagination.add(new AnchorListItem("Previous"));
        pagination.add(new AnchorListItem("1"));
        pagination.add(new AnchorListItem("2"));
        pagination.add(new AnchorListItem("3"));
        pagination.add(new AnchorListItem("Next"));

        final Pagination small = new Pagination();
        small.setPaginationSize(PaginationSize.SMALL);
        small.add(new AnchorListItem("1"));
        small.add(new AnchorListItem("2"));

        final Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new AnchorListItem("Home"));
        breadcrumbs.add(new AnchorListItem("Library"));
        breadcrumbs.add(new AnchorListItem("Data"));

        body.add(pagination);
        body.add(small);
        body.add(breadcrumbs);
        return body;
    }

    private static Widget overlays() {
        final PanelBody body = new PanelBody();
        body.addStyleName("d-flex flex-wrap gap-2 align-items-center");

        final Placement[] placements = {Placement.TOP, Placement.RIGHT, Placement.BOTTOM,
                Placement.LEFT};
        for (final Placement placement : placements) {
            final Button button = new Button("Tooltip " + placement.name().toLowerCase());
            final Tooltip tooltip = new Tooltip(button, "Tooltip on " + placement.name().toLowerCase());
            tooltip.setPlacement(placement);
            body.add(tooltip);
        }

        final Button popoverButton = new Button("Popover", ButtonType.PRIMARY);
        final Popover popover = new Popover(popoverButton, "A popover",
                "Its content, positioned by Popper through Bootstrap 5.");
        popover.setPlacement(Placement.TOP);
        body.add(popover);
        return body;
    }

    private static Widget modals() {
        final PanelBody body = new PanelBody();
        body.addStyleName("d-flex flex-wrap gap-2 align-items-center");

        final Modal modal = new Modal();
        modal.setTitle("A Bootstrap 5 modal");
        modal.setFade(true);
        modal.setClosable(true);
        modal.addToBody(new Paragraph(
                "Shown through Bootstrap's own JavaScript, called via TeaVM @JSBody."));
        final ModalFooter footer = new ModalFooter();
        final Button dismiss = new Button("Close", ButtonType.SECONDARY);
        dismiss.addClickHandler(event -> modal.hide());
        footer.add(dismiss);
        modal.add(footer);

        final Button open = new Button("Show modal", ButtonType.PRIMARY);
        open.addClickHandler(event -> modal.show());

        final Paragraph answer = new Paragraph();

        final Button confirm = new Button("Confirm", ButtonType.SECONDARY);
        confirm.addClickHandler(event -> Dialogs.confirm("Delete this record?",
                result -> answer.setText("Confirm returned " + result)));

        final Button prompt = new Button("Prompt", ButtonType.SECONDARY);
        prompt.addClickHandler(event -> Dialogs.prompt("What is your name?",
                result -> answer.setText(result == null ? "Prompt cancelled" : "Hello, " + result)));

        body.add(open);
        body.add(confirm);
        body.add(prompt);
        body.add(answer);
        body.add(modal);
        return body;
    }

    private static Panel panel(final String title, final Widget example, final String code) {
        final Panel panel = new Panel();
        panel.addStyleName("mb-4");
        final PanelHeader header = new PanelHeader();
        header.add(new Heading(3, title));
        final PanelBody body = new PanelBody();
        body.add(example);
        final PanelFooter footer = new PanelFooter();
        final Pre pre = new Pre(code);
        pre.addStyleName("mb-0 small");
        footer.add(pre);
        panel.add(header);
        panel.add(body);
        panel.add(footer);
        return panel;
    }
}
