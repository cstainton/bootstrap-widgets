package org.gwtbootstrap3.teavm.demo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Container;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.ColumnSize;
import org.gwtbootstrap3.client.ui.constants.HeadingSize;
import org.gwtbootstrap3.client.ui.html.Paragraph;

public final class GwtCompatSmokeApp implements EntryPoint {
    public static void main(final String[] args) {
        new GwtCompatSmokeApp().onModuleLoad();
    }

    @Override
    public void onModuleLoad() {
        final Container container = new Container();
        container.add(new Heading(HeadingSize.H1, "TeaVM Bootstrap Modern"));
        container.add(new Paragraph("This page is rendered with GWT-style widget APIs running on TeaVM."));

        final Row row = new Row();
        row.add(new Column(ColumnSize.MD_6, new Button("Primary action", event -> {
            final Label label = new Label("clicked");
            label.addStyleName("ms-2");
            container.add(label);
        })));
        row.add(new Column(ColumnSize.MD_6, new Anchor("GWT Bootstrap Modern", "https://github.com/cstainton/gwtbootstrap-modern")));
        container.add(row);

        final Button button = new Button("Secondary action");
        button.setType(ButtonType.PRIMARY);
        container.add(button);

        RootPanel.get().add(container);
    }
}
