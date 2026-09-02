package org.gwtbootstrap3.teavm.demo;

import org.gwtbootstrap3.teavm.ui.Button;
import org.gwtbootstrap3.teavm.ui.Column;
import org.gwtbootstrap3.teavm.ui.Container;
import org.gwtbootstrap3.teavm.ui.Heading;
import org.gwtbootstrap3.teavm.ui.Mount;
import org.gwtbootstrap3.teavm.ui.Paragraph;
import org.gwtbootstrap3.teavm.ui.Row;

/**
 * TeaVM compile smoke app for the widget backend prototype.
 */
public final class TeaVmSmokeApp {

    private TeaVmSmokeApp() {
    }

    public static void main(final String[] args) {
        final Container container = new Container();
        container.add(new Heading(1, "GWT Bootstrap Modern TeaVM"));
        container.add(new Paragraph("TeaVM-rendered Bootstrap widgets using the non-GWT DOM backend."));

        final Row row = new Row();
        row.add(Column.md(6).add(new Button("Primary action")));
        row.add(Column.md(6).add(new Paragraph("This is compiled by TeaVM, not the GWT compiler.")));
        container.add(row);

        Mount.toBody(container);
    }
}
