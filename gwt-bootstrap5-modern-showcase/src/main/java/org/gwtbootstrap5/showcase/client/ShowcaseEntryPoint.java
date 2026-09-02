package org.gwtbootstrap5.showcase.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.Arrays;
import org.gwtbootstrap5.client.ui.Abbreviation;
import org.gwtbootstrap5.client.ui.Alert;
import org.gwtbootstrap5.client.ui.Anchor;
import org.gwtbootstrap5.client.ui.AnchorButton;
import org.gwtbootstrap5.client.ui.AnchorListItem;
import org.gwtbootstrap5.client.ui.Badge;
import org.gwtbootstrap5.client.ui.BlockQuote;
import org.gwtbootstrap5.client.ui.Breadcrumbs;
import org.gwtbootstrap5.client.ui.Button;
import org.gwtbootstrap5.client.ui.ButtonGroup;
import org.gwtbootstrap5.client.ui.ButtonToolBar;
import org.gwtbootstrap5.client.ui.Caption;
import org.gwtbootstrap5.client.ui.Carousel;
import org.gwtbootstrap5.client.ui.CarouselCaption;
import org.gwtbootstrap5.client.ui.CarouselControl;
import org.gwtbootstrap5.client.ui.CarouselIndicator;
import org.gwtbootstrap5.client.ui.CarouselIndicators;
import org.gwtbootstrap5.client.ui.CarouselSlide;
import org.gwtbootstrap5.client.ui.CheckBox;
import org.gwtbootstrap5.client.ui.CheckBoxButton;
import org.gwtbootstrap5.client.ui.Code;
import org.gwtbootstrap5.client.ui.Collapse;
import org.gwtbootstrap5.client.ui.Column;
import org.gwtbootstrap5.client.ui.Container;
import org.gwtbootstrap5.client.ui.Description;
import org.gwtbootstrap5.client.ui.DescriptionData;
import org.gwtbootstrap5.client.ui.DescriptionTitle;
import org.gwtbootstrap5.client.ui.Divider;
import org.gwtbootstrap5.client.ui.DoubleBox;
import org.gwtbootstrap5.client.ui.DropDown;
import org.gwtbootstrap5.client.ui.DropDownHeader;
import org.gwtbootstrap5.client.ui.DropDownItem;
import org.gwtbootstrap5.client.ui.FieldSet;
import org.gwtbootstrap5.client.ui.Form;
import org.gwtbootstrap5.client.ui.FormControlStatic;
import org.gwtbootstrap5.client.ui.FormGroup;
import org.gwtbootstrap5.client.ui.FormLabel;
import org.gwtbootstrap5.client.ui.HelpBlock;
import org.gwtbootstrap5.client.ui.Heading;
import org.gwtbootstrap5.client.ui.Icon;
import org.gwtbootstrap5.client.ui.Image;
import org.gwtbootstrap5.client.ui.ImageAnchor;
import org.gwtbootstrap5.client.ui.InlineCheckBox;
import org.gwtbootstrap5.client.ui.InlineHelpBlock;
import org.gwtbootstrap5.client.ui.InlineRadio;
import org.gwtbootstrap5.client.ui.Input;
import org.gwtbootstrap5.client.ui.InputGroup;
import org.gwtbootstrap5.client.ui.InputGroupAddon;
import org.gwtbootstrap5.client.ui.InputGroupButton;
import org.gwtbootstrap5.client.ui.IntegerBox;
import org.gwtbootstrap5.client.ui.Jumbotron;
import org.gwtbootstrap5.client.ui.Label;
import org.gwtbootstrap5.client.ui.Lead;
import org.gwtbootstrap5.client.ui.Legend;
import org.gwtbootstrap5.client.ui.LinkedGroup;
import org.gwtbootstrap5.client.ui.LinkedGroupItem;
import org.gwtbootstrap5.client.ui.LinkedGroupItemText;
import org.gwtbootstrap5.client.ui.ListBox;
import org.gwtbootstrap5.client.ui.ListDropDown;
import org.gwtbootstrap5.client.ui.ListGroup;
import org.gwtbootstrap5.client.ui.ListGroupItem;
import org.gwtbootstrap5.client.ui.ListItem;
import org.gwtbootstrap5.client.ui.LongBox;
import org.gwtbootstrap5.client.ui.MediaBody;
import org.gwtbootstrap5.client.ui.MediaList;
import org.gwtbootstrap5.client.ui.Modal;
import org.gwtbootstrap5.client.ui.ModalFooter;
import org.gwtbootstrap5.client.ui.NavPills;
import org.gwtbootstrap5.client.ui.NavTabs;
import org.gwtbootstrap5.client.ui.Navbar;
import org.gwtbootstrap5.client.ui.NavbarBrand;
import org.gwtbootstrap5.client.ui.NavbarButton;
import org.gwtbootstrap5.client.ui.NavbarCollapse;
import org.gwtbootstrap5.client.ui.NavbarCollapseButton;
import org.gwtbootstrap5.client.ui.NavbarForm;
import org.gwtbootstrap5.client.ui.NavbarHeader;
import org.gwtbootstrap5.client.ui.NavbarLink;
import org.gwtbootstrap5.client.ui.PageHeader;
import org.gwtbootstrap5.client.ui.Pager;
import org.gwtbootstrap5.client.ui.Pagination;
import org.gwtbootstrap5.client.ui.Panel;
import org.gwtbootstrap5.client.ui.PanelBody;
import org.gwtbootstrap5.client.ui.PanelCollapse;
import org.gwtbootstrap5.client.ui.PanelFooter;
import org.gwtbootstrap5.client.ui.PanelGroup;
import org.gwtbootstrap5.client.ui.PanelHeader;
import org.gwtbootstrap5.client.ui.Paragraph;
import org.gwtbootstrap5.client.ui.Popover;
import org.gwtbootstrap5.client.ui.Pre;
import org.gwtbootstrap5.client.ui.Progress;
import org.gwtbootstrap5.client.ui.ProgressBar;
import org.gwtbootstrap5.client.ui.Radio;
import org.gwtbootstrap5.client.ui.RadioButton;
import org.gwtbootstrap5.client.ui.Row;
import org.gwtbootstrap5.client.ui.SimpleCheckBox;
import org.gwtbootstrap5.client.ui.SimpleRadioButton;
import org.gwtbootstrap5.client.ui.StringRadioGroup;
import org.gwtbootstrap5.client.ui.SubmitButton;
import org.gwtbootstrap5.client.ui.SuggestBox;
import org.gwtbootstrap5.client.ui.TabContent;
import org.gwtbootstrap5.client.ui.TabListItem;
import org.gwtbootstrap5.client.ui.TabPane;
import org.gwtbootstrap5.client.ui.TabPanel;
import org.gwtbootstrap5.client.ui.TextArea;
import org.gwtbootstrap5.client.ui.TextBox;
import org.gwtbootstrap5.client.ui.ThumbnailLink;
import org.gwtbootstrap5.client.ui.ThumbnailPanel;
import org.gwtbootstrap5.client.ui.Tooltip;
import org.gwtbootstrap5.client.ui.TooltipHelpBlock;
import org.gwtbootstrap5.client.ui.ValueListBox;
import org.gwtbootstrap5.client.ui.Variant;
import org.gwtbootstrap5.client.ui.VerticalButtonGroup;
import org.gwtbootstrap5.client.ui.Well;

