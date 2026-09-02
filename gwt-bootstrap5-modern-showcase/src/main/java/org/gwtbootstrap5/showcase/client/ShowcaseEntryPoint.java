package org.gwtbootstrap5.showcase.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import org.gwtbootstrap5.client.ui.Alert;
import org.gwtbootstrap5.client.ui.Anchor;
import org.gwtbootstrap5.client.ui.Badge;
import org.gwtbootstrap5.client.ui.Button;
import org.gwtbootstrap5.client.ui.Card;
import org.gwtbootstrap5.client.ui.Column;
import org.gwtbootstrap5.client.ui.Container;
import org.gwtbootstrap5.client.ui.Heading;
import org.gwtbootstrap5.client.ui.Label;
import org.gwtbootstrap5.client.ui.Lead;
import org.gwtbootstrap5.client.ui.ListGroup;
import org.gwtbootstrap5.client.ui.ListGroupItem;
import org.gwtbootstrap5.client.ui.Panel;
import org.gwtbootstrap5.client.ui.PanelBody;
import org.gwtbootstrap5.client.ui.PanelFooter;
import org.gwtbootstrap5.client.ui.PanelHeader;
import org.gwtbootstrap5.client.ui.Paragraph;
import org.gwtbootstrap5.client.ui.Row;
import org.gwtbootstrap5.client.ui.Variant;
import org.gwtbootstrap5.client.ui.Well;

public class ShowcaseEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        RootPanel root = RootPanel.get("showcase");
        if (root == null) {
            root = RootPanel.get();
        }

        root.add(new HTML("<nav class=\"navbar navbar-expand-lg bg-body-tertiary border-bottom\">"
                + "<div class=\"container\"><a class=\"navbar-brand fw-semibold\" href=\"#\">GWT Bootstrap 5 Modern</a>"
                + "<button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#mainNav\" aria-controls=\"mainNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"><span class=\"navbar-toggler-icon\"></span></button>"
                + "<div class=\"collapse navbar-collapse\" id=\"mainNav\"><ul class=\"navbar-nav ms-auto\"><li class=\"nav-item\"><a class=\"nav-link active\" href=\"#components\">Components</a></li><li class=\"nav-item\"><a class=\"nav-link\" href=\"../\">Bootstrap 3 showcase</a></li></ul></div>"
                + "</div></nav>"));

        Container container = new Container();
        container.addStyleName("py-5");
        container.add(new HTML("<section class=\"p-5 mb-4 rounded-3 bg-light\"><h1 class=\"display-5 fw-bold\">Bootstrap 5-native GWT widgets</h1><p class=\"lead\">This module is intentionally separate from the GwtBootstrap3 compatibility API. It uses Bootstrap 5 classes, data attributes, and templates directly.</p></section>"));

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
        root.add(container);
    }
}
