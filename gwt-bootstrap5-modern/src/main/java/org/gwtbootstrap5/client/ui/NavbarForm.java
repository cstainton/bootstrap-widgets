package org.gwtbootstrap5.client.ui;

public class NavbarForm extends Form {

    public NavbarForm() {
        super();
        setStyleName("d-flex");
        getElement().setAttribute("role", "search");
    }
}
