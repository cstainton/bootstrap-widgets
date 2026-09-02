package org.gwtbootstrap5.client.ui;

public class TabPanel extends ElementPanel {

    private final NavTabs tabs = new NavTabs();
    private final TabContent content = new TabContent();

    public TabPanel() {
        super("div");
        tabs.getElement().setAttribute("role", "tablist");
        add(tabs);
        add(content);
    }

    public NavTabs getTabs() {
        return tabs;
    }

    public TabContent getContent() {
        return content;
    }
}
