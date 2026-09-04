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
import io.instanto.bootstrap5.client.Bootstrap5Resources;
import io.instanto.bootstrap5.extras.markdown.client.MarkdownResources;
import io.instanto.bootstrap5.extras.richtext.client.RichTextResources;
import io.instanto.bootstrap5.extras.richtext.client.ui.RichTextEditor;
import io.instanto.bootstrap5.extras.slider.client.SliderResources;
import io.instanto.bootstrap5.extras.slider.client.ui.Slider;
import io.instanto.bootstrap5.extras.markdown.client.ui.MarkdownEditor;
import io.instanto.bootstrap5.client.ui.BlockQuote;
import io.instanto.bootstrap5.client.ui.ButtonToolBar;
import io.instanto.bootstrap5.client.ui.Card;
import io.instanto.bootstrap5.client.ui.Code;
import io.instanto.bootstrap5.client.ui.Description;
import io.instanto.bootstrap5.client.ui.DescriptionData;
import io.instanto.bootstrap5.client.ui.DescriptionTitle;
import io.instanto.bootstrap5.client.ui.IconStack;
import io.instanto.bootstrap5.client.ui.Image;
import io.instanto.bootstrap5.client.ui.Jumbotron;
import io.instanto.bootstrap5.client.ui.Lead;
import io.instanto.bootstrap5.client.ui.LinkedGroup;
import io.instanto.bootstrap5.client.ui.LinkedGroupItem;
import io.instanto.bootstrap5.client.ui.Pager;
import io.instanto.bootstrap5.client.ui.VerticalButtonGroup;
import io.instanto.bootstrap5.client.ui.Well;
import io.instanto.bootstrap5.client.ui.constants.ButtonGroupSize;
import io.instanto.bootstrap5.client.ui.constants.IconFlip;
import io.instanto.bootstrap5.client.ui.constants.IconRotate;
import io.instanto.bootstrap5.client.ui.constants.ProgressType;
import io.instanto.bootstrap5.client.ui.Anchor;
import io.instanto.bootstrap5.client.ui.Carousel;
import io.instanto.bootstrap5.client.ui.CarouselControl;
import io.instanto.bootstrap5.client.ui.CarouselIndicator;
import io.instanto.bootstrap5.client.ui.CarouselIndicators;
import io.instanto.bootstrap5.client.ui.CarouselSlide;
import io.instanto.bootstrap5.client.ui.Collapse;
import io.instanto.bootstrap5.client.ui.FieldSet;
import io.instanto.bootstrap5.client.ui.Form;
import io.instanto.bootstrap5.client.ui.FormControlStatic;
import io.instanto.bootstrap5.client.ui.Legend;
import io.instanto.bootstrap5.client.ui.MediaBody;
import io.instanto.bootstrap5.client.ui.MediaList;
import io.instanto.bootstrap5.client.ui.Navbar;
import io.instanto.bootstrap5.client.ui.NavbarBrand;
import io.instanto.bootstrap5.client.ui.NavbarLink;
import io.instanto.bootstrap5.client.ui.NavPills;
import io.instanto.bootstrap5.client.ui.PanelCollapse;
import io.instanto.bootstrap5.client.ui.PanelGroup;
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

    /** Where this page deploys the TeaVM module's assets. One setting covers everything. */
    private static final String ASSETS = "teavm5/";

    private static final Paragraph THEME_STATUS = new Paragraph();

    public static void main(final String[] args) {
        // One setting; the widgets ask for whatever else they need themselves.
        Bootstrap5Resources.setAssetBase(ASSETS);
        Themes.register(StandardThemes.all(Bootstrap5Resources.cssBase()));
        Themes.register(BootswatchThemes.all(Bootstrap5Resources.cssBase()));
        Themes.restore(StandardThemes.bootstrap(Bootstrap5Resources.cssBase()));
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
        column.add(panel("Grid", grid(),
                "Row row = new Row();\n"
                + "Column column = new Column(12);\n"
                + "column.setMediumSpan(6);   // col-12 col-md-6"));
        column.add(panel("Typography", typography(),
                "new Heading(3, \"...\");\nnew Lead(\"...\");\n"
                + "new BlockQuote();\nnew Description();   // dl/dt/dd"));
        column.add(panel("Code", code(),
                "new Code(\"inline\");\nnew Pre(\"a block\");"));
        column.add(panel("Tables", tables(),
                "// No Table widget: Bootstrap 5 tables are class-driven,\n"
                + "// so plain markup with .table is the whole API."));
        column.add(panel("Cards", cards(),
                "Card card = new Card();\n"
                + "card.addStyleName(\"text-bg-primary\");"));
        column.add(panel("Wells and jumbotron", wells(),
                "new Well();\n"
                + "// Bootstrap 5 dropped both classes; the widgets render the\n"
                + "// documented utility equivalents instead."));
        column.add(panel("Progress variants", progressVariants(),
                "Progress progress = new Progress();\n"
                + "progress.setType(ProgressType.STRIPED);\n"
                + "progress.setActive(true);   // adds progress-bar-animated\n"
                + "progress.add(new ProgressBar(40));"));
        column.add(panel("Button groups", buttonGroups(),
                "ButtonGroup group = new ButtonGroup();\n"
                + "group.setSize(ButtonGroupSize.LARGE);\n"
                + "new ButtonToolBar();\nnew VerticalButtonGroup();"));
        column.add(panel("Icon variants", iconVariants(),
                "icon.setRotate(IconRotate.ROTATE_90);\n"
                + "icon.setFlip(IconFlip.HORIZONTAL);\n"
                + "IconStack stack = new IconStack();"));
        column.add(panel("Pager and linked groups", pagerAndLinked(),
                "Pager pager = new Pager();\npager.setPreviousText(\"Older\");\n"
                + "LinkedGroup group = new LinkedGroup();"));
        column.add(panel("Collapse", collapse(),
                "Collapse collapse = new Collapse();\ncollapse.toggle();"));
        column.add(panel("Accordion", accordion(),
                "PanelGroup accordion = new PanelGroup();\n"
                + "PanelCollapse body = new PanelCollapse();\n"
                + "// Bootstrap 5 renames the attributes: data-bs-toggle,\n"
                + "// data-bs-target and data-bs-parent"));
        column.add(panel("Navs", navs(),
                "NavPills pills = new NavPills();\n"
                + "pills.addStyleName(\"flex-column\");   // replaces nav-stacked"));
        column.add(panel("Navbar", navbar(),
                "Navbar navbar = new Navbar();\n"
                + "navbar.getContainer().add(new NavbarBrand(\"Brand\", \"#\"));\n"
                + "navbar.getNav().add(new NavbarLink(\"Link\", \"#\"));"));
        column.add(panel("Carousel", carousel(),
                "Carousel carousel = new Carousel();\n"
                + "carousel.addSlide(new CarouselSlide(content));\n"
                + "carousel.add(new CarouselControl(id, true));"));
        column.add(panel("Media and images", media(),
                "MediaList list = new MediaList();\nlist.add(new MediaBody());\n"
                + "// Bootstrap 5 dropped .media for flex utilities"));
        column.add(panel("Form layout", formLayout(),
                "Form form = new Form();\nFieldSet fieldSet = new FieldSet();\n"
                + "fieldSet.add(new Legend(\"...\"));\n"
                + "new FormControlStatic(\"read-only text\");"));
        column.add(panel("Rich text", richText(),
                "RichTextEditor editor = new RichTextEditor(Toolbar.FULL);\n"
                + "editor.setHTML(\"<p>Hello</p>\");\n"
                + "String html = editor.getHTML();\n\n"
                + "// Quill, loaded by URL rather than inlined. Use Markdown above\n"
                + "// when the stored format is Markdown rather than HTML."));
        column.add(panel("Slider", slider(),
                "Slider slider = new Slider(0, 100);\n"
                + "slider.setRange(true);      // two handles\n"
                + "slider.setTooltips(true);\n"
                + "slider.setValues(20, 80);\n\n"
                + "// noUiSlider, loaded by URL rather than inlined"));
        column.add(panel("Markdown", markdown(),
                "MarkdownEditor editor = new MarkdownEditor(source);\n"
                + "String markdown = editor.getValue();   // as typed\n"
                + "String html = editor.getHTML();        // rendered\n\n"
                + "// The first extra to reach TeaVM. marked and DOMPurify load by\n"
                + "// URL rather than being inlined by a ClientBundle, and the two\n"
                + "// JSNI seams are re-implemented against @JSBody."));
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

    private static Widget grid() {
        final PanelBody body = new PanelBody();
        final Row row = new Row();
        row.addStyleName("g-2 mb-2");
        for (int i = 0; i < 3; i++) {
            final Column cell = new Column(12);
            cell.setMediumSpan(4);
            cell.add(new HTML("<div class='p-2 text-center border rounded bg-body-tertiary'>col-12 col-md-4</div>"));
            row.add(cell);
        }

        final Row nested = new Row();
        nested.addStyleName("g-2");
        final Column outer = new Column(12);
        outer.setMediumSpan(8);
        final Row inner = new Row();
        inner.addStyleName("g-2");
        for (int i = 0; i < 2; i++) {
            final Column cell = new Column(6);
            cell.add(new HTML("<div class='p-2 text-center border rounded bg-body-tertiary'>nested col-6</div>"));
            inner.add(cell);
        }
        outer.add(new HTML("<div class='p-2 border rounded bg-body-tertiary mb-2'>col-12 col-md-8</div>"));
        outer.add(inner);
        nested.add(outer);

        body.add(row);
        body.add(nested);
        return body;
    }

    private static Widget typography() {
        final PanelBody body = new PanelBody();
        body.add(new Heading(3, "A level three heading"));
        body.add(new Lead("A lead paragraph, which Bootstrap 5 still styles with .lead."));
        body.add(new Paragraph("An ordinary paragraph for comparison."));
        body.add(new HTML("<p><mark>Marked</mark>, <del>deleted</del>, <ins>inserted</ins>, "
                + "<small>small</small> and <strong>strong</strong> inline elements.</p>"));

        final BlockQuote quote = new BlockQuote();
        quote.add(new Paragraph("A quotation, contained in a blockquote element."));
        quote.add(new HTML("<footer class='blockquote-footer'>Someone famous in "
                + "<cite title='Source Title'>Source Title</cite></footer>"));
        body.add(quote);

        final Description description = new Description();
        description.add(new DescriptionTitle("Widgets"));
        description.add(new DescriptionData("Compiled from the same sources as the GWT build."));
        description.add(new DescriptionTitle("DOM and events"));
        description.add(new DescriptionData("TeaVM JSO."));
        body.add(description);
        return body;
    }

    private static Widget code() {
        final PanelBody body = new PanelBody();
        body.add(new HTML("<p>An inline reference to "
                + "<code>Bootstrap5.mount(container)</code> reads like this.</p>"));
        final Pre block = new Pre("Container container = new Container();\n"
                + "container.add(new Button(\"Primary\", ButtonType.PRIMARY));\n"
                + "Bootstrap5.mount(container);");
        block.addStyleName("mb-0");
        body.add(block);
        return body;
    }

    private static Widget tables() {
        final PanelBody body = new PanelBody();
        body.add(new HTML("<table class='table table-striped align-middle'>"
                + "<caption>The widget modules in this repository</caption>"
                + "<thead><tr><th scope='col'>#</th><th scope='col'>Module</th>"
                + "<th scope='col'>Backing CSS</th></tr></thead><tbody>"
                + "<tr><th scope='row'>1</th><td>gwt-bootstrap3</td><td>Bootstrap 3.4.1</td></tr>"
                + "<tr><th scope='row'>2</th><td>gwt-bootstrap5</td><td>Bootstrap 5.3.8</td></tr>"
                + "<tr class='table-success'><th scope='row'>3</th><td>teavm-bootstrap5</td>"
                + "<td>Bootstrap 5.3.8</td></tr>"
                + "</tbody></table>"));
        body.add(new HTML("<div class='table-responsive'><table class='table table-bordered mb-0'>"
                + "<thead><tr><th>Scrolls</th><th>horizontally</th><th>when</th><th>narrow</th>"
                + "<th>enough</th><th>to</th><th>need</th><th>it</th></tr></thead>"
                + "<tbody><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td>"
                + "<td>7</td><td>8</td></tr></tbody></table></div>"));
        return body;
    }

    private static Widget cards() {
        final PanelBody body = new PanelBody();
        final Row row = new Row();
        row.addStyleName("g-3");

        final Column plain = new Column(12);
        plain.setMediumSpan(6);
        final Card card = new Card();
        card.add(new HTML("<div class='card-body'><h5 class='card-title'>Card title</h5>"
                + "<p class='card-text mb-0'>A card with a body, built from the Card widget.</p></div>"));
        plain.add(card);

        final Column tinted = new Column(12);
        tinted.setMediumSpan(6);
        final Card coloured = new Card();
        coloured.addStyleName("text-bg-primary");
        coloured.add(new HTML("<div class='card-header'>Header</div>"
                + "<div class='card-body'><h5 class='card-title'>Coloured card</h5>"
                + "<p class='card-text mb-0'>Bootstrap 5 tints cards with text-bg-*.</p></div>"
                + "<div class='card-footer'>Footer</div>"));
        tinted.add(coloured);

        row.add(plain);
        row.add(tinted);
        body.add(row);
        return body;
    }

    private static Widget wells() {
        final PanelBody body = new PanelBody();
        final Well well = new Well();
        well.add(new HTML("<span>A Well. Bootstrap 5 removed the class, so the widget renders "
                + "the documented card-like utilities instead.</span>"));

        final Jumbotron jumbotron = new Jumbotron();
        jumbotron.add(new Heading(2, "Jumbotron"));
        jumbotron.add(new Lead("Also removed in Bootstrap 5, and also mapped onto utilities."));
        final Button learn = new Button("A call to action", ButtonType.PRIMARY);
        jumbotron.add(learn);

        body.add(well);
        body.add(jumbotron);
        return body;
    }

    private static Widget progressVariants() {
        final PanelBody body = new PanelBody();
        body.add(bar(ProgressType.DEFAULT, 60, ProgressBarType.DEFAULT, false, "plain"));
        body.add(bar(ProgressType.STRIPED, 40, ProgressBarType.SUCCESS, false, "striped"));
        body.add(bar(ProgressType.STRIPED, 75, ProgressBarType.INFO, true, "striped and active"));

        final Progress stacked = new Progress();
        final ProgressBar first = new ProgressBar(35);
        first.setType(ProgressBarType.SUCCESS);
        final ProgressBar second = new ProgressBar(25);
        second.setType(ProgressBarType.WARNING);
        stacked.add(first);
        stacked.add(second);
        body.add(stacked);
        return body;
    }

    private static Progress bar(final ProgressType type, final int percent,
            final ProgressBarType barType, final boolean active, final String label) {
        final Progress progress = new Progress();
        progress.setType(type);
        progress.setActive(active);
        final ProgressBar bar = new ProgressBar(percent);
        bar.setType(barType);
        bar.setText(label);
        progress.add(bar);
        return progress;
    }

    private static Widget buttonGroups() {
        final PanelBody body = new PanelBody();

        final ButtonGroup large = new ButtonGroup();
        large.setSize(ButtonGroupSize.LARGE);
        large.add(new Button("Left"));
        large.add(new Button("Middle"));
        large.add(new Button("Right"));

        final ButtonToolBar toolbar = new ButtonToolBar();
        toolbar.addStyleName("gap-2");
        final ButtonGroup one = new ButtonGroup();
        one.add(new Button("1"));
        one.add(new Button("2"));
        final ButtonGroup two = new ButtonGroup();
        two.add(new Button("A"));
        two.add(new Button("B"));
        toolbar.add(one);
        toolbar.add(two);

        final VerticalButtonGroup vertical = new VerticalButtonGroup();
        vertical.add(new Button("Top"));
        vertical.add(new Button("Middle"));
        vertical.add(new Button("Bottom"));

        body.add(large);
        body.add(toolbar);
        body.add(vertical);
        return body;
    }

    private static Widget iconVariants() {
        final PanelBody body = new PanelBody();
        body.addStyleName("d-flex flex-wrap gap-3 align-items-center");

        for (final IconRotate rotate : new IconRotate[] {IconRotate.NONE, IconRotate.ROTATE_90,
                IconRotate.ROTATE_180, IconRotate.ROTATE_270}) {
            final Icon icon = new Icon(IconType.SIGNPOST_2);
            icon.setSize(IconSize.X_LARGE);
            icon.setRotate(rotate);
            body.add(icon);
        }
        for (final IconFlip flip : new IconFlip[] {IconFlip.HORIZONTAL, IconFlip.VERTICAL}) {
            final Icon icon = new Icon(IconType.SIGNPOST_2);
            icon.setSize(IconSize.X_LARGE);
            icon.setFlip(flip);
            body.add(icon);
        }

        final IconStack stack = new IconStack();
        stack.add(new Icon(IconType.CIRCLE_FILL), true);
        final Icon glyph = new Icon(IconType.TERMINAL);
        glyph.setInverse(true);
        stack.add(glyph, false);
        body.add(stack);
        return body;
    }

    private static Widget pagerAndLinked() {
        final PanelBody body = new PanelBody();

        final Pager pager = new Pager();
        pager.setPreviousText("Older");
        pager.setNextText("Newer");

        final LinkedGroup group = new LinkedGroup();
        final LinkedGroupItem active = new LinkedGroupItem("An active linked item", "#");
        active.setActive(true);
        group.add(active);
        group.add(new LinkedGroupItem("A second linked item", "#"));
        group.add(new LinkedGroupItem("A third linked item", "#"));

        body.add(pager);
        body.add(group);
        return body;
    }

    private static Widget collapse() {
        final PanelBody body = new PanelBody();
        final Collapse collapse = new Collapse();
        collapse.getElement().setId("teavmCollapseExample");
        final Well well = new Well();
        well.add(new HTML("<span>Collapsed content, shown and hidden by Bootstrap's own "
                + "JavaScript through TeaVM.</span>"));
        collapse.add(well);

        final Button toggle = new Button("Toggle collapse", ButtonType.PRIMARY);
        toggle.addStyleName("mb-2");
        toggle.addClickHandler(event -> collapse.toggle());

        body.add(toggle);
        body.add(collapse);
        return body;
    }

    private static Widget accordion() {
        final PanelGroup accordion = new PanelGroup();
        accordion.getElement().setId("teavmAccordion");
        accordion.add(accordionItem("teavmCollapseOne", "First section", true));
        accordion.add(accordionItem("teavmCollapseTwo", "Second section", false));
        accordion.add(accordionItem("teavmCollapseThree", "Third section", false));
        return accordion;
    }

    private static Panel accordionItem(final String id, final String title, final boolean open) {
        final Panel panel = new Panel();
        final PanelHeader header = new PanelHeader();
        final Anchor anchor = new Anchor(title, "#" + id);
        anchor.setDataToggle("collapse");
        anchor.getElement().setAttribute("data-bs-target", "#" + id);
        header.add(anchor);
        panel.add(header);

        final PanelCollapse collapse = new PanelCollapse();
        collapse.getElement().setId(id);
        collapse.setIn(open);
        // Bootstrap 3 read data-parent from the toggle; Bootstrap 5 reads
        // data-bs-parent from the collapsing element itself.
        collapse.getElement().setAttribute("data-bs-parent", "#teavmAccordion");
        final PanelBody body = new PanelBody();
        body.add(new Paragraph("The content of " + title + "."));
        collapse.add(body);
        panel.add(collapse);
        return panel;
    }

    private static Widget navs() {
        final PanelBody body = new PanelBody();

        final NavPills pills = new NavPills();
        pills.addLink("Home", "#").addStyleName("active");
        pills.addLink("Profile", "#");
        pills.addLink("Messages", "#");

        final NavPills stacked = new NavPills();
        stacked.addStyleName("flex-column");
        stacked.addLink("Stacked one", "#").addStyleName("active");
        stacked.addLink("Stacked two", "#");

        final Column narrow = new Column(12);
        narrow.setMediumSpan(4);
        narrow.add(stacked);
        final Row wrapper = new Row();
        wrapper.add(narrow);

        body.add(pills);
        body.add(wrapper);
        return body;
    }

    private static Widget navbar() {
        final Navbar navbar = new Navbar();
        navbar.getContainer().add(new NavbarBrand("Brand", "#"));
        navbar.getNav().add(new NavbarLink("Link", "#"));
        navbar.getNav().add(new NavbarLink("Another", "#"));
        return navbar;
    }

    private static Widget carousel() {
        final String id = "teavmCarousel";
        final Carousel carousel = new Carousel();
        carousel.getElement().setId(id);

        final CarouselIndicators indicators = new CarouselIndicators();
        final CarouselIndicator first = new CarouselIndicator(id, 0);
        first.setActive(true);
        indicators.addIndicator(first);
        indicators.addIndicator(new CarouselIndicator(id, 1));
        carousel.insert(indicators, 0);

        final CarouselSlide one = new CarouselSlide(new HTML("<div class='d-flex align-items-center"
                + " justify-content-center text-bg-primary rounded' style='height: 11rem;'>"
                + "First slide</div>"));
        one.setActive(true);
        carousel.addSlide(one);
        carousel.addSlide(new CarouselSlide(new HTML("<div class='d-flex align-items-center"
                + " justify-content-center text-bg-success rounded' style='height: 11rem;'>"
                + "Second slide</div>")));

        carousel.add(new CarouselControl(id, true));
        carousel.add(new CarouselControl(id, false));
        return carousel;
    }

    private static Widget media() {
        final PanelBody body = new PanelBody();
        final MediaList list = new MediaList();
        final MediaBody mediaBody = new MediaBody();
        mediaBody.add(new Heading(5, "Media heading"));
        mediaBody.add(new Paragraph("Bootstrap 5 dropped the media object for flex utilities, "
                + "and the widget renders those instead."));
        list.add(new HTML("<div class='flex-shrink-0 me-3 d-flex align-items-center"
                + " justify-content-center bg-body-tertiary border rounded'"
                + " style='width:64px;height:64px;'>64</div>"));
        list.add(mediaBody);
        body.add(list);
        body.add(new HTML("<img class='img-fluid rounded' alt='A responsive placeholder'"
                + " src=\"data:image/svg+xml;charset=UTF-8,%3Csvg xmlns='http://www.w3.org/2000/svg'"
                + " width='640' height='120'%3E%3Crect fill='%230b6b5e' width='640' height='120'/%3E"
                + "%3Ctext fill='%23fff' font-family='sans-serif' font-size='20' x='50%25' y='50%25'"
                + " text-anchor='middle' dominant-baseline='middle'%3Eimg-fluid%3C/text%3E%3C/svg%3E\">"));
        return body;
    }

    private static Widget formLayout() {
        final Form form = new Form();
        final FieldSet fieldSet = new FieldSet();
        fieldSet.add(new Legend("A fieldset with a legend"));

        final FormGroup group = new FormGroup();
        group.add(new FormLabel("Editable"));
        final TextBox editable = new TextBox();
        editable.setPlaceholder("An ordinary control");
        group.add(editable);

        final FormGroup readOnly = new FormGroup();
        readOnly.add(new FormLabel("Read-only"));
        readOnly.add(new FormControlStatic("Rendered as static text, not an input"));

        fieldSet.add(group);
        fieldSet.add(readOnly);
        form.add(fieldSet);
        return form;
    }

    private static Widget markdown() {
        final PanelBody body = new PanelBody();
        body.add(new HTML("<p class='text-body-secondary'>The Markdown extra, compiled by"
                + " TeaVM from the same sources the GWT build uses. The pencil switches"
                + " between reading and editing.</p>"));

        final MarkdownEditor editor = new MarkdownEditor("## Release notes\n\n"
                + "The **first** extra to reach TeaVM.\n\n"
                + "- Rich text keeps HTML\n"
                + "- This keeps Markdown, exactly as typed\n\n"
                + "- [x] renders task lists\n"
                + "- [ ] and tables\n\n"
                + "| Backend | Markdown |\n| --- | --- |\n| GWT | yes |\n| TeaVM | yes |\n");
        editor.setVisibleLines(10);
        body.add(editor);
        return body;
    }

    private static Widget slider() {
        final PanelBody body = new PanelBody();
        body.add(new HTML("<p class='text-body-secondary mb-4'>noUiSlider through the"
                + " same widget the GWT build uses. Use Range above when a native"
                + " control will do; this is for two handles, pips and tooltips.</p>"));

        final Paragraph single = new Paragraph("Single: 40");
        final Slider one = new Slider(0, 100);
        one.setTooltips(true);
        one.setValue(40);
        one.addValueChangeHandler(new ValueChangeHandler<Double>() {
            @Override
            public void onValueChange(final ValueChangeEvent<Double> event) {
                single.setText("Single: " + (long) event.getValue().doubleValue());
            }
        });

        final Slider two = new Slider(0, 1000);
        two.setRange(true);
        two.setTooltips(true);
        two.setPips(true);
        two.setValues(120, 880);

        body.add(single);
        body.add(one);
        body.add(new HTML("<p class='mt-4 mb-2'>Two handles, with pips</p>"));
        body.add(two);
        return body;
    }

    private static Widget richText() {
        final PanelBody body = new PanelBody();
        body.add(new HTML("<p class='text-body-secondary'>Quill through the same widget"
                + " the GWT build uses. This stores HTML; the Markdown editor below"
                + " stores Markdown.</p>"));

        final RichTextEditor editor = new RichTextEditor(RichTextEditor.Toolbar.FULL);
        editor.setPlaceholder("Write something...");
        editor.setHTML("<h3>Rich text on TeaVM</h3>"
                + "<p>The toolbar, the <strong>formatting</strong> and the"
                + " <em>document model</em> are all Quill's.</p>"
                + "<ul><li>Compiled from the shared widget source</li>"
                + "<li>Quill fetched by URL, not inlined</li></ul>");
        body.add(editor);
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
