/*
 * #%L
 * GWT Bootstrap
 * %%
 * Copyright (C) 2026 Carl Stainton
 * %%
 * Reimplements, over TeaVM's JSO libraries, part of the GWT client API. Class,
 * method and package names follow GWT (https://github.com/gwtproject/gwt),
 * Copyright (C) The GWT Project Authors, licensed under the Apache License,
 * Version 2.0. No GWT source is included.
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
package org.gwtbootstrap3.teavm.demo;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Alert;
import org.gwtbootstrap3.client.ui.AnchorButton;
import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Breadcrumbs;
import org.gwtbootstrap3.client.ui.DropDownMenu;
import org.gwtbootstrap3.client.ui.Icon;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.InputGroup;
import org.gwtbootstrap3.client.ui.InputGroupAddon;
import org.gwtbootstrap3.client.ui.InputGroupButton;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.ModalBody;
import org.gwtbootstrap3.client.ui.ModalFooter;
import org.gwtbootstrap3.client.ui.NavTabs;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.Popover;
import org.gwtbootstrap3.client.ui.TabContent;
import org.gwtbootstrap3.client.ui.TabListItem;
import org.gwtbootstrap3.client.ui.TabPane;
import org.gwtbootstrap3.client.ui.TabPanel;
import org.gwtbootstrap3.client.ui.Tooltip;
import org.gwtbootstrap3.client.ui.constants.IconSize;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Placement;
import org.gwtbootstrap3.client.ui.constants.PaginationSize;
import org.gwtbootstrap3.client.ui.constants.Toggle;
import org.gwtbootstrap3.client.ui.Badge;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.ButtonGroup;
import org.gwtbootstrap3.client.ui.CheckBox;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Container;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.ListGroupItem;
import org.gwtbootstrap3.client.ui.PageHeader;
import org.gwtbootstrap3.client.ui.Panel;
import org.gwtbootstrap3.client.ui.PanelBody;
import org.gwtbootstrap3.client.ui.PanelFooter;
import org.gwtbootstrap3.client.ui.PanelHeader;
import org.gwtbootstrap3.client.ui.Progress;
import org.gwtbootstrap3.client.ui.ProgressBar;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.Well;
import org.gwtbootstrap3.client.ui.constants.AlertType;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.ColumnSize;
import org.gwtbootstrap3.client.ui.constants.HeadingSize;
import org.gwtbootstrap3.client.ui.constants.LabelType;
import org.gwtbootstrap3.client.ui.constants.ProgressBarType;
import org.gwtbootstrap3.client.ui.html.Paragraph;
import org.gwtbootstrap3.client.ui.theme.StandardThemes;
import org.gwtbootstrap3.client.ui.theme.Themes;
import org.gwtbootstrap3.themes.client.BootswatchThemes;
import org.gwtbootstrap3.client.ui.html.Text;

/**
 * Showcase for the TeaVM build of the Bootstrap 3 widgets.
 *
 * <p>Laid out the way the GwtBootstrap3 demo lays its pages out: a row holding a
 * full-width column, a page header, then one panel per section with the live example in
 * the panel body and the code that produced it in the footer.</p>
 */
public final class Bootstrap3ShowcaseApp {

    public static void main(final String[] args) {
        // the host page serves the theme stylesheets from themes/
        Themes.register(StandardThemes.all("bootstrap/"));
        Themes.register(BootswatchThemes.all("themes/"));
        Themes.restore(StandardThemes.bootstrap("bootstrap/"));

        final Container container = new Container();
        container.add(page());
        RootPanel.get().add(container);
    }

