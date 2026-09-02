package org.gwtbootstrap5.teavm.demo;

import org.gwtbootstrap5.teavm.ui.Alert;
import org.gwtbootstrap5.teavm.ui.Badge;
import org.gwtbootstrap5.teavm.ui.Button;
import org.gwtbootstrap5.teavm.ui.Card;
import org.gwtbootstrap5.teavm.ui.CardFooter;
import org.gwtbootstrap5.teavm.ui.CardHeader;
import org.gwtbootstrap5.teavm.ui.Column;
import org.gwtbootstrap5.teavm.ui.Container;
import org.gwtbootstrap5.teavm.ui.Divider;
import org.gwtbootstrap5.teavm.ui.DropDown;
import org.gwtbootstrap5.teavm.ui.DropDownItem;
import org.gwtbootstrap5.teavm.ui.Heading;
import org.gwtbootstrap5.teavm.ui.Lead;
import org.gwtbootstrap5.teavm.ui.ListGroup;
import org.gwtbootstrap5.teavm.ui.ListGroupItem;
import org.gwtbootstrap5.teavm.ui.Modal;
import org.gwtbootstrap5.teavm.ui.ModalFooter;
import org.gwtbootstrap5.teavm.ui.ModalHeader;
import org.gwtbootstrap5.teavm.ui.Mount;
import org.gwtbootstrap5.teavm.ui.Navbar;
import org.gwtbootstrap5.teavm.ui.NavbarBrand;
import org.gwtbootstrap5.teavm.ui.NavbarLink;
import org.gwtbootstrap5.teavm.ui.PageItem;
import org.gwtbootstrap5.teavm.ui.Pagination;
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
        final Navbar navbar = new Navbar();
        navbar.getContainer().add(new NavbarBrand("TeaVM Bootstrap 5", "#"));
        navbar.getNav().setEndAligned(true);
        navbar.getNav().add(new NavbarLink("Smoke", "#").setActive(true));
        Mount.toBody(navbar);

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

        final Row interactive = new Row();
        interactive.setStyleName("row g-4 mt-2");

        final Modal modal = new Modal();
        modal.addHeader(new ModalHeader("TeaVM Bootstrap 5 modal"));
        modal.addToBody(new Paragraph("Modal uses Bootstrap 5's JavaScript API through TeaVM JSO."));
        final ModalFooter modalFooter = new ModalFooter();
        modalFooter.add(new Button("Close").setButtonStyle("btn-secondary").setAttribute("data-bs-dismiss", "modal"));
        modal.addFooter(modalFooter);

        final Card modalCard = new Card();
        modalCard.addBody(new Heading(3, "Modal"));
        final Button showModal = new Button("Open TeaVM modal");
        showModal.onClick(() -> modal.show());
        modalCard.addBody(showModal);
        interactive.add(Column.md(4).add(modalCard));

        final DropDown dropDown = new DropDown("TeaVM actions")
                .addItem(new DropDownItem("First action", "#"))
                .addMenuWidget(new Divider())
                .addItem(new DropDownItem("Disabled action", "#").setDisabled(true));
        final Card dropdownCard = new Card();
        dropdownCard.addBody(new Heading(3, "Dropdown"));
        dropdownCard.addBody(dropDown);
        interactive.add(Column.md(4).add(dropdownCard));

        final Pagination pagination = new Pagination()
                .add(new PageItem("Previous", "#").setDisabled(true))
                .add(new PageItem("1", "#"))
                .add(new PageItem("2", "#").setActive(true))
                .add(new PageItem("Next", "#"));
        final Card paginationCard = new Card();
        paginationCard.addBody(new Heading(3, "Pagination"));
        paginationCard.addBody(pagination);
        interactive.add(Column.md(4).add(paginationCard));

        container.add(interactive);
        container.add(modal);

        Mount.toBody(container);
    }
}
