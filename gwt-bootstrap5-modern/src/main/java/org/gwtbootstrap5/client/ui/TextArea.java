package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.TextBoxBase;
import org.gwtbootstrap5.client.ui.constants.Styles;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.user.client.ui.RootPanel;

public class TextArea extends TextBoxBase {

    public static TextArea wrap(final Element element) {
        assert Document.get().getBody().isOrHasChild(element);

        final TextArea textArea = new TextArea(element);
        textArea.onAttach();
        RootPanel.detachOnWindowClose(textArea);
        return textArea;
    }

    public TextArea() {
        super(Document.get().createTextAreaElement());
        setStyleName(Styles.FORM_CONTROL);
    }

    public TextArea(String text) {
        this();
        setText(text);
    }

    protected TextArea(final Element element) {
        super(element.<Element>cast());
        TextAreaElement.as(element);
        element.addClassName(Styles.FORM_CONTROL);
    }

    public int getCharacterWidth() {
        return getTextAreaElement().getCols();
    }

    @Override
    public int getCursorPos() {
        return getImpl().getTextAreaCursorPos(getElement());
    }

    @Override
    public int getSelectionLength() {
        return getImpl().getTextAreaSelectionLength(getElement());
    }

    public int getVisibleLines() {
        return getTextAreaElement().getRows();
    }

    public void setCharacterWidth(final int width) {
        getTextAreaElement().setCols(width);
    }

    public void setVisibleLines(final int lines) {
        getTextAreaElement().setRows(lines);
    }

    private TextAreaElement getTextAreaElement() {
        return getElement().cast();
    }

    public void clear() {
        super.setValue(null);
    }
}
