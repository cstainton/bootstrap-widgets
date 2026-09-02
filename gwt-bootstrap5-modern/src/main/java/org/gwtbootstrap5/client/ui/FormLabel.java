package org.gwtbootstrap5.client.ui;

public class FormLabel extends com.google.gwt.user.client.ui.Label {

    private String text = "";
    private boolean html;
    private boolean showRequiredIndicator;

    public FormLabel() {
        addStyleName("form-label");
    }

    public FormLabel(String text) {
        this();
        setText(text);
    }

    @Override
    public void setText(String text) {
        this.text = text == null ? "" : text;
        this.html = false;
        render();
    }

    @Override
    public String getText() {
        return text;
    }

    public void setHTML(String html) {
        this.text = html == null ? "" : html;
        this.html = true;
        render();
    }

    public String getHTML() {
        return getElement().getInnerHTML();
    }

    public void setFor(String targetId) {
        if (targetId == null || targetId.isEmpty()) {
            getElement().removeAttribute("for");
        } else {
            getElement().setAttribute("for", targetId);
        }
    }

    public void setShowRequiredIndicator(boolean showRequiredIndicator) {
        this.showRequiredIndicator = showRequiredIndicator;
        render();
    }

    public boolean getShowRequiredIndicator() {
        return showRequiredIndicator;
    }

    private void render() {
        String value = html ? text : escape(text);
        if (showRequiredIndicator && !value.isEmpty()) {
            value += " <sup class=\"text-danger\">*</sup>";
        }
        getElement().setInnerHTML(value);
    }

    private String escape(String value) {
        return value == null ? "" : value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}
