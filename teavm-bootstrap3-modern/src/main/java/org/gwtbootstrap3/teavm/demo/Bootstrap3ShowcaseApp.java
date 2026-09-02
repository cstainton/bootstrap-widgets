/*
 * #%L
 * GWT Bootstrap Modern
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
        Themes.register(BootswatchThemes.all("themes/"));
        Themes.restore(BootswatchThemes.byName("flatly", "themes/"));

        final Container container = new Container();
        container.add(page());
        RootPanel.get().add(container);
    }

    private static Row page() {
        final Row row = new Row();
        final Column column = new Column(ColumnSize.XS_12);

        final PageHeader header = new PageHeader();
        header.setText("GwtBootstrap3 on TeaVM");
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
