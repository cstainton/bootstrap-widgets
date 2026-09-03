/*
 * #%L
 * GWT Bootstrap Modern
 * %%
 * Copyright (C) 2026 Carl Stainton
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
package io.instanto.bootstrap5.teavm.demo;

import io.instanto.bootstrap5.client.Bootstrap5;
import io.instanto.bootstrap5.client.ui.Alert;
import io.instanto.bootstrap5.client.ui.Badge;
import io.instanto.bootstrap5.client.ui.Button;
import io.instanto.bootstrap5.client.ui.Card;
import io.instanto.bootstrap5.client.ui.CheckBox;
import io.instanto.bootstrap5.client.ui.Column;
import io.instanto.bootstrap5.client.ui.Container;
import io.instanto.bootstrap5.client.ui.Heading;
import io.instanto.bootstrap5.client.ui.ListGroup;
import io.instanto.bootstrap5.client.ui.ListGroupItem;
import io.instanto.bootstrap5.client.ui.Modal;
import io.instanto.bootstrap5.client.ui.Progress;
import io.instanto.bootstrap5.client.ui.ProgressBar;
import io.instanto.bootstrap5.client.ui.Row;
import io.instanto.bootstrap5.client.ui.TextBox;
import io.instanto.bootstrap5.client.ui.constants.ButtonType;
import io.instanto.bootstrap5.client.ui.form.validator.BlankValidator;
import io.instanto.bootstrap5.client.ui.form.validator.ValidationMessages;
import io.instanto.bootstrap5.client.ui.html.Paragraph;

/**
 * Smoke page for the TeaVM build, exercising layout, form, status and JavaScript-backed
 * widgets.
 */
public final class Bootstrap5SmokeApp {

    public static void main(final String[] args) {
        final Container container = new Container();
        container.addStyleName("py-4");

        container.add(new Heading(1, "Bootstrap 5 Modern on TeaVM"));
        container.add(new Paragraph(
                "Bootstrap 5 widgets from io.instanto.bootstrap5.client.ui, running on TeaVM."));

        container.add(buttonRow());
        container.add(formRow());
        container.add(statusCard());
        container.add(modalCard());

        Bootstrap5.mount(container);
    }

    private static Row buttonRow() {
        final Row row = new Row();
        row.addStyleName("g-3 mb-4");

        final Column buttons = new Column(12);
        buttons.setMediumSpan(6);
        buttons.add(new Button("Default"));
        buttons.add(new Button("Primary", ButtonType.PRIMARY));
        buttons.add(new Button("Success", ButtonType.SUCCESS));
        buttons.add(new Button("Danger", ButtonType.DANGER));

        final Column badges = new Column(12);
        badges.setMediumSpan(6);
        badges.add(new Badge("42"));
        badges.add(new Alert("Alerts, badges and buttons all render from the shared source."));

        row.add(buttons);
        row.add(badges);
        return row;
    }

    private static Row formRow() {
        final Row row = new Row();
        row.addStyleName("g-3 mb-4");

        final Column column = new Column(12);
        final TextBox textBox = new TextBox();
        textBox.setPlaceholder("A TextBox from the shared widget source");

        final CheckBox checkBox = new CheckBox("Clicking this label toggles the box");
        checkBox.addValueChangeHandler(event ->
                textBox.setValue("checkbox is now " + event.getValue()));

        final BlankValidator<String> blank = new BlankValidator<>();
        final Paragraph message = new Paragraph("Validator message: "
                + blank.getInvalidMessage(ValidationMessages.Keys.BLANK));

        column.add(textBox);
        column.add(checkBox);
        column.add(message);
        row.add(column);
        return row;
    }

    private static Card statusCard() {
        final Card card = new Card();
        card.addStyleName("mb-4");

        final Progress progress = new Progress();
        progress.add(new ProgressBar(60));

        final ListGroup listGroup = new ListGroup();
        listGroup.add(new ListGroupItem("Widgets: io.instanto.bootstrap5.client.ui"));
        listGroup.add(new ListGroupItem("DOM and events: TeaVM JSO"));
        listGroup.add(new ListGroupItem("Bootstrap JS: reached through BootstrapJs"));
        listGroup.add(new ListGroupItem("Messages: ResourceBundle, resolved at runtime"));

        card.add(new Heading(2, "Status"));
        card.add(progress);
        card.add(listGroup);
        return card;
    }

    private static Card modalCard() {
        final Card card = new Card();

        final Modal modal = new Modal();
        modal.setTitle("A Bootstrap 5 modal");
        modal.setFade(true);
        modal.addToBody(new Paragraph(
                "Shown through Bootstrap's own JavaScript, called via TeaVM @JSBody."));

        final Button open = new Button("Show modal", ButtonType.PRIMARY);
        open.addClickHandler(event -> modal.show());

        card.add(new Heading(2, "Bootstrap JavaScript"));
        card.add(open);
        card.add(modal);
        return card;
    }
}
