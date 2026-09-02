package org.gwtbootstrap5.teavm.demo;

import org.gwtbootstrap5.teavm.ui.Alert;
import org.gwtbootstrap5.teavm.ui.Badge;
import org.gwtbootstrap5.teavm.ui.Button;
import org.gwtbootstrap5.teavm.ui.Card;
import org.gwtbootstrap5.teavm.ui.CardFooter;
import org.gwtbootstrap5.teavm.ui.CardHeader;
import org.gwtbootstrap5.teavm.ui.Column;
import org.gwtbootstrap5.teavm.ui.Container;
import org.gwtbootstrap5.teavm.ui.Heading;
import org.gwtbootstrap5.teavm.ui.Lead;
import org.gwtbootstrap5.teavm.ui.ListGroup;
import org.gwtbootstrap5.teavm.ui.ListGroupItem;
import org.gwtbootstrap5.teavm.ui.Mount;
import org.gwtbootstrap5.teavm.ui.Paragraph;
import org.gwtbootstrap5.teavm.ui.Row;
import org.gwtbootstrap5.teavm.ui.Variant;
import org.gwtbootstrap5.teavm.ui.Well;

/**
 * TeaVM compile smoke app for the widget backend prototype.
 */
public final class Bootstrap5SmokeApp {

    private Bootstrap5SmokeApp() {
    }

    public static void main(final String[] args) {
        final Container container = new Container();
        container.addStyleName("py-4");
        container.add(new Heading(1, "GWT Bootstrap 5 Modern TeaVM"));
        container.add(new Paragraph("TeaVM-rendered Bootstrap 5 widgets using the non-GWT DOM backend."));

        final Row row = new Row();
        row.add(Column.md(6).add(new Button("Primary action")));
        row.add(Column.md(6).add(new Paragraph("This is compiled by TeaVM, not the GWT compiler.")));
        container.add(row);

        final Card card = new Card();
        card.addStyleName("mt-4");
        card.addHeader(new CardHeader("Bootstrap 5 card"));
        card.addBody(new Heading(2, "Bootstrap 5-native TeaVM widgets"));
        card.addBody(new Paragraph("This page uses Bootstrap 5 resources and the org.gwtbootstrap5.teavm API."));
        card.addFooter(new CardFooter("Compiled by TeaVM"));
        container.add(card);

        final Row parity = new Row();
        parity.setStyleName("row g-4 mt-2");

        final Card feedback = new Card();
        feedback.addBody(new Alert("TeaVM alert using Bootstrap 5 alert classes.", Variant.INFO).setDismissible(true));
        feedback.addBody(new Badge("rounded badge", Variant.SUCCESS).setPill(true));
        parity.add(Column.md(6).add(feedback));

        final ListGroup listGroup = new ListGroup()
                .add(new ListGroupItem("Plain list group item"))
                .add(new ListGroupItem("Active list group item").setActive(true))
                .add(new ListGroupItem("Warning list group item").setVariant(Variant.WARNING));
        parity.add(Column.md(6).add(listGroup));

        final Well well = new Well();
        well.add(new Heading(3, "Well concept"));
        well.add(new Lead("Bootstrap 5 renders this through utility classes."));
        parity.add(Column.md(12).add(well));

        container.add(parity);

        Mount.toBody(container);
    }
}
