package org.gwtbootstrap5.showcase.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import org.gwtbootstrap5.client.ui.Alert;
import org.gwtbootstrap5.client.ui.Anchor;
import org.gwtbootstrap5.client.ui.AnchorButton;
import org.gwtbootstrap5.client.ui.Badge;
import org.gwtbootstrap5.client.ui.Button;
import org.gwtbootstrap5.client.ui.ButtonGroup;
import org.gwtbootstrap5.client.ui.ButtonToolBar;
import org.gwtbootstrap5.client.ui.Card;
import org.gwtbootstrap5.client.ui.Carousel;
import org.gwtbootstrap5.client.ui.CarouselCaption;
import org.gwtbootstrap5.client.ui.CarouselControl;
import org.gwtbootstrap5.client.ui.CarouselIndicator;
import org.gwtbootstrap5.client.ui.CarouselIndicators;
import org.gwtbootstrap5.client.ui.CarouselSlide;
import org.gwtbootstrap5.client.ui.CheckBox;
import org.gwtbootstrap5.client.ui.Collapse;
import org.gwtbootstrap5.client.ui.Column;
import org.gwtbootstrap5.client.ui.Container;
import org.gwtbootstrap5.client.ui.Divider;
import org.gwtbootstrap5.client.ui.DropDown;
import org.gwtbootstrap5.client.ui.DropDownItem;
import org.gwtbootstrap5.client.ui.Form;
import org.gwtbootstrap5.client.ui.FormGroup;
import org.gwtbootstrap5.client.ui.FormLabel;
import org.gwtbootstrap5.client.ui.HelpBlock;
import org.gwtbootstrap5.client.ui.Heading;
import org.gwtbootstrap5.client.ui.Input;
import org.gwtbootstrap5.client.ui.InputGroup;
import org.gwtbootstrap5.client.ui.InputGroupAddon;
import org.gwtbootstrap5.client.ui.InputGroupButton;
import org.gwtbootstrap5.client.ui.Jumbotron;
import org.gwtbootstrap5.client.ui.Label;
import org.gwtbootstrap5.client.ui.Lead;
import org.gwtbootstrap5.client.ui.ListBox;
import org.gwtbootstrap5.client.ui.ListGroup;
import org.gwtbootstrap5.client.ui.ListGroupItem;
import org.gwtbootstrap5.client.ui.Modal;
import org.gwtbootstrap5.client.ui.ModalFooter;
import org.gwtbootstrap5.client.ui.NavPills;
import org.gwtbootstrap5.client.ui.NavTabs;
import org.gwtbootstrap5.client.ui.Navbar;
import org.gwtbootstrap5.client.ui.NavbarBrand;
import org.gwtbootstrap5.client.ui.NavbarCollapse;
import org.gwtbootstrap5.client.ui.NavbarCollapseButton;
import org.gwtbootstrap5.client.ui.NavbarLink;
import org.gwtbootstrap5.client.ui.PageItem;
import org.gwtbootstrap5.client.ui.Pager;
import org.gwtbootstrap5.client.ui.Pagination;
import org.gwtbootstrap5.client.ui.Panel;
import org.gwtbootstrap5.client.ui.PanelBody;
import org.gwtbootstrap5.client.ui.PanelFooter;
import org.gwtbootstrap5.client.ui.PanelHeader;
import org.gwtbootstrap5.client.ui.Paragraph;
import org.gwtbootstrap5.client.ui.Progress;
import org.gwtbootstrap5.client.ui.ProgressBar;
import org.gwtbootstrap5.client.ui.Radio;
import org.gwtbootstrap5.client.ui.Row;
import org.gwtbootstrap5.client.ui.TextArea;
import org.gwtbootstrap5.client.ui.Tooltip;
import org.gwtbootstrap5.client.ui.Variant;
import org.gwtbootstrap5.client.ui.Well;
import org.gwtbootstrap5.client.ui.Popover;