public class ShowcaseEntryPoint implements EntryPoint {

    private static final String IMG_WIDE = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='640' height='240'%3E%3Crect width='100%25' height='100%25' fill='rgb(13,110,253)'/%3E%3Ctext x='50%25' y='50%25' fill='white' text-anchor='middle' dominant-baseline='middle' font-family='sans-serif' font-size='32'%3EBootstrap 5%3C/text%3E%3C/svg%3E";
    private static final String IMG_THUMB = "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='320' height='180'%3E%3Crect width='100%25' height='100%25' fill='rgb(25,135,84)'/%3E%3Ctext x='50%25' y='50%25' fill='white' text-anchor='middle' dominant-baseline='middle' font-family='sans-serif' font-size='24'%3EThumbnail%3C/text%3E%3C/svg%3E";
    private static final String[] CSS_SECTIONS = {"buttons", "code", "forms", "gridSystem", "images", "responsiveUtilities", "tables", "typography"};
    private static final String[] CSS_LABELS = {"Buttons", "Code", "Forms", "Grid System", "Images", "Responsive Utilities", "Tables", "Typography"};
    private static final String[] COMPONENT_SECTIONS = {"alerts", "badges", "breadcrumbs", "buttonDropdowns", "buttonGroups", "dropdowns", "icons", "inputGroups", "jumbotron", "labels", "listGroup", "mediaObjects", "navbar", "navs", "pageHeader", "pagination", "panels", "progressBars", "suggestBox", "thumbnails", "wells"};
    private static final String[] COMPONENT_LABELS = {"Alerts", "Badges", "Breadcrumbs", "Button Dropdowns", "Button Groups", "Dropdowns", "Icons", "Input Groups", "Jumbotron", "Labels", "List Group", "Media Objects", "Navbar", "Navs", "Page Header", "Pagination", "Panels", "Progress Bars", "SuggestBox", "Thumbnails", "Wells"};
    private static final String[] JS_SECTIONS = {"carousel", "collapse", "modals", "popover", "tabs", "tooltips"};
    private static final String[] JS_LABELS = {"Carousel", "Collapse", "Modals", "Popover", "Tabs", "Tooltips"};
    private static final String[] EXTRA_SECTIONS = {"cards", "unsupportedExtras"};
    private static final String[] EXTRA_LABELS = {"Cards", "Unsupported Extras"};

    @Override
    public void onModuleLoad() {
        RootPanel root = RootPanel.get("showcase");
        if (root == null) {
            root = RootPanel.get();
        }

        root.add(createNavbar());

        Container container = new Container();
        container.addStyleName("gbm-showcase");
        container.add(createHome());
        container.add(createSetup());
        container.add(createCssSections());
        container.add(createComponentSections());
        container.add(createJavaScriptSections(root));
        container.add(createExtraSections());
        root.add(container);
    }

