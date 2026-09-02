package org.gwtbootstrap5.showcase.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
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
import org.gwtbootstrap5.client.ui.constants.AlertType;
import org.gwtbootstrap5.client.ui.constants.ButtonType;
import org.gwtbootstrap5.client.ui.constants.ColumnOffset;
import org.gwtbootstrap5.client.ui.constants.ColumnSize;
import org.gwtbootstrap5.client.ui.constants.IconSize;
import org.gwtbootstrap5.client.ui.constants.IconType;
import org.gwtbootstrap5.client.ui.constants.ImageType;
import org.gwtbootstrap5.client.ui.constants.InputGroupSize;
import org.gwtbootstrap5.client.ui.constants.LabelType;
import org.gwtbootstrap5.client.ui.constants.PaginationSize;
import org.gwtbootstrap5.client.ui.constants.PanelType;
import org.gwtbootstrap5.client.ui.constants.ProgressBarType;
import org.gwtbootstrap5.client.ui.constants.ProgressType;
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
        root.add(container);

        final RootPanel modalRoot = root;
        final Container content = container;
        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                renderToken(content, modalRoot, event.getValue());
            }
        });
        renderToken(content, modalRoot, History.getToken());
    }

    private void renderToken(Container content, RootPanel root, String token) {
        content.clear();
        content.add(createPage(root, normalizeToken(token)));
    }

    private String normalizeToken(String token) {
        return token == null || token.isEmpty() ? "home" : token;
    }

    private Widget createPage(RootPanel root, String token) {
        if ("home".equals(token)) {
            return createHome();
        }
        if ("setup".equals(token)) {
            return createSetup();
        }
        if (contains(CSS_SECTIONS, token)) {
            Row row = createCssSections();
            filterSections(row, token);
            return row;
        }
        if (contains(COMPONENT_SECTIONS, token)) {
            Row row = createComponentSections();
            filterSections(row, token);
            return row;
        }
        if (contains(JS_SECTIONS, token)) {
            Row row = createJavaScriptSections(root);
            filterSections(row, token);
            return row;
        }
        if (contains(EXTRA_SECTIONS, token)) {
            Row row = createExtraSections();
            filterSections(row, token);
            return row;
        }
        return createHome();
    }

    private boolean contains(String[] values, String value) {
        for (String candidate : values) {
            if (candidate.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private void filterSections(Row row, String token) {
        for (Widget rowChild : row) {
            if (!(rowChild instanceof HasWidgets)) {
                continue;
            }
            boolean visible = false;
            for (Widget sectionChild : (HasWidgets) rowChild) {
                if (sectionChild instanceof PageHeader) {
                    visible = token.equals(sectionChild.getElement().getAttribute("data-section"));
                }
                sectionChild.setVisible(visible);
            }
        }
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

    private Row createCssSections() {
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
        Image rounded = new Image(IMG_THUMB);
        rounded.setType(ImageType.ROUNDED);
        Image circle = new Image(IMG_THUMB);
        circle.setType(ImageType.CIRCLE);
        Image thumbnail = new Image(IMG_THUMB);
        thumbnail.setType(ImageType.THUMBNAIL);
        column.add(panel("Basic", inline(rounded, circle, thumbnail, new ImageAnchor("#", IMG_THUMB)), "Image rounded = new Image(url);\nrounded.setType(ImageType.ROUNDED);\nimage.setType(ImageType.CIRCLE);\nimage.setType(ImageType.THUMBNAIL);"));

        addPageHeader(column, "responsiveUtilities", "Responsive Utilities", null);
        column.add(panel("Bootstrap 5 utilities", new HTML("<p class='d-none d-md-block'>Visible on medium screens and wider.</p><p class='d-md-none'>Visible below medium screens.</p>"), "Bootstrap 5 responsive display utilities: d-none, d-md-block, d-md-none."));

        addPageHeader(column, "tables", "Tables", null);
        column.add(panel("Basic", new HTML("<table class='table table-striped'><caption>Table caption</caption><thead><tr><th>#</th><th>Name</th></tr></thead><tbody><tr><td>1</td><td>Bootstrap 5</td></tr></tbody></table>"), "<table class=\"table table-striped\">...</table>"));

        addPageHeader(column, "typography", "Typography", null);
        column.add(typographyPanel());
        return row;
    }

    private Row createComponentSections() {
        Row row = row();
        Column column = fullColumn(row);
        addPageHeader(column, "alerts", "Alerts", null);
        column.add(alertsBasicPanel());
        column.add(alertDismissiblePanel());
        column.add(alertLinksPanel());

        addPageHeader(column, "badges", "Badges", null);
        column.add(badgesPanel());

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
        column.add(labelsPanel());

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
        column.add(pagerPanel());

        addPageHeader(column, "panels", "Panels", null);
        column.add(panel("Basic", basicPanelExample(), "Panel panel = new Panel();\npanel.add(new PanelHeader(\"Panel Header\"));\npanel.add(new PanelBody());\npanel.add(new PanelFooter(\"Panel Footer\"));"));
        column.add(panel("Contextual Classes", inline(panelExample(PanelType.INFO), panelExample(PanelType.DANGER), panelExample(PanelType.SUCCESS)), "new Panel(PanelType.INFO);\nnew Panel(PanelType.DANGER);\nnew Panel(PanelType.SUCCESS);"));

        addPageHeader(column, "progressBars", "Progress Bars", null);
        column.add(progressBasicPanel());
        column.add(progressStripedPanel());
        column.add(progressAnimatedPanel());
        column.add(progressStackedPanel());

        addPageHeader(column, "suggestBox", "SuggestBox", null);
        column.add(panel("Basic", new SuggestBox(), "new SuggestBox();"));

        addPageHeader(column, "thumbnails", "Thumbnails", null);
        column.add(thumbnailsPanel());

        addPageHeader(column, "wells", "Wells", null);
        column.add(wellsPanel());
        return row;
    }

    private Row createJavaScriptSections(RootPanel root) {
        Row row = row();
        Column column = fullColumn(row);
        addPageHeader(column, "carousel", "Carousel", null);
        column.add(panel("Basic", carousel(), "Carousel carousel = new Carousel();\ncarousel.addSlide(...);"));

        addPageHeader(column, "collapse", "Collapse", null);
        column.add(collapsePanel());
        column.add(accordionPanel());

        addPageHeader(column, "modals", "Modals", null);
        column.add(modalPanel(root));

        addPageHeader(column, "popover", "Popover", null);
        Popover popover = new Popover(new Button("Popover", ButtonType.DEFAULT), "Popover", "Popover content");
        popover.init();
        column.add(panel("Basic", popover, "new Popover(widget, \"Popover\", \"Popover content\");"));

        addPageHeader(column, "tabs", "Tabs", null);
        column.add(tabsPanel());

        addPageHeader(column, "tooltips", "Tooltips", null);
        Tooltip tooltip = new Tooltip(new Button("Tooltip", ButtonType.DEFAULT), "Tooltip text");
        tooltip.init();
        column.add(panel("Basic", inline(tooltip, new TooltipHelpBlock("TooltipHelpBlock")), "new Tooltip(widget, \"Tooltip text\");"));
        return row;
    }

    private Row createExtraSections() {
        Row row = row();
        Column column = fullColumn(row);
        addPageHeader(column, "cards", "Cards", null);
        column.add(panel("Bootstrap 5 native", sampleCard(), "Card is Bootstrap 5-native and replaces many Bootstrap 3 panel/card-extra use cases."));
        addPageHeader(column, "unsupportedExtras", "Unsupported Extras", null);
        column.add(panel("Not part of core Bootstrap 5", new HTML("<p>Bootstrap Select, Bootbox, DatePicker, DateTimePicker, FullCalendar, Gallery, Notify, Slider, Summernote, TagsInput, ToggleSwitch, Typeahead and Offline are extras with separate third-party dependencies. They are intentionally not represented as Bootstrap 5 core widgets yet.</p>"), "These require separate migration decisions, not silent Bootstrap 5 shims."));
        return row;
    }

    private Widget buttonsBasicPanel() {
        return panel("Basic", inline(new Button("Default", ButtonType.DEFAULT), new Button("Primary", ButtonType.PRIMARY), new Button("Success", ButtonType.SUCCESS), new Button("Info", ButtonType.INFO), new Button("Warning", ButtonType.WARNING), new Button("Danger", ButtonType.DANGER), linkButton("Link")), "new Button(\"Primary\", ButtonType.PRIMARY);" );
    }

    private Widget buttonSizesPanel() {
        Button small = new Button("Small", ButtonType.PRIMARY);
        small.setSmall(true);
        Button large = new Button("Large", ButtonType.PRIMARY);
        large.setLarge(true);
        return panel("Sizes", inline(small, large, note("Bootstrap 5 has small and large button sizes; extra-small is not a Bootstrap 5 button size.")), "button.setSmall(true);\nbutton.setLarge(true);" );
    }

    private Widget buttonStatesPanel() {
        Button enabled = new Button("Enabled", ButtonType.PRIMARY);
        Button disabled = new Button("Disabled", ButtonType.PRIMARY);
        disabled.setEnabled(false);
        Button toggle = new Button("Toggle button", ButtonType.DEFAULT);
        toggle.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                toggle.toggle();
            }
        });
        Button loading = new Button("Click me", ButtonType.PRIMARY);
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
        Button block = new Button("Block level button", ButtonType.PRIMARY);
        block.setBlock(true);
        return panel("States", inline(enabled, disabled, toggle, loading, block), "button.setActive(true);\nbutton.setLoadingText(\"Loading...\");\nbutton.state().loading();\nbutton.state().reset();\nbutton.toggle();\nbutton.setBlock(true);" );
    }

    private Widget buttonCompositionPanel() {
        Button button = new Button("With icon and badge", ButtonType.PRIMARY);
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
        Row compatibilityGrid = row();
        Column stringSized = new Column("XS_12 MD_6");
        stringSized.add(new HTML("<div class='p-3 text-bg-success rounded'>new Column(\"XS_12 MD_6\")</div>"));
        Column offset = new Column(ColumnSize.XS_12);
        offset.addSize(ColumnSize.MD_3);
        offset.setOffset(ColumnOffset.MD_3);
        offset.add(new HTML("<div class='p-3 text-bg-warning rounded'>MD_3 with MD_3 offset</div>"));
        compatibilityGrid.add(stringSized);
        compatibilityGrid.add(offset);
        return panel("Basic and Compatibility", stacked(grid, compatibilityGrid), "Column column = new Column(12);\ncolumn.setMediumSpan(6);\nnew Column(\"XS_12 MD_6\");\ncolumn.addSize(ColumnSize.MD_3);\ncolumn.setOffset(ColumnOffset.MD_3);");
    }

    private Widget alertsBasicPanel() {
        return panel("Basic", stacked(
                alert(AlertType.SUCCESS),
                alert(AlertType.INFO),
                alert(AlertType.WARNING),
                alert(AlertType.DANGER)),
                "new Alert(\"Title Description\", AlertType.SUCCESS);\nnew Alert(\"Title Description\", AlertType.INFO);\nnew Alert(\"Title Description\", AlertType.WARNING);\nnew Alert(\"Title Description\", AlertType.DANGER);");
    }

    private Widget alertDismissiblePanel() {
        Alert alert = alert(AlertType.SUCCESS);
        alert.setDismissible(true);
        return panel("Dismissible with Handlers", alert, "Alert alert = new Alert(\"Title Description\", AlertType.SUCCESS);\nalert.setDismissible(true);\nalert.addCloseHandler(...);\nalert.addClosedHandler(...);");
    }

    private Widget alertLinksPanel() {
        Alert alert = alert(AlertType.SUCCESS);
        alert.setDismissible(true);
        alert.add(new Anchor("Link", "#"));
        return panel("Links Inside", alert, "Alert alert = new Alert(\"Title Description\", AlertType.SUCCESS);\nalert.setDismissible(true);\nalert.add(new Anchor(\"Link\", \"#\"));");
    }

    private Alert alert(AlertType type) {
        Alert alert = new Alert("", type);
        alert.add(new HTML("<strong>Title</strong> Description"));
        return alert;
    }

    private Widget badgesPanel() {
        Badge deleted = new Badge("42");
        Anchor deletedItems = new Anchor("Deleted Items ", "#");
        deletedItems.add(deleted);
        Badge inbox = new Badge();
        inbox.add(new HTML("12 <i class='bi bi-envelope'></i> <em>unread</em>"));
        Anchor inboxLink = new Anchor("Inbox ", "#");
        inboxLink.add(inbox);
        Badge pill = new Badge("Pill", LabelType.SUCCESS);
        pill.setPill(true);
        return panel("Basic and Advanced", stacked(
                deletedItems,
                inboxLink,
                inline(new Label("Label concept", LabelType.DEFAULT), pill)),
                "new Badge(\"42\");\nBadge badge = new Badge();\nbadge.add(new Icon(\"envelope\"));\nbadge.setPill(true);");
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
        group.addButton(new Button("Button 1", ButtonType.DEFAULT));
        group.addButton(new Button("Button 2", ButtonType.DEFAULT));
        group.addButton(new Button("Button 3", ButtonType.DEFAULT));
        ButtonGroup large = new ButtonGroup();
        large.setLarge(true);
        large.addButton(new Button("Left", ButtonType.DEFAULT));
        large.addButton(new Button("Middle", ButtonType.DEFAULT));
        large.addButton(new Button("Right", ButtonType.DEFAULT));
        ButtonGroup small = new ButtonGroup();
        small.setSmall(true);
        small.addButton(new Button("Left", ButtonType.DEFAULT));
        small.addButton(new Button("Middle", ButtonType.DEFAULT));
        small.addButton(new Button("Right", ButtonType.DEFAULT));
        VerticalButtonGroup vertical = new VerticalButtonGroup();
        vertical.addButton(new Button("Top", ButtonType.DEFAULT));
        vertical.addButton(new Button("Bottom", ButtonType.DEFAULT));
        ButtonToolBar toolbar = new ButtonToolBar();
        toolbar.addGroup(group);
        return panel("Basic and Sizing", stacked(inline(toolbar, vertical), inline(large, small)), "ButtonGroup group = new ButtonGroup();\ngroup.addButton(new Button(\"Button 1\"));\nlarge.setLarge(true);\nsmall.setSmall(true);\nButtonToolBar toolbar = new ButtonToolBar();");
    }

    private Widget dropdownsPanel() {
        DropDown dropDown = new DropDown("Click to toggle dropdown");
        dropDown.setMenuMatchToggleWidth(true);
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
        endAligned.setMenuMatchToggleWidth(true);
        endAligned.addItem(new DropDownItem("Action", "#"));
        endAligned.addItem(new DropDownItem("Another action", "#"));
        DropDown dropUp = new DropDown("Dropup");
        dropUp.setDropUp(true);
        dropUp.setMenuMatchToggleWidth(true);
        dropUp.addItem(new DropDownItem("Action", "#"));
        dropUp.addItem(new DropDownItem("Another action", "#"));
        return panel("Basic", inline(dropDown, endAligned, dropUp), "DropDown dropDown = new DropDown(\"Click to toggle dropdown\");\ndropDown.addItem(new DropDownItem(\"Action\", \"#\"));\ndropDown.setMenuEndAligned(true);\ndropDown.setDropUp(true);\ndropDown.setMenuMatchToggleWidth(true);");
    }

    private Widget inputGroupsPanel() {
        InputGroup inputGroup = new InputGroup();
        inputGroup.add(new InputGroupAddon("@"));
        Input input = new Input("text");
        input.setPlaceholder("Username");
        inputGroup.add(input);
        inputGroup.add(new InputGroupButton(new Button("Go", ButtonType.PRIMARY)));
        InputGroup large = new InputGroup();
        large.setSize(InputGroupSize.LARGE);
        large.add(new InputGroupAddon("Large"));
        Input largeInput = new Input("text");
        largeInput.setPlaceholder("InputGroupSize.LARGE");
        large.add(largeInput);
        InputGroup small = new InputGroup();
        small.setSmall(true);
        small.add(new InputGroupAddon("Small"));
        Input smallInput = new Input("text");
        smallInput.setPlaceholder("setSmall(true)");
        small.add(smallInput);
        return panel("Basic and Sizing", stacked(inputGroup, large, small), "InputGroup inputGroup = new InputGroup();\ninputGroup.add(new InputGroupAddon(\"@\"));\ninputGroup.setSize(InputGroupSize.LARGE);\ninputGroup.setSmall(true);");
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
        Pagination pagination = pagination(PaginationSize.NONE);
        Pagination small = pagination(PaginationSize.SMALL);
        Pagination large = pagination(PaginationSize.LARGE);
        return panel("Basic", stacked(pagination, small, large), "Pagination pagination = new Pagination();\npagination.setPaginationSize(PaginationSize.SMALL);\npagination.setPaginationSize(PaginationSize.LARGE);");
    }

    private Widget pagerPanel() {
        Pager pager = new Pager();
        Pager custom = new Pager();
        custom.setPreviousText("Older");
        custom.setNextText("Newer");
        Pager aligned = new Pager();
        aligned.setPreviousText("Older");
        aligned.setNextText("Newer");
        aligned.setAlignToSides(true);
        Pager iconPager = new Pager();
        iconPager.setPreviousIcon(IconType.ANGLE_LEFT);
        iconPager.setNextIcon(IconType.ANGLE_RIGHT);
        iconPager.setPreviousIconSize(IconSize.LARGE);
        iconPager.setNextIconSize(IconSize.LARGE);
        return panel("Pager", stacked(pager, custom, aligned, iconPager), "new Pager();\npager.setPreviousText(\"Older\");\npager.setNextText(\"Newer\");\npager.setAlignToSides(true);\npager.setPreviousIcon(IconType.ANGLE_LEFT);\npager.setNextIcon(IconType.ANGLE_RIGHT);");
    }

    private Pagination pagination(PaginationSize size) {
        Pagination pagination = new Pagination();
        pagination.setPaginationSize(size);
        pagination.addPreviousLink();
        AnchorListItem active = new AnchorListItem("1", "#");
        active.setActive(true);
        pagination.add(active);
        pagination.add(new AnchorListItem("2", "#"));
        pagination.addNextLink();
        return pagination;
    }

    private Widget progressBasicPanel() {
        return panel("Basic", stacked(
                progress(40, ProgressBarType.SUCCESS),
                progress(20, ProgressBarType.INFO),
                progress(60, ProgressBarType.WARNING),
                progress(80, ProgressBarType.DANGER)),
                "new ProgressBar(40, ProgressBarType.SUCCESS);\nnew ProgressBar(20, ProgressBarType.INFO);\nnew ProgressBar(60, ProgressBarType.WARNING);\nnew ProgressBar(80, ProgressBarType.DANGER);");
    }

    private Widget progressStripedPanel() {
        return panel("Striped", stacked(
                stripedProgress(40, ProgressBarType.SUCCESS),
                stripedProgress(20, ProgressBarType.INFO),
                stripedProgress(60, ProgressBarType.WARNING),
                stripedProgress(80, ProgressBarType.DANGER)),
                "Progress progress = new Progress();\nprogress.setType(ProgressType.STRIPED);");
    }

    private Widget progressAnimatedPanel() {
        Progress progress = stripedProgress(40, ProgressBarType.SUCCESS);
        progress.setActive(true);
        return panel("Animated", progress, "Progress progress = new Progress();\nprogress.setType(ProgressType.STRIPED);\nprogress.setActive(true);");
    }

    private Widget progressStackedPanel() {
        Progress progress = new Progress();
        ProgressBar success = new ProgressBar();
        success.setPercent(35);
        success.setType(ProgressBarType.SUCCESS);
        ProgressBar warning = new ProgressBar();
        warning.setPercent(20);
        warning.setType(ProgressBarType.WARNING);
        ProgressBar danger = new ProgressBar();
        danger.setPercent(10);
        danger.setType(ProgressBarType.DANGER);
        progress.add(success);
        progress.add(warning);
        progress.add(danger);
        return panel("Stacked", progress, "Progress progress = new Progress();\nprogress.add(new ProgressBar(...));\nprogress.add(new ProgressBar(...));");
    }

    private Progress progress(int percent, ProgressBarType type) {
        Progress progress = new Progress();
        ProgressBar bar = new ProgressBar();
        bar.setPercent(percent);
        bar.setText(percent + "%");
        bar.setType(type);
        progress.add(bar);
        return progress;
    }

    private Progress stripedProgress(int percent, ProgressBarType type) {
        Progress progress = progress(percent, type);
        progress.setType(ProgressType.STRIPED);
        return progress;
    }

    private Widget thumbnailsPanel() {
        ThumbnailPanel panel = new ThumbnailPanel();
        panel.addBody(new Image(IMG_THUMB));
        panel.addBody(new Paragraph("ThumbnailPanel maps to Card."));
        ThumbnailLink link = new ThumbnailLink("#");
        link.add(new Image(IMG_THUMB));
        return panel("Basic", inline(panel, link), "new ThumbnailPanel();\nnew ThumbnailLink(\"#\");");
    }

    private Widget labelsPanel() {
        return panel("Basic", inline(
                new Label("Default", LabelType.DEFAULT),
                new Label("Primary", LabelType.PRIMARY),
                new Label("Success", LabelType.SUCCESS),
                new Label("Info", LabelType.INFO),
                new Label("Warning", LabelType.WARNING),
                new Label("Danger", LabelType.DANGER)),
                "new Label(\"Primary\", LabelType.PRIMARY);");
    }

    private Widget wellsPanel() {
        Well standard = new Well();
        standard.add(new HTML("<span>Look, I am in a well!</span>"));
        Well large = new Well();
        large.addStyleName("p-5");
        large.add(new HTML("<span>Large well mapping</span>"));
        Well small = new Well();
        small.addStyleName("p-2");
        small.add(new HTML("<span>Small well mapping</span>"));
        return panel("Default and Optional Classes", stacked(standard, large, small), "new Well();\nwell.addStyleName(\"p-5\");\nwell.addStyleName(\"p-2\");");
    }

    private Widget basicPanelExample() {
        Panel panel = new Panel();
        panel.add(new PanelHeader("Panel Header"));
        PanelBody body = new PanelBody();
        body.add(new HTML("<strong>Panel Body</strong>"));
        panel.add(body);
        panel.add(new PanelFooter("Panel Footer"));
        return panel;
    }

    private Widget panelExample(PanelType type) {
        Panel panel = new Panel(type);
        panel.add(new PanelHeader("Panel Header"));
        PanelBody body = new PanelBody();
        body.add(new HTML("<strong>Panel Body</strong>"));
        panel.add(body);
        panel.add(new PanelFooter("Panel Footer"));
        return panel;
    }

    private Widget collapsePanel() {
        Collapse collapse = new Collapse();
        collapse.getElement().setId("collapseExample");
        collapse.add(new Well());
        Button button = new Button("Toggle collapse", ButtonType.PRIMARY);
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                collapse.toggle();
            }
        });
        return panel("Basic", inline(button, collapse), "Collapse collapse = new Collapse();\ncollapse.toggle();");
    }

    private Widget accordionPanel() {
        PanelGroup accordion = new PanelGroup();
        accordion.getElement().setId("accordion");
        accordion.add(accordionItem("collapseOne", "Collapse Group #1", true));
        accordion.add(accordionItem("collapseTwo", "Collapse Group #2", false));
        accordion.add(accordionItem("collapseThree", "Collapse Group #3", false));
        return panel("Accordion Example using PanelCollapse", accordion, "PanelGroup accordion = new PanelGroup();\naccordion.add(panelWithPanelCollapse(...));");
    }

    private Widget accordionItem(String id, String title, boolean open) {
        Panel panel = new Panel();
        PanelHeader header = new PanelHeader();
        Anchor anchor = new Anchor(title, "#" + id);
        anchor.setDataToggle("collapse");
        anchor.getElement().setAttribute("data-bs-target", "#" + id);
        anchor.getElement().setAttribute("data-bs-parent", "#accordion");
        header.add(anchor);
        panel.add(header);
        PanelCollapse collapse = new PanelCollapse();
        collapse.getElement().setId(id);
        collapse.setIn(open);
        PanelBody body = new PanelBody();
        body.add(new Paragraph("I am the content of " + title + "."));
        collapse.add(body);
        panel.add(collapse);
        return panel;
    }

    private Widget modalPanel(RootPanel root) {
        Modal modal = new Modal();
        modal.getElement().setId("exampleModal");
        modal.setTitle("Cupcake ipsum");
        modal.addToBody(new Paragraph("Modal body..."));
        ModalFooter footer = new ModalFooter();
        footer.add(new Button("Do something", ButtonType.PRIMARY));
        Button close = new Button("Close", ButtonType.DEFAULT);
        close.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                modal.hide();
            }
        });
        footer.add(close);
        modal.addFooter(footer);
        root.add(modal);
        Button show = new Button("Click to show modal", ButtonType.PRIMARY);
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
        Panel panel = new Panel(PanelType.PRIMARY);
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
        card.addBody(new AnchorButton("Card action", "#", ButtonType.PRIMARY));
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
        header.getElement().setAttribute("data-section", id);
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

    private Widget stacked(Widget... widgets) {
        PanelBody wrapper = new PanelBody();
        wrapper.setStyleName("gbm-stacked-demo");
        for (Widget widget : widgets) {
            wrapper.add(widget);
        }
        return wrapper;
    }

    private Widget note(String text) {
        return new HTML("<span class='text-body-secondary'>" + text + "</span>");
    }
}
