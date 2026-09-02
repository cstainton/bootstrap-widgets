package org.gwtbootstrap5.teavm.demo;

import org.gwtbootstrap5.teavm.ui.Button;
import org.gwtbootstrap5.teavm.ui.Card;
import org.gwtbootstrap5.teavm.ui.Column;
import org.gwtbootstrap5.teavm.ui.Container;
import org.gwtbootstrap5.teavm.ui.Heading;
import org.gwtbootstrap5.teavm.ui.Mount;
import org.gwtbootstrap5.teavm.ui.Paragraph;
import org.gwtbootstrap5.teavm.ui.Row;

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
        card.addBody(new Heading(2, "Bootstrap 5-native TeaVM widgets"));
        card.addBody(new Paragraph("This page uses Bootstrap 5 resources and the org.gwtbootstrap5.teavm API."));
        container.add(card);

        Mount.toBody(container);
    }
}