    private Navbar createNavbar() {
        Navbar navbar = new Navbar();
        navbar.addStyleName("fixed-top");
        NavbarCollapseButton navbarCollapseButton = new NavbarCollapseButton("navbar-collapse");
        NavbarCollapse navbarCollapse = new NavbarCollapse();
        navbarCollapse.getElement().setId("navbar-collapse");
        navbar.getContainer().add(new NavbarBrand("GWT Bootstrap Modern", "#home"));
        navbar.getContainer().add(navbarCollapseButton);
        navbarCollapse.add(navbar.getNav());
        navbar.getContainer().add(navbarCollapse);
        navbar.getNav().add(new NavbarLink("Setup", "#setup"));
        navbar.getNav().add(dropdown("CSS", CSS_LABELS, CSS_SECTIONS));
        navbar.getNav().add(dropdown("Components", COMPONENT_LABELS, COMPONENT_SECTIONS));
        navbar.getNav().add(dropdown("JavaScript", JS_LABELS, JS_SECTIONS));
        navbar.getNav().add(dropdown("Extras", EXTRA_LABELS, EXTRA_SECTIONS));
        navbar.getNav().add(dropdown("View Javadoc", new String[] {"GWT Bootstrap 5"}, new String[] {"apidocs/index.html"}));
        navbar.getNav().add(new NavbarLink("Bootstrap 3 Showcase", "../"));
        navbar.getNav().add(new NavbarLink("Fork on GitHub", "https://github.com/cstainton/gwtbootstrap-modern"));
        return navbar;
    }

    private ListDropDown dropdown(String text, String[] labels, String[] hrefs) {
        ListDropDown dropDown = new ListDropDown(text);
        for (int i = 0; i < labels.length; i++) {
            String href = hrefs[i].startsWith("http") || hrefs[i].contains("/") ? hrefs[i] : "#" + hrefs[i];
            dropDown.addItem(new DropDownItem(labels[i], href));
        }
        return dropDown;
    }

    private Widget createHome() {
        Row row = row();
        Column column = fullColumn();
        Jumbotron jumbotron = new Jumbotron();
        jumbotron.getElement().setId("home");
        jumbotron.add(new Heading(1, "GWT Bootstrap Modern Showcase"));
        jumbotron.add(new Paragraph("A GWT widget library migration fork backed by Bootstrap 5."));
        jumbotron.add(new Paragraph("This page mirrors the Bootstrap 3 showcase structure so migration differences are visible rather than hidden. The Java-facing composition and event model should remain familiar; the rendering uses Bootstrap 5 classes and data-bs attributes."));
        column.add(jumbotron);
        row.add(column);
        return row;
    }

    private Widget createSetup() {
        Row row = section("setup", "Setup", "Bootstrap 5-native module setup");
        Column column = fullColumn(row);
        column.add(panel("Maven", new HTML("<p>Use the Bootstrap 5 module when migrating code/templates to Bootstrap 5 idioms.</p>"), "<dependency>\n  <groupId>org.gwtbootstrap3</groupId>\n  <artifactId>gwt-bootstrap5-modern</artifactId>\n  <version>1.0-SNAPSHOT</version>\n</dependency>"));
        column.add(panel("GWT Module", new HTML("<p>Inherit the Bootstrap 5 GWT module.</p>"), "<inherits name=\"org.gwtbootstrap5.GwtBootstrap5\"/>"));
        return row;
    }

    private Widget createCssSections() {
        Row row = row();
        Column column = fullColumn(row);
        addPageHeader(column, "buttons", "Buttons", "styled, states, icons...");
        column.add(buttonsBasicPanel());
        column.add(buttonSizesPanel());
        column.add(buttonStatesPanel());
        column.add(buttonCompositionPanel());

        addPageHeader(column, "code", "Code", null);
        column.add(panel("Inline and block code", inline(new Code("Code"), new Pre("Preformatted text")), "new Code(\"Code\");\nnew Pre(\"Preformatted text\");"));

        addPageHeader(column, "forms", "Forms", null);
        column.add(formsPanel());
        column.add(formAdaptersPanel());

        addPageHeader(column, "gridSystem", "Grid System", null);
        column.add(gridPanel());

        addPageHeader(column, "images", "Images", null);
        column.add(panel("Responsive images", inline(new Image(IMG_WIDE), new ImageAnchor("#", IMG_THUMB)), "new Image(url);\nnew ImageAnchor(\"#\", url);"));

        addPageHeader(column, "responsiveUtilities", "Responsive Utilities", null);
        column.add(panel("Bootstrap 5 utilities", new HTML("<p class='d-none d-md-block'>Visible on medium screens and wider.</p><p class='d-md-none'>Visible below medium screens.</p>"), "Bootstrap 5 responsive display utilities: d-none, d-md-block, d-md-none."));

        addPageHeader(column, "tables", "Tables", null);
        column.add(panel("Basic", new HTML("<table class='table table-striped'><caption>Table caption</caption><thead><tr><th>#</th><th>Name</th></tr></thead><tbody><tr><td>1</td><td>Bootstrap 5</td></tr></tbody></table>"), "<table class=\"table table-striped\">...</table>"));

        addPageHeader(column, "typography", "Typography", null);
        column.add(typographyPanel());
        return row;
    }