public class ShowcaseEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        RootPanel root = RootPanel.get("showcase");
        if (root == null) {
            root = RootPanel.get();
        }

        Navbar navbar = new Navbar();
        NavbarCollapseButton navbarCollapseButton = new NavbarCollapseButton("showcaseNavbar");
        NavbarCollapse navbarCollapse = new NavbarCollapse();
        navbarCollapse.getElement().setId("showcaseNavbar");
        navbar.getContainer().add(new NavbarBrand("GWT Bootstrap 5 Modern", "#"));
        navbar.getContainer().add(navbarCollapseButton);
        navbarCollapse.add(navbar.getNav());
        navbar.getContainer().add(navbarCollapse);
        navbar.getNav().setEndAligned(true);
        NavbarLink components = new NavbarLink("Components", "#components");
        components.setActive(true);
        navbar.getNav().add(components);
        navbar.getNav().add(new NavbarLink("Bootstrap 3 showcase", "../"));
        root.add(navbar);

        Container container = new Container();
        container.addStyleName("py-5");
        container.add(new HTML("<section class=\"p-5 mb-4 rounded-3 bg-light\"><h1 class=\"display-5 fw-bold\">Bootstrap 5-native GWT widgets</h1><p class=\"lead\">This module is intentionally separate from the GwtBootstrap3 compatibility API. It uses Bootstrap 5 classes, data attributes, and templates directly.</p></section>"));

        Jumbotron jumbotron = new Jumbotron();
        jumbotron.add(new Heading(2, "Jumbotron concept"));
        jumbotron.add(new Paragraph("Bootstrap 5 removed jumbotron, so this maps to spacing, background and rounded utilities."));
        container.add(jumbotron);

        Row row = new Row();
        Column first = new Column(12);
        first.setMediumSpan(6);
        Card buttons = new Card();
        buttons.setTitle("Buttons");
        buttons.addBody(new HTML("<p class=\"card-text\">Buttons expose Bootstrap 5 variants rather than Bootstrap 3 button state APIs.</p>"));
        buttons.addBody(new Button("Primary", Variant.PRIMARY));
        Button outline = new Button("Outline success", Variant.SUCCESS);
        outline.setOutline(true);
        outline.addStyleName("ms-2");
        buttons.addBody(outline);
        first.add(buttons);

        Column second = new Column(12);
        second.setMediumSpan(6);
        Card markup = new Card();
        markup.setTitle("Bootstrap 5 markup");
        markup.addBody(new HTML("<p class=\"card-text\">Components use Bootstrap 5 names such as <code>data-bs-toggle</code>, <code>card</code>, <code>ms-auto</code>, and <code>bg-body-tertiary</code>.</p><span class=\"badge text-bg-primary\"><i class=\"bi bi-check2-circle\"></i> Separate API</span>"));
        second.add(markup);

        row.add(first);
        row.add(second);
        container.add(row);

        Row parity = new Row();
        parity.setStyleName("row g-4 mt-1");

        Column feedbackColumn = new Column(12);
        feedbackColumn.setMediumSpan(6);
        Card feedback = new Card();
        feedback.setTitle("Feedback and status");
        Alert alert = new Alert("Dismissible alerts use Bootstrap 5 alert markup and data-bs-dismiss.", Variant.INFO);
        alert.setDismissible(true);
        feedback.addBody(alert);
        Badge badge = new Badge("text-bg-success badge", Variant.SUCCESS);
        badge.setPill(true);
        feedback.addBody(badge);
        feedback.addBody(new HTML(" "));
        feedback.addBody(new Label("label concept as badge", Variant.SECONDARY));
        feedback.addBody(new HTML("<div class=\"mt-3\"></div>"));
        Anchor anchor = new Anchor("Button-styled anchor", "https://getbootstrap.com/docs/5.3/components/buttons/");
        anchor.setButtonVariant(Variant.PRIMARY);
        anchor.setOutline(true);
        feedback.addBody(anchor);
        feedbackColumn.add(feedback);

        Column listColumn = new Column(12);
        listColumn.setMediumSpan(6);
        Card lists = new Card();
        lists.setTitle("List groups");
        ListGroup group = new ListGroup();
        group.add(new ListGroupItem("Plain Bootstrap 5 list-group-item"));
        ListGroupItem active = new ListGroupItem("Active item");
        active.setActive(true);
        group.add(active);
        ListGroupItem warning = new ListGroupItem("Contextual warning item");
        warning.setVariant(Variant.WARNING);
        group.add(warning);
        lists.addBody(group);
        listColumn.add(lists);

        Column contentColumn = new Column(12);
        contentColumn.setMediumSpan(6);
        Panel panel = new Panel(Variant.PRIMARY);
        panel.add(new PanelHeader("Panel concept mapped to Bootstrap 5 card"));
        PanelBody body = new PanelBody();
        body.add(new Heading(3, "Card-backed panel"));
        body.add(new Lead("Bootstrap 5 removed panels and wells, so these compatibility surfaces use cards and utility classes."));
        body.add(new Paragraph("The compatibility names are available, but the generated DOM is Bootstrap 5-native."));
        panel.add(body);
        panel.add(new PanelFooter("card-footer"));
        contentColumn.add(panel);

        Column wellColumn = new Column(12);
        wellColumn.setMediumSpan(6);
        Well well = new Well();
        well.add(new Heading(3, "Well concept"));
        well.add(new Paragraph("Rendered as p-3 rounded bg-body-tertiary border."));
        wellColumn.add(well);

        parity.add(feedbackColumn);
        parity.add(listColumn);
        parity.add(contentColumn);
        parity.add(wellColumn);
        container.add(parity);

        Row interactive = new Row();
        interactive.setStyleName("row g-4 mt-1");

        Column modalColumn = new Column(12);
        modalColumn.setMediumSpan(4);
        Card modalCard = new Card();
        modalCard.setTitle("Modal");
        modalCard.addBody(new Paragraph("Modal show/hide uses Bootstrap 5's JavaScript API, not jQuery."));
        Button showModal = new Button("Open modal", Variant.PRIMARY);
        modalCard.addBody(showModal);
        Modal modal = new Modal();
        modal.setTitle("Bootstrap 5 modal");
        modal.addToBody(new Paragraph("This modal is built from GWT widgets and shown through bootstrap.Modal."));
        ModalFooter modalFooter = new ModalFooter();
        Button closeModal = new Button("Close", Variant.SECONDARY);
        closeModal.getElement().setAttribute("data-bs-dismiss", "modal");
        modalFooter.add(closeModal);
        modal.addFooter(modalFooter);
        showModal.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                modal.show();
            }
        });
        modalColumn.add(modalCard);
        modalColumn.add(modal);

        Column dropdownColumn = new Column(12);
        dropdownColumn.setMediumSpan(4);
        Card dropdownCard = new Card();
        dropdownCard.setTitle("Dropdown");
        DropDown dropDown = new DropDown("Actions");
        dropDown.addItem(new DropDownItem("Primary action", "#"));
        DropDownItem disabled = new DropDownItem("Disabled action", "#");
        disabled.setDisabled(true);
        dropDown.addItem(disabled);
        dropDown.addMenuWidget(new Divider());
        dropDown.addItem(new DropDownItem("Separated action", "#"));
        dropdownCard.addBody(dropDown);
        dropdownColumn.add(dropdownCard);

        Column paginationColumn = new Column(12);
        paginationColumn.setMediumSpan(4);
        Card paginationCard = new Card();
        paginationCard.setTitle("Pagination");
        Pagination pagination = new Pagination();
        PageItem previous = new PageItem("Previous", "#");
        previous.setDisabled(true);
        pagination.add(previous);
        pagination.add(new PageItem("1", "#"));
        PageItem current = new PageItem("2", "#");
        current.setActive(true);
        pagination.add(current);
        pagination.add(new PageItem("3", "#"));
        pagination.add(new PageItem("Next", "#"));
        paginationCard.addBody(pagination);
        Pager pager = new Pager();
        pager.setAlignToSides(true);
        pager.setPreviousEnabled(false);
        pager.addStyleName("mt-3");
        paginationCard.addBody(pager);
        paginationColumn.add(paginationCard);

        interactive.add(modalColumn);
        interactive.add(dropdownColumn);
        interactive.add(paginationColumn);
        container.add(interactive);

        Row controls = new Row();
        controls.setStyleName("row g-4 mt-1");

        Column buttonGroupsColumn = new Column(12);
        buttonGroupsColumn.setMediumSpan(4);
        Card groupedButtons = new Card();
        groupedButtons.setTitle("Button groups");
        ButtonToolBar toolbar = new ButtonToolBar();
        ButtonGroup primaryGroup = new ButtonGroup();
        primaryGroup.addButton(new Button("Left", Variant.PRIMARY));
        primaryGroup.addButton(new Button("Middle", Variant.PRIMARY));
        primaryGroup.addButton(new Button("Right", Variant.PRIMARY));
        toolbar.addGroup(primaryGroup);
        ButtonGroup secondaryGroup = new ButtonGroup();
        secondaryGroup.addStyleName("ms-2");
        secondaryGroup.addButton(new AnchorButton("Link", "#", Variant.SECONDARY));
        secondaryGroup.addButton(new Button("Action", Variant.SECONDARY));
        toolbar.addGroup(secondaryGroup);
        groupedButtons.addBody(toolbar);
        buttonGroupsColumn.add(groupedButtons);

        Column navColumn = new Column(12);
        navColumn.setMediumSpan(4);
        Card navCard = new Card();
        navCard.setTitle("Navigation");
        NavTabs tabs = new NavTabs();
        tabs.addLink("Active", "#").addStyleName("active");
        tabs.addLink("Profile", "#");
        tabs.addLink("Disabled", "#").addStyleName("disabled");
        navCard.addBody(tabs);
        NavPills pills = new NavPills();
        pills.addStyleName("mt-3");
        pills.addLink("Build", "#").addStyleName("active");
        pills.addLink("Release", "#");
        navCard.addBody(pills);
        navColumn.add(navCard);

        Column progressColumn = new Column(12);
        progressColumn.setMediumSpan(4);
        Card progressCard = new Card();
        progressCard.setTitle("Progress");
        Progress progress = new Progress();
        ProgressBar bar = new ProgressBar(65);
        bar.setVariant(Variant.SUCCESS);
        bar.setStriped(true);
        progress.addBar(bar);
        progressCard.addBody(progress);
        progressColumn.add(progressCard);

        controls.add(buttonGroupsColumn);
        controls.add(navColumn);
        controls.add(progressColumn);
        container.add(controls);

        Row forms = new Row();
        forms.setStyleName("row g-4 mt-1");
        Column formColumn = new Column(12);
        formColumn.setMediumSpan(8);
        Card formCard = new Card();
        formCard.setTitle("Forms");
        Form form = new Form();
        FormGroup nameGroup = new FormGroup();
        nameGroup.add(new FormLabel("Name"));
        Input name = new Input("text");
        name.setPlaceholder("Bootstrap 5 input");
        nameGroup.add(name);
        nameGroup.add(new HelpBlock("HelpBlock maps to Bootstrap 5 form-text."));
        form.add(nameGroup);
        FormGroup notesGroup = new FormGroup();
        notesGroup.add(new FormLabel("Notes"));
        TextArea notes = new TextArea();
        notes.setVisibleLines(3);
        notesGroup.add(notes);
        form.add(notesGroup);
        CheckBox enabled = new CheckBox("Enable option");
        enabled.setValue(true);
        form.add(enabled);
        form.add(new Radio("Radio option"));
        FormGroup choiceGroup = new FormGroup();
        choiceGroup.add(new FormLabel("ListBox"));
        ListBox listBox = new ListBox();
        listBox.addItem("Alpha", "alpha");
        listBox.addItem("Bravo", "bravo");
        choiceGroup.add(listBox);
        form.add(choiceGroup);
        InputGroup inputGroup = new InputGroup();
        inputGroup.add(new InputGroupAddon("@"));
        Input groupedInput = new Input("text");
        groupedInput.setPlaceholder("Input group");
        inputGroup.add(groupedInput);
        InputGroupButton inputGroupButton = new InputGroupButton();
        inputGroupButton.add(new Button("Go", Variant.PRIMARY));
        inputGroup.add(inputGroupButton);
        form.add(inputGroup);
        formCard.addBody(form);
        formColumn.add(formCard);
        forms.add(formColumn);
        container.add(forms);

        Row scripted = new Row();
        scripted.setStyleName("row g-4 mt-1");
        Column collapseColumn = new Column(12);
        collapseColumn.setMediumSpan(4);
        Card collapseCard = new Card();
        collapseCard.setTitle("Collapse");
        Button toggleCollapse = new Button("Toggle collapse", Variant.PRIMARY);
        Collapse collapse = new Collapse();
        collapse.addStyleName("mt-3");
        Well collapseContent = new Well();
        collapseContent.add(new Paragraph("Collapse uses Bootstrap 5 JavaScript APIs."));
        collapse.add(collapseContent);
        toggleCollapse.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                collapse.toggle();
            }
        });
        collapseCard.addBody(toggleCollapse);
        collapseCard.addBody(collapse);
        collapseColumn.add(collapseCard);

        Column tooltipColumn = new Column(12);
        tooltipColumn.setMediumSpan(4);
        Card tooltipCard = new Card();
        tooltipCard.setTitle("Tooltip and popover");
        Tooltip tooltip = new Tooltip(new Button("Tooltip", Variant.SECONDARY), "Bootstrap 5 tooltip");
        tooltip.init();
        Popover popover = new Popover(new Button("Popover", Variant.SECONDARY), "Popover title", "Bootstrap 5 popover content");
        popover.addStyleName("ms-2");
        popover.init();
        tooltipCard.addBody(tooltip);
        tooltipCard.addBody(popover);
        tooltipColumn.add(tooltipCard);

        Column carouselColumn = new Column(12);
        carouselColumn.setMediumSpan(4);
        Card carouselCard = new Card();
        carouselCard.setTitle("Carousel");
        String carouselId = "showcaseCarousel";
        Carousel carousel = new Carousel();
        carousel.getElement().setId(carouselId);
        CarouselIndicators indicators = new CarouselIndicators();
        CarouselIndicator firstIndicator = new CarouselIndicator(carouselId, 0);
        firstIndicator.setActive(true);
        indicators.addIndicator(firstIndicator);
        indicators.addIndicator(new CarouselIndicator(carouselId, 1));
        carousel.insert(indicators, 0);
        CarouselSlide firstSlide = new CarouselSlide(new HTML("<div class=\"d-flex align-items-center justify-content-center text-bg-primary rounded\" style=\"height: 9rem;\">First slide</div>"));
        firstSlide.setActive(true);
        CarouselCaption caption = new CarouselCaption();
        caption.add(new Heading(5, "First slide"));
        firstSlide.add(caption);
        carousel.addSlide(firstSlide);
        carousel.addSlide(new CarouselSlide(new HTML("<div class=\"d-flex align-items-center justify-content-center text-bg-success rounded\" style=\"height: 9rem;\">Second slide</div>")));
        carousel.add(new CarouselControl(carouselId, true));
        carousel.add(new CarouselControl(carouselId, false));
        carouselCard.addBody(carousel);
        carouselColumn.add(carouselCard);

        scripted.add(collapseColumn);
        scripted.add(tooltipColumn);
        scripted.add(carouselColumn);
        container.add(scripted);

        root.add(container);
    }
}