    private static Row page() {
        final Row row = new Row();
        final Column column = new Column(ColumnSize.XS_12);

        final PageHeader header = new PageHeader();
        header.setText("Bootstrap 3 on TeaVM");
        header.setSubText("the same widgets, compiled to JavaScript by TeaVM");
        column.add(header);

        column.add(panel("Theme", themeSection(),
                "Themes.register(BootswatchThemes.all(\"themes/\"));\n"
                + "Themes.restore(BootswatchThemes.byName(\"flatly\", \"themes/\"));\n"
                + "Themes.apply(\"darkly\");"));
        column.add(panel("Buttons", buttons(),
                "new Button(\"Primary\", ButtonType.PRIMARY);\n"
                + "ButtonGroup group = new ButtonGroup();"));
        column.add(panel("Alerts", alerts(),
                "new Alert(\"...\", AlertType.SUCCESS);\n"
                + "alert.setDismissable(true);"));
        column.add(panel("Labels and badges", labels(),
                "new Label(\"Primary\", LabelType.PRIMARY);\n"
                + "new Badge(\"42\");"));
        column.add(panel("Forms", forms(),
                "TextBox box = new TextBox();\n"
                + "box.setPlaceholder(\"...\");\n"
                + "new CheckBox(\"Clicking the label toggles this\");"));
        column.add(panel("Cell table", cellTable(),
                "CellTable<Person> table = new CellTable<>(5);\n"
                + "table.addColumn(new TextColumn<Person>() { ... }, \"Name\");\n"
                + "ListDataProvider<Person> data = new ListDataProvider<>(people);\n"
                + "data.addDataDisplay(table);"));
        column.add(panel("Cell list and cell tree", cellListAndTree(),
                "CellList<String> list = new CellList<>(new TextCell());\n"
                + "CellTree tree = new CellTree(model, \"root\");"));
        column.add(panel("Progress and list groups", status(),
                "Progress progress = new Progress();\n"
                + "progress.add(new ProgressBar(60));\n"
                + "new ListGroup();"));
        column.add(panel("Icons", icons(),
                "new Icon(IconType.GEAR);\n"
                + "icon.setSize(IconSize.LARGE);\n"
                + "icon.setSpin(true);"));
        column.add(panel("Input groups", inputGroups(),
                "InputGroup group = new InputGroup();\n"
                + "group.add(new InputGroupAddon(\"@\"));\n"
                + "group.add(new InputGroupButton(new Button(\"Go\")));"));
        column.add(panel("Tabs", tabs(),
                "TabPanel tabPanel = new TabPanel();\n"
                + "tabPanel.getTabs().add(new TabListItem(\"First\"));\n"
                + "tabPanel.getTabContent().add(pane);"));
        column.add(panel("Dropdowns", dropdowns(),
                "ButtonGroup group = new ButtonGroup();\n"
                + "AnchorButton toggle = new AnchorButton();\n"
                + "toggle.setDataToggle(Toggle.DROPDOWN);\n"
                + "group.add(new DropDownMenu());"));
        column.add(panel("Pagination and breadcrumbs", navigation(),
                "Pagination pagination = new Pagination();\n"
                + "pagination.setPaginationSize(PaginationSize.SMALL);\n"
                + "new Breadcrumbs();"));
        column.add(panel("Tooltips and popovers", overlays(),
                "new Tooltip(button, \"Tooltip text\");\n"
                + "new Popover(button, \"Title\", \"Content\");\n"
                + "// Bootstrap 3 plugins, reached through jQuery"));
        column.add(panel("Modals", modals(),
                "Modal modal = new Modal();\n"
                + "modal.setClosable(true);\n"
                + "modal.show();"));

        row.add(column);
        return row;
    }

    /** A row in the cell table demo. */
    private static final class Person {

        private final String name;
        private final String role;

        Person(final String name, final String role) {
            this.name = name;
            this.role = role;
        }
    }