    private Widget createComponentSections() {
        Row row = row();
        Column column = fullColumn(row);
        addPageHeader(column, "alerts", "Alerts", null);
        Alert alert = new Alert("Dismissible alert", Variant.INFO);
        alert.setDismissible(true);
        column.add(panel("Basic", alert, "Alert alert = new Alert(\"Dismissible alert\", Variant.INFO);\nalert.setDismissible(true);"));

        addPageHeader(column, "badges", "Badges", null);
        Badge badge = new Badge("Badge", Variant.SUCCESS);
        badge.setPill(true);
        column.add(panel("Basic", inline(badge, new Label("Label concept", Variant.SECONDARY)), "new Badge(\"Badge\", Variant.SUCCESS);\nnew Label(\"Label concept\", Variant.SECONDARY);"));

        addPageHeader(column, "breadcrumbs", "Breadcrumbs", null);
        column.add(panel("Basic", new Breadcrumbs(new AnchorListItem("Home", "#home"), new ListItem("Current")), "new Breadcrumbs(new AnchorListItem(\"Home\", \"#home\"), new ListItem(\"Current\"));"));

        addPageHeader(column, "buttonDropdowns", "Button Dropdowns", null);
        column.add(panel("Basic", dropdown("Action", new String[] {"First", "Second"}, new String[] {"#", "#"}), "ListDropDown dropDown = new ListDropDown(\"Action\");"));

        addPageHeader(column, "buttonGroups", "Button Groups", null);
        column.add(buttonGroupsPanel());

        addPageHeader(column, "dropdowns", "Dropdowns", null);
        column.add(dropdownsPanel());

        addPageHeader(column, "icons", "Icons", null);
        column.add(panel("Bootstrap Icons", inline(new Icon("camera"), new Icon("credit-card"), new Icon("check2-circle")), "new Icon(\"camera\");"));

        addPageHeader(column, "inputGroups", "Input Groups", null);
        column.add(inputGroupsPanel());

        addPageHeader(column, "jumbotron", "Jumbotron", null);
        column.add(panel("Bootstrap 5 mapping", new Jumbotron(), "Bootstrap 5 removed jumbotron; this wrapper maps to spacing/background/rounded utilities."));

        addPageHeader(column, "labels", "Labels", null);
        column.add(panel("Basic", inline(new Label("Default", Variant.SECONDARY), new Label("Primary", Variant.PRIMARY), new Label("Danger", Variant.DANGER)), "new Label(\"Primary\", Variant.PRIMARY);"));

        addPageHeader(column, "listGroup", "List Group", null);
        column.add(listGroupPanel());

        addPageHeader(column, "mediaObjects", "Media Objects", null);
        column.add(mediaPanel());

        addPageHeader(column, "navbar", "Navbar", null);
        column.add(navbarPanel());

        addPageHeader(column, "navs", "Navs", null);
        column.add(navsPanel());

        addPageHeader(column, "pageHeader", "Page Header", null);
        PageHeader pageHeader = new PageHeader();
        pageHeader.add(new Heading(2, "Page Header"));
        column.add(panel("Basic", pageHeader, "PageHeader pageHeader = new PageHeader();"));

        addPageHeader(column, "pagination", "Pagination", null);
        column.add(paginationPanel());

        addPageHeader(column, "panels", "Panels", null);
        column.add(panel("Bootstrap 5 mapping", samplePanel(), "Panel maps to Bootstrap 5 card markup."));

        addPageHeader(column, "progressBars", "Progress Bars", null);
        column.add(progressPanel());

        addPageHeader(column, "suggestBox", "SuggestBox", null);
        column.add(panel("Basic", new SuggestBox(), "new SuggestBox();"));

        addPageHeader(column, "thumbnails", "Thumbnails", null);
        column.add(thumbnailsPanel());

        addPageHeader(column, "wells", "Wells", null);
        Well well = new Well();
        well.add(new Paragraph("Bootstrap 5 has no well component; this maps to utility classes."));
        column.add(panel("Bootstrap 5 mapping", well, "new Well();"));
        return row;
    }

    private Widget createJavaScriptSections(RootPanel root) {
        Row row = row();
        Column column = fullColumn(row);
        addPageHeader(column, "carousel", "Carousel", null);
        column.add(panel("Basic", carousel(), "Carousel carousel = new Carousel();\ncarousel.addSlide(...);"));

        addPageHeader(column, "collapse", "Collapse", null);
        column.add(collapsePanel());

        addPageHeader(column, "modals", "Modals", null);
        column.add(modalPanel(root));

        addPageHeader(column, "popover", "Popover", null);
        Popover popover = new Popover(new Button("Popover", Variant.SECONDARY), "Popover", "Popover content");
        popover.init();
        column.add(panel("Basic", popover, "new Popover(widget, \"Popover\", \"Popover content\");"));

        addPageHeader(column, "tabs", "Tabs", null);
        column.add(tabsPanel());

        addPageHeader(column, "tooltips", "Tooltips", null);
        Tooltip tooltip = new Tooltip(new Button("Tooltip", Variant.SECONDARY), "Tooltip text");
        tooltip.init();
        column.add(panel("Basic", inline(tooltip, new TooltipHelpBlock("TooltipHelpBlock")), "new Tooltip(widget, \"Tooltip text\");"));
        return row;
    }

