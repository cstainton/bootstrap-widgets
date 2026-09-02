package org.gwtbootstrap5.teavm.demo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import org.gwtbootstrap5.teavm.ui.Button;
import org.gwtbootstrap5.teavm.ui.Card;
import org.gwtbootstrap5.teavm.ui.Column;
import org.gwtbootstrap5.teavm.ui.Container;
import org.gwtbootstrap5.teavm.ui.Heading;
import org.gwtbootstrap5.teavm.ui.Paragraph;
import org.gwtbootstrap5.teavm.ui.Row;

public final class GwtCompatBootstrap5SmokeApp implements EntryPoint {
    public static void main(final String[] args) {
        new GwtCompatBootstrap5SmokeApp().onModuleLoad();
    }

    @Override
    public void onModuleLoad() {
        final Container container = new Container();
        container.addStyleName("py-4");
        container.add(new Heading(1, "GWT Bootstrap 5 Modern TeaVM"));
        container.add(new Paragraph("This Bootstrap 5 TeaVM page uses the shared GWT compatibility layer for its entry point and root panel."));

        final Row row = new Row();
        row.add(Column.md(6).add(new Button("Primary action")));
        row.add(Column.md(6).add(new Paragraph("Version-specific widgets sit above the shared TeaVM GWT compatibility layer.")));
        container.add(row);

        final Card card = new Card();
        card.addStyleName("mt-4");
        card.addBody(new Heading(2, "Bootstrap 5-native widgets"));
        card.addBody(new Paragraph("The API remains Bootstrap 5-native under org.gwtbootstrap5.teavm."));
        container.add(card);

        RootPanel.get().add(container);
    }
}
