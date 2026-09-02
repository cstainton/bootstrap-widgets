package org.gwtbootstrap5.client.ui;

/** A semantic HTML label styled with Bootstrap 5's form-label class. */
public class FormLabel extends ElementPanel {

    private boolean showRequiredIndicator;
    private String text = "";
    private boolean html;

    public FormLabel() {
        super("label");
        addStyleName("form-label");
    }

    public FormLabel(String text) {
        this();
        setText(text);
    }

    @Override
    public void setText(String text) {
        this.text = text == null ? "" : text;
        html = false;
        render();
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setHTML(String html) {
        this.text = html == null ? "" : html;
        this.html = true;
        render();
    }

    @Override
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
        return value == null ? "" : value.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }
}