    private Widget createExtraSections() {
        Row row = row();
        Column column = fullColumn(row);
        addPageHeader(column, "cards", "Cards", null);
        column.add(panel("Bootstrap 5 native", sampleCard(), "Card is Bootstrap 5-native and replaces many Bootstrap 3 panel/card-extra use cases."));
        addPageHeader(column, "unsupportedExtras", "Unsupported Extras", null);
        column.add(panel("Not part of core Bootstrap 5", new HTML("<p>Bootstrap Select, Bootbox, DatePicker, DateTimePicker, FullCalendar, Gallery, Notify, Slider, Summernote, TagsInput, ToggleSwitch, Typeahead and Offline are extras with separate third-party dependencies. They are intentionally not represented as Bootstrap 5 core widgets yet.</p>"), "These require separate migration decisions, not silent Bootstrap 5 shims."));
        return row;
    }

    private Widget buttonsBasicPanel() {
        return panel("Basic", inline(new Button("Default", Variant.SECONDARY), new Button("Primary", Variant.PRIMARY), new Button("Success", Variant.SUCCESS), new Button("Info", Variant.INFO), new Button("Warning", Variant.WARNING), new Button("Danger", Variant.DANGER), linkButton("Link")), "new Button(\"Primary\", Variant.PRIMARY);" );
    }

    private Widget buttonSizesPanel() {
        Button small = new Button("Small", Variant.PRIMARY);
        small.setSmall(true);
        Button large = new Button("Large", Variant.PRIMARY);
        large.setLarge(true);
        return panel("Sizes", inline(small, large, note("Bootstrap 5 has small and large button sizes; extra-small is not a Bootstrap 5 button size.")), "button.setSmall(true);\nbutton.setLarge(true);" );
    }

