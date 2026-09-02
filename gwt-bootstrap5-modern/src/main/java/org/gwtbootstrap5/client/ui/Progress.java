package org.gwtbootstrap5.client.ui;

public class Progress extends ElementPanel {

    public Progress() {
        super("div");
        addStyleName("progress");
    }

    public void addBar(ProgressBar bar) {
        add(bar);
    }
}