    private static Widget cellTable() {
        final org.gwtbootstrap3.client.ui.gwt.CellTable<Person> table =
                new org.gwtbootstrap3.client.ui.gwt.CellTable<>(5);
        table.setStriped(true);
        table.setBordered(true);

        final com.google.gwt.user.cellview.client.TextColumn<Person> nameColumn =
                new com.google.gwt.user.cellview.client.TextColumn<Person>() {
                    @Override
                    public String getValue(final Person person) {
                        return person.name;
                    }
                };
        nameColumn.setSortable(true);
        table.addColumn(nameColumn, "Name");

        table.addColumn(new com.google.gwt.user.cellview.client.TextColumn<Person>() {
            @Override
            public String getValue(final Person person) {
                return person.role;
            }
        }, "Role");

        final com.google.gwt.user.cellview.client.Column<Person, String> action =
                new com.google.gwt.user.cellview.client.Column<Person, String>(
                        new com.google.gwt.cell.client.ButtonCell()) {
                    @Override
                    public String getValue(final Person person) {
                        return "Select";
                    }
                };
        action.setFieldUpdater((index, person, value) ->
                selection.setText("Selected: " + person.name + " (" + person.role + ")"));
        table.addColumn(action, "Action");

        final java.util.List<Person> people = new java.util.ArrayList<>();
        people.add(new Person("Ada Lovelace", "Analyst"));
        people.add(new Person("Grace Hopper", "Engineer"));
        people.add(new Person("Alan Turing", "Researcher"));

        final com.google.gwt.view.client.ListDataProvider<Person> data =
                new com.google.gwt.view.client.ListDataProvider<>(people);
        data.addDataDisplay(table);

        // sorting the underlying list and refreshing is the ListHandler pattern
        final com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler<Person> sortHandler =
                new com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler<>(
                        data.getList());
        sortHandler.setComparator(nameColumn,
                (a, b) -> a.name.compareTo(b.name));
        table.addColumnSortHandler(sortHandler);
        table.addColumnSortHandler(event -> data.refresh());

        final Well well = new Well();
        well.add(table);
        well.add(selection);
        return well;
    }

    private static final Paragraph selection = new Paragraph();

    private static Widget cellListAndTree() {
        final Well well = new Well();

        final com.google.gwt.user.cellview.client.CellList<String> list =
                new com.google.gwt.user.cellview.client.CellList<>(
                        new com.google.gwt.cell.client.TextCell());
        final java.util.List<String> items = new java.util.ArrayList<>();
        items.add("Analysis");
        items.add("Engineering");
        items.add("Research");
        final com.google.gwt.view.client.ListDataProvider<String> listData =
                new com.google.gwt.view.client.ListDataProvider<>(items);
        listData.addDataDisplay(list);

        final com.google.gwt.user.cellview.client.CellTree tree =
                new com.google.gwt.user.cellview.client.CellTree(new DepartmentModel(), "root");

        well.add(new Heading(HeadingSize.H4, "CellList"));
        well.add(list);
        well.add(new Heading(HeadingSize.H4, "CellTree"));
        well.add(tree);
        return well;
    }

    /** Two-level tree: departments, each with people. */
    private static final class DepartmentModel
            implements com.google.gwt.user.cellview.client.TreeViewModel {

        @Override
        public <T> NodeInfo<?> getNodeInfo(final T value) {
            final java.util.List<String> children = new java.util.ArrayList<>();
            if ("root".equals(value)) {
                children.add("Analysis");
                children.add("Engineering");
            } else if ("Analysis".equals(value)) {
                children.add("Ada Lovelace");
            } else if ("Engineering".equals(value)) {
                children.add("Grace Hopper");
                children.add("Alan Turing");
            } else {
                return null;
            }
            return new DefaultNodeInfo<>(
                    new com.google.gwt.view.client.ListDataProvider<>(children),
                    new com.google.gwt.cell.client.TextCell());
        }

        @Override
        public boolean isLeaf(final Object value) {
            return value != null && !"root".equals(value)
                    && !"Analysis".equals(value) && !"Engineering".equals(value);
        }
    }

    /** One demo section: heading, live example, and the code behind it. */
    private static Widget icons() {
        final Well well = new Well();
        final Paragraph line = new Paragraph();
        for (final IconType type : new IconType[] {IconType.HOME, IconType.HEART, IconType.STAR,
                IconType.GEAR, IconType.ENVELOPE}) {
            line.add(new Icon(type));
            line.add(new Text(" "));
        }
        final Icon large = new Icon(IconType.CAMERA);
        large.setSize(IconSize.LARGE);
        line.add(large);
        line.add(new Text(" "));
        final Icon spinner = new Icon(IconType.SPINNER);
        spinner.setSpin(true);
        line.add(spinner);
        well.add(line);
        return well;
    }

    private static Widget inputGroups() {
        final Well well = new Well();

        final InputGroup at = new InputGroup();
        final InputGroupAddon addon = new InputGroupAddon();
        addon.setText("@");
        at.add(addon);
        final Input username = new Input();
        username.setPlaceholder("Username");
        at.add(username);

        final InputGroup go = new InputGroup();
        final Input search = new Input();
        search.setPlaceholder("Search");
        go.add(search);
        final InputGroupButton goButton = new InputGroupButton();
        goButton.add(button("Go", ButtonType.PRIMARY));
        go.add(goButton);

        well.add(at);
        well.add(go);
        return well;
    }