    private Widget buttonStatesPanel() {
        Button enabled = new Button("Enabled", Variant.PRIMARY);
        Button disabled = new Button("Disabled", Variant.PRIMARY);
        disabled.setEnabled(false);
        Button toggle = new Button("Toggle button", Variant.SECONDARY);
        toggle.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                toggle.toggle();
            }
        });
        Button loading = new Button("Click me", Variant.PRIMARY);
        loading.setLoadingText("Loading...");
        loading.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                loading.setLoading(true);
                new Timer() {
                    @Override
                    public void run() {
                        loading.state().reset();
                    }
                }.schedule(1200);
            }
        });
        Button block = new Button("Block level button", Variant.PRIMARY);
        block.setBlock(true);
        return panel("States", inline(enabled, disabled, toggle, loading, block), "button.setActive(true);\nbutton.setLoadingText(\"Loading...\");\nbutton.state().loading();\nbutton.state().reset();\nbutton.toggle();\nbutton.setBlock(true);" );
    }

    private Widget buttonCompositionPanel() {
        Button button = new Button("With icon and badge", Variant.PRIMARY);
        button.setHTML("<i class='bi bi-star'></i> With icon <span class='badge text-bg-light'>1</span>");
        CheckBoxButton checkBoxButton = new CheckBoxButton("CheckBoxButton");
        return panel("Composition", inline(button, checkBoxButton), "button.setHTML(\"<i class='bi bi-star'></i> With icon ...\");\nnew CheckBoxButton(\"CheckBoxButton\");" );
    }

    private Widget formsPanel() {
        Form form = new Form();
        FormGroup nameGroup = new FormGroup();
        nameGroup.add(new FormLabel("TextBox"));
        nameGroup.add(new TextBox());
        FormGroup textAreaGroup = new FormGroup();
        textAreaGroup.add(new FormLabel("TextArea"));
        textAreaGroup.add(new TextArea());
        form.add(nameGroup);
        form.add(textAreaGroup);
        form.add(new CheckBox("CheckBox"));
        form.add(new Radio("form-radio", "Radio"));
        form.add(new InlineCheckBox("InlineCheckBox"));
        form.add(new InlineRadio("InlineRadio"));
        form.add(new SimpleCheckBox("SimpleCheckBox"));
        form.add(new SimpleRadioButton("SimpleRadioButton"));
        form.add(new HelpBlock("HelpBlock"));
        form.add(new InlineHelpBlock("InlineHelpBlock"));
        form.add(new SubmitButton("SubmitButton"));
        return panel("Basic", form, "new Form();\nnew TextBox();\nnew CheckBox(\"CheckBox\");\nnew Radio(\"group\", \"Radio\");");
    }

    private Widget formAdaptersPanel() {
        IntegerBox integerBox = new IntegerBox();
        integerBox.getElement().setAttribute("placeholder", "IntegerBox");
        DoubleBox doubleBox = new DoubleBox();
        doubleBox.getElement().setAttribute("placeholder", "DoubleBox");
        LongBox longBox = new LongBox();
        longBox.getElement().setAttribute("placeholder", "LongBox");
        ListBox listBox = new ListBox();
        listBox.addItem("ListBox");
        ValueListBox<String> valueListBox = new ValueListBox<String>();
        valueListBox.setAcceptableValues(Arrays.asList("ValueListBox A", "ValueListBox B"));
        StringRadioGroup radioGroup = new StringRadioGroup("string-radio-group");
        radioGroup.addRadio("a", "StringRadioGroup A");
        radioGroup.addRadio("b", "StringRadioGroup B");
        return panel("Value adapters", inline(integerBox, doubleBox, longBox, listBox, valueListBox, radioGroup), "new IntegerBox();\nnew DoubleBox();\nnew LongBox();\nnew ValueListBox<String>();\nnew StringRadioGroup(\"group\");");
    }

    private Widget gridPanel() {
        Row grid = row();
        Column left = new Column(12);
        left.setMediumSpan(6);
        left.add(new HTML("<div class='p-3 text-bg-primary rounded'>.col-md-6</div>"));
        Column right = new Column(12);
        right.setMediumSpan(6);
        right.add(new HTML("<div class='p-3 text-bg-secondary rounded'>.col-md-6</div>"));
        grid.add(left);
        grid.add(right);
        return panel("Basic", grid, "Column column = new Column(12);\ncolumn.setMediumSpan(6);");
    }

    private Widget typographyPanel() {
        Description description = new Description();
        description.add(new DescriptionTitle("DescriptionTitle"));
        description.add(new DescriptionData("DescriptionData"));
        FieldSet fieldSet = new FieldSet();
        fieldSet.add(new Legend("Legend"));
        fieldSet.add(new FormControlStatic("FormControlStatic"));
        BlockQuote quote = new BlockQuote();
        quote.add(new Paragraph("BlockQuote maps to Bootstrap 5 blockquote styling."));
        Abbreviation abbreviation = new Abbreviation("GWT");
        abbreviation.setTitle("Google Web Toolkit");
        return panel("Basic", inline(new Heading(2, "Heading"), new Lead("Lead text"), new Paragraph("Paragraph text"), abbreviation, quote, description, fieldSet, new Caption("Caption helper")), "new Heading(2, \"Heading\");\nnew Lead(\"Lead text\");\nnew BlockQuote();");
    }

    private Widget buttonGroupsPanel() {
        ButtonGroup group = new ButtonGroup();
        group.addButton(new Button("Left", Variant.SECONDARY));
        group.addButton(new Button("Middle", Variant.SECONDARY));
        group.addButton(new Button("Right", Variant.SECONDARY));
        VerticalButtonGroup vertical = new VerticalButtonGroup();
        vertical.addButton(new Button("Top", Variant.SECONDARY));
        vertical.addButton(new Button("Bottom", Variant.SECONDARY));
        ButtonToolBar toolbar = new ButtonToolBar();
        toolbar.addGroup(group);
        return panel("Basic", inline(toolbar, vertical), "ButtonGroup group = new ButtonGroup();\ngroup.addButton(new Button(\"Left\"));\nButtonToolBar toolbar = new ButtonToolBar();");
    }

    private Widget dropdownsPanel() {
        DropDown dropDown = new DropDown("Click to toggle dropdown");
        dropDown.addMenuWidget(new DropDownHeader("Header 1"));
        DropDownItem first = new DropDownItem("Action 1", "#");
        first.add(new Icon("camera"));
        dropDown.addItem(first);
        dropDown.addMenuWidget(new Divider());
        DropDownItem disabled = new DropDownItem("Action 2 (disabled)", "#");
        disabled.setDisabled(true);
        dropDown.addItem(disabled);
        DropDown endAligned = new DropDown("End aligned");
        endAligned.setMenuEndAligned(true);
        endAligned.addItem(new DropDownItem("Action", "#"));
        endAligned.addItem(new DropDownItem("Another action", "#"));
        DropDown dropUp = new DropDown("Dropup");
        dropUp.setDropUp(true);
        dropUp.addItem(new DropDownItem("Action", "#"));
        dropUp.addItem(new DropDownItem("Another action", "#"));
        return panel("Basic", inline(dropDown, endAligned, dropUp), "DropDown dropDown = new DropDown(\"Click to toggle dropdown\");\ndropDown.addItem(new DropDownItem(\"Action\", \"#\"));\ndropDown.setMenuEndAligned(true);\ndropDown.setDropUp(true);");
    }

    private Widget inputGroupsPanel() {
        InputGroup inputGroup = new InputGroup();
        inputGroup.add(new InputGroupAddon("@"));
        Input input = new Input("text");
        input.setPlaceholder("Username");
        inputGroup.add(input);
        inputGroup.add(new InputGroupButton(new Button("Go", Variant.PRIMARY)));
        return panel("Basic", inputGroup, "InputGroup inputGroup = new InputGroup();\ninputGroup.add(new InputGroupAddon(\"@\"));");
    }

    private Widget listGroupPanel() {
        ListGroup group = new ListGroup();
        group.add(new ListGroupItem("Plain list-group-item"));
        ListGroupItem active = new ListGroupItem("Active item");
        active.setActive(true);
        group.add(active);
        LinkedGroup linked = new LinkedGroup();
        LinkedGroupItem linkedItem = new LinkedGroupItem("LinkedGroupItem", "#");
        linkedItem.add(new LinkedGroupItemText("Linked item body text"));
        linked.add(linkedItem);
        return panel("Basic", inline(group, linked), "new ListGroup();\nnew LinkedGroup();");
    }

    private Widget mediaPanel() {
        MediaList mediaList = new MediaList();
        MediaBody mediaBody = new MediaBody();
        mediaBody.add(new Heading(4, "Media heading"));
        mediaBody.add(new Paragraph("Bootstrap 5 uses flex utilities for the old media object pattern."));
        mediaList.add(new Image(IMG_THUMB));
        mediaList.add(mediaBody);
        return panel("Basic", mediaList, "MediaList mediaList = new MediaList();\nmediaList.add(new MediaBody());");
    }

    private Widget navbarPanel() {
        Navbar navbar = new Navbar();
        navbar.getContainer().add(new NavbarBrand("Brand", "#"));
        navbar.getNav().add(new NavbarLink("Link", "#"));
        NavbarHeader header = new NavbarHeader();
        header.add(new NavbarButton("NavbarButton"));
        NavbarForm form = new NavbarForm();
        Input input = new Input("search");
        input.setPlaceholder("NavbarForm");
        form.add(input);
        navbar.getContainer().add(header);
        navbar.getContainer().add(form);
        return panel("Basic", navbar, "Navbar navbar = new Navbar();\nnavbar.getContainer().add(new NavbarBrand(...));");
    }

    private Widget navsPanel() {
        NavTabs tabs = new NavTabs();
        tabs.addLink("Home", "#").addStyleName("active");
        tabs.addLink("Profile", "#");
        NavPills pills = new NavPills();
        pills.addLink("Home", "#").addStyleName("active");
        pills.addLink("Profile", "#");
        return panel("Tabs and pills", inline(tabs, pills), "new NavTabs();\nnew NavPills();");
    }

    private Widget paginationPanel() {
        Pagination pagination = new Pagination();
        pagination.addPreviousLink();
        AnchorListItem active = new AnchorListItem("1", "#");
        active.setActive(true);
        pagination.add(active);
        pagination.add(new AnchorListItem("2", "#"));
        pagination.addNextLink();
        Pager pager = new Pager();
        pager.setAlignToSides(true);
        return panel("Basic", inline(pagination, pager), "Pagination pagination = new Pagination();\npagination.addPreviousLink();\npagination.add(new AnchorListItem(\"1\", \"#\"));\npagination.addNextLink();\npagination.rebuild(simplePager);");
    }

    private Widget progressPanel() {
        Progress progress = new Progress();
        ProgressBar bar = new ProgressBar();
        bar.setPercent(60);
        bar.setVariant(Variant.SUCCESS);
        progress.add(bar);
        return panel("Basic", progress, "ProgressBar bar = new ProgressBar();\nbar.setPercent(60);");
    }

    private Widget thumbnailsPanel() {
        ThumbnailPanel panel = new ThumbnailPanel();
        panel.addBody(new Image(IMG_THUMB));
        panel.addBody(new Paragraph("ThumbnailPanel maps to Card."));
        ThumbnailLink link = new ThumbnailLink("#");
        link.add(new Image(IMG_THUMB));
        return panel("Basic", inline(panel, link), "new ThumbnailPanel();\nnew ThumbnailLink(\"#\");");
    }

    private Widget collapsePanel() {
        Collapse collapse = new Collapse();
        collapse.getElement().setId("collapseExample");
        collapse.add(new Well());
        Button button = new Button("Toggle collapse", Variant.PRIMARY);
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                collapse.toggle();
            }
        });
        return panel("Basic", inline(button, collapse), "Collapse collapse = new Collapse();\ncollapse.toggle();");
    }

    private Widget modalPanel(RootPanel root) {
        Modal modal = new Modal();
        modal.getElement().setId("exampleModal");
        modal.setTitle("Cupcake ipsum");
        modal.addToBody(new Paragraph("Modal body..."));
        ModalFooter footer = new ModalFooter();
        footer.add(new Button("Do something", Variant.PRIMARY));
        Button close = new Button("Close", Variant.SECONDARY);
        close.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                modal.hide();
            }
        });
        footer.add(close);
        modal.addFooter(footer);
        root.add(modal);
        Button show = new Button("Click to show modal", Variant.PRIMARY);
        show.setLarge(true);
        show.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                modal.show();
            }
        });
        return panel("Basic", show, "Modal modal = new Modal();\nmodal.show();\nmodal.hide();");
    }

    private Widget tabsPanel() {
        TabPanel tabPanel = new TabPanel();
        TabListItem firstTab = new TabListItem("First", "firstPane");
        firstTab.setActive(true);
        tabPanel.getTabs().add(firstTab);
        tabPanel.getTabs().add(new TabListItem("Second", "secondPane"));
        TabPane firstPane = new TabPane();
        firstPane.getElement().setId("firstPane");
        firstPane.setActive(true);
        firstPane.add(new Paragraph("First tab content"));
        TabPane secondPane = new TabPane();
        secondPane.getElement().setId("secondPane");
        secondPane.add(new Paragraph("Second tab content"));
        TabContent content = tabPanel.getContent();
        content.add(firstPane);
        content.add(secondPane);
        return panel("Basic", tabPanel, "TabPanel tabPanel = new TabPanel();\ntabPanel.getTabs().add(new TabListItem(...));");
    }

    private Widget carousel() {
        String carouselId = "showcaseCarousel";
        Carousel carousel = new Carousel();
        carousel.getElement().setId(carouselId);
        CarouselIndicators indicators = new CarouselIndicators();
        CarouselIndicator firstIndicator = new CarouselIndicator(carouselId, 0);
        firstIndicator.setActive(true);
        indicators.addIndicator(firstIndicator);
        indicators.addIndicator(new CarouselIndicator(carouselId, 1));
        carousel.insert(indicators, 0);
        CarouselSlide firstSlide = new CarouselSlide(new HTML("<div class='d-flex align-items-center justify-content-center text-bg-primary rounded' style='height: 12rem;'>First slide</div>"));
        firstSlide.setActive(true);
        CarouselCaption caption = new CarouselCaption();
        caption.add(new Heading(5, "First slide"));
        firstSlide.add(caption);
        carousel.addSlide(firstSlide);
        carousel.addSlide(new CarouselSlide(new HTML("<div class='d-flex align-items-center justify-content-center text-bg-success rounded' style='height: 12rem;'>Second slide</div>")));
        carousel.add(new CarouselControl(carouselId, true));
        carousel.add(new CarouselControl(carouselId, false));
        return carousel;
    }

    private Widget samplePanel() {
        Panel panel = new Panel(Variant.PRIMARY);
        panel.add(new PanelHeader("Panel heading"));
        PanelBody body = new PanelBody();
        body.add(new Paragraph("Panel body"));
        panel.add(body);
        panel.add(new PanelFooter("Panel footer"));
        PanelGroup group = new PanelGroup();
        PanelCollapse collapse = new PanelCollapse();
        collapse.add(new Paragraph("PanelCollapse wrapper"));
        group.add(collapse);
        panel.add(group);
        return panel;
    }

    private Widget sampleCard() {
        org.gwtbootstrap5.client.ui.Card card = new org.gwtbootstrap5.client.ui.Card();
        card.setTitle("Card title");
        card.addBody(new Paragraph("Bootstrap 5 card content."));
        card.addBody(new AnchorButton("Card action", "#", Variant.PRIMARY));
        return card;
    }

    private Widget linkButton(String text) {
        Anchor link = new Anchor(text, "#");
        link.addStyleName("btn btn-link");
        return link;
    }

    private Row section(String id, String title, String subText) {
        Row row = row();
        Column column = fullColumn(row);
        addPageHeader(column, id, title, subText);
        return row;
    }

    private Row row() {
        Row row = new Row();
        row.setStyleName("row");
        return row;
    }

    private Column fullColumn() {
        Column column = new Column(12);
        return column;
    }

    private Column fullColumn(Row row) {
        Column column = fullColumn();
        row.add(column);
        return column;
    }

    private void addPageHeader(Column column, String id, String title, String subText) {
        PageHeader header = new PageHeader();
        header.getElement().setId(id);
        Heading heading = new Heading(2, title);
        if (subText != null && !subText.isEmpty()) {
            heading.add(new HTML(" <small class='text-body-secondary'>" + subText + "</small>"));
        }
        header.add(heading);
        column.add(header);
    }

    private Panel panel(String title, Widget bodyWidget, String code) {
        Panel panel = new Panel();
        panel.addStyleName("mb-4");
        PanelHeader header = new PanelHeader();
        header.add(new Heading(3, title));
        PanelBody body = new PanelBody();
        body.addStyleName("gbm-example-body");
        body.add(bodyWidget);
        PanelFooter footer = new PanelFooter();
        Pre pre = new Pre(code);
        pre.addStyleName("mb-0 small");
        footer.add(pre);
        panel.add(header);
        panel.add(body);
        panel.add(footer);
        return panel;
    }

    private Widget inline(Widget... widgets) {
        PanelBody wrapper = new PanelBody();
        wrapper.setStyleName("gbm-inline-demo");
        for (Widget widget : widgets) {
            wrapper.add(widget);
        }
        return wrapper;
    }

    private Widget note(String text) {
        return new HTML("<span class='text-body-secondary'>" + text + "</span>");
    }
}
