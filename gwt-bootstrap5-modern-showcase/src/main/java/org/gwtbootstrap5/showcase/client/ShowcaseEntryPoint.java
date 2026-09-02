package org.gwtbootstrap5.showcase.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import org.gwtbootstrap5.client.ui.Button;
import org.gwtbootstrap5.client.ui.Card;
import org.gwtbootstrap5.client.ui.Column;
import org.gwtbootstrap5.client.ui.Container;
import org.gwtbootstrap5.client.ui.Row;
import org.gwtbootstrap5.client.ui.Variant;

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
        root.add(container);
    }
}