    private static Widget tabs() {
        final TabPanel tabPanel = new TabPanel();

        final NavTabs navTabs = new NavTabs();
        final TabListItem first = new TabListItem("First");
        first.setDataTarget("#teavmTab1");
        first.setActive(true);
        final TabListItem second = new TabListItem("Second");
        second.setDataTarget("#teavmTab2");
        navTabs.add(first);
        navTabs.add(second);

        final TabContent content = new TabContent();
        content.add(pane("teavmTab1", "First tab content, shown by Bootstrap's tab plugin.", true));
        content.add(pane("teavmTab2", "Second tab content.", false));

        tabPanel.add(navTabs);
        tabPanel.add(content);
        return tabPanel;
    }

    private static TabPane pane(final String id, final String text, final boolean active) {
        final TabPane pane = new TabPane();
        pane.getElement().setId(id);
        pane.setActive(active);
        pane.add(new Paragraph(text));
        return pane;
    }

    private static Widget dropdowns() {
        final Well well = new Well();
        final ButtonGroup group = new ButtonGroup();
        final AnchorButton toggle = new AnchorButton();
        toggle.setText("Click to toggle");
        toggle.setDataToggle(Toggle.DROPDOWN);
        final DropDownMenu menu = new DropDownMenu();
        menu.add(new AnchorListItem("Action"));
        menu.add(new AnchorListItem("Another action"));
        group.add(toggle);
        group.add(menu);
        well.add(group);
        return well;
    }

    private static Widget navigation() {
        final Well well = new Well();

        final Pagination pagination = new Pagination();
        pagination.add(new AnchorListItem("Previous"));
        pagination.add(new AnchorListItem("1"));
        pagination.add(new AnchorListItem("2"));
        pagination.add(new AnchorListItem("Next"));

        final Pagination small = new Pagination();
        small.setPaginationSize(PaginationSize.SMALL);
        small.add(new AnchorListItem("1"));
        small.add(new AnchorListItem("2"));

        final Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new AnchorListItem("Home"));
        breadcrumbs.add(new AnchorListItem("Library"));
        breadcrumbs.add(new AnchorListItem("Data"));

        well.add(pagination);
        well.add(small);
        well.add(breadcrumbs);
        return well;
    }

    private static Widget overlays() {
        final Well well = new Well();
        final Paragraph line = new Paragraph();
        for (final Placement placement : new Placement[] {Placement.TOP, Placement.RIGHT,
                Placement.BOTTOM, Placement.LEFT}) {
            final Button target = button("Tooltip " + placement.name().toLowerCase(),
                    ButtonType.DEFAULT);
            final Tooltip tooltip = new Tooltip(target,
                    "Tooltip on " + placement.name().toLowerCase());
            tooltip.setPlacement(placement);
            line.add(tooltip);
            line.add(new Text(" "));
        }
        final Button popoverTarget = button("Popover", ButtonType.PRIMARY);
        final Popover popover = new Popover(popoverTarget, "A popover",
                "Its content, positioned by Bootstrap 3's popover plugin.");
        popover.setPlacement(Placement.TOP);
        line.add(popover);
        well.add(line);
        return well;
    }

    private static Widget modals() {
        final Well well = new Well();

        final Modal modal = new Modal();
        modal.setTitle("A Bootstrap 3 modal");
        modal.setClosable(true);
        modal.setFade(true);
        final ModalBody body = new ModalBody();
        body.add(new Paragraph("Shown through Bootstrap's modal plugin."));
        modal.add(body);
        final ModalFooter footer = new ModalFooter();
        final Button dismiss = button("Close", ButtonType.DEFAULT);
        dismiss.addClickHandler(event -> modal.hide());
        footer.add(dismiss);
        modal.add(footer);

        final Button open = button("Show modal", ButtonType.PRIMARY);
        open.addClickHandler(event -> modal.show());

        well.add(open);
        well.add(modal);
        return well;
    }

    private static Panel panel(final String title, final Widget example, final String code) {
        final Panel panel = new Panel();

        final PanelHeader header = new PanelHeader();
        header.add(new Heading(HeadingSize.H3, title));
        panel.add(header);

        final PanelBody body = new PanelBody();
        body.add(example);
        panel.add(body);

        final PanelFooter footer = new PanelFooter();
        final org.gwtbootstrap3.client.ui.Pre pre = new org.gwtbootstrap3.client.ui.Pre();
        pre.setText(code);
        footer.add(pre);
        panel.add(footer);

        return panel;
    }

    private static Widget themeSection() {
        final Well well = new Well();
        final ButtonGroup group = new ButtonGroup();
        for (final org.gwtbootstrap3.client.ui.theme.Theme theme : Themes.getThemes()) {
            final Button button = new Button(theme.getDisplayName());
            button.setType(theme.isDark() ? ButtonType.PRIMARY : ButtonType.DEFAULT);
            button.addClickHandler(event -> Themes.apply(theme));
            group.add(button);
        }
        well.add(group);
        well.add(themeStatus);
        Themes.addThemeChangeHandler(Bootstrap3ShowcaseApp::showActiveTheme);
        showActiveTheme(Themes.getCurrent());
        return well;
    }

    private static final Paragraph themeStatus = new Paragraph();

    private static void showActiveTheme(final org.gwtbootstrap3.client.ui.theme.Theme theme) {
        themeStatus.setText(theme == null ? "No theme applied"
                : "Active theme: " + theme.getDisplayName()
                        + (theme.isDark() ? " (dark)" : " (light)"));
    }

    private static Widget buttons() {
        final ButtonGroup group = new ButtonGroup();
        group.add(new Button("Default"));
        group.add(button("Primary", ButtonType.PRIMARY));
        group.add(button("Success", ButtonType.SUCCESS));
        group.add(button("Danger", ButtonType.DANGER));
        return group;
    }

    private static Button button(final String text, final ButtonType type) {
        final Button button = new Button(text);
        button.setType(type);
        return button;
    }

    private static Widget alerts() {
        final Well well = new Well();
        well.add(alert("Success - this one is dismissable.", AlertType.SUCCESS, true));
        well.add(alert("Info - plain.", AlertType.INFO, false));
        well.add(alert("Warning - plain.", AlertType.WARNING, false));
        return well;
    }

    private static Alert alert(final String text, final AlertType type, final boolean dismissable) {
        final Alert alert = new Alert(text);
        alert.setType(type);
        alert.setDismissable(dismissable);
        return alert;
    }

    private static Widget labels() {
        final Paragraph paragraph = new Paragraph();
        paragraph.add(label("Primary", LabelType.PRIMARY));
        paragraph.add(new Text(" "));
        paragraph.add(label("Success", LabelType.SUCCESS));
        paragraph.add(new Text(" "));
        paragraph.add(label("Danger", LabelType.DANGER));
        paragraph.add(new Text(" "));
        paragraph.add(new Badge("42"));
        return paragraph;
    }

    private static Label label(final String text, final LabelType type) {
        final Label label = new Label(text);
        label.setType(type);
        return label;
    }

    private static Widget forms() {
        final Well well = new Well();
        final TextBox box = new TextBox();
        box.setPlaceholder("A TextBox from the shared widget source");
        final CheckBox checkBox = new CheckBox("Clicking this label toggles the box");
        checkBox.addValueChangeHandler(event ->
                box.setValue("checkbox is now " + event.getValue()));
        well.add(box);
        well.add(checkBox);
        return well;
    }

    private static ListGroupItem listItem(final String text) {
        final ListGroupItem item = new ListGroupItem();
        item.setText(text);
        return item;
    }

    private static Widget status() {
        final Well well = new Well();

        final Progress progress = new Progress();
        final ProgressBar bar = new ProgressBar();
        bar.setPercent(60);
        bar.setType(ProgressBarType.SUCCESS);
        progress.add(bar);
        well.add(progress);

        final ListGroup listGroup = new ListGroup();
        listGroup.add(listItem("Widgets: org.gwtbootstrap3.client.ui"));
        listGroup.add(listItem("DOM and events: TeaVM JSO"));
        listGroup.add(listItem("Bootstrap plugins: reached through jQuery"));
        well.add(listGroup);

        return well;
    }
}
