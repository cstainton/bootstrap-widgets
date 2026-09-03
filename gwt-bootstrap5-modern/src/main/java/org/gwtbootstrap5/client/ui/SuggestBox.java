/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2018 GwtBootstrap3
 * %%
 * Modified from the GwtBootstrap3 original for the Bootstrap 5 track of
 * GWT Bootstrap Modern: moved to the org.gwtbootstrap5 namespace and re-targeted
 * at Bootstrap 5 markup, class names and JavaScript APIs.
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
package org.gwtbootstrap5.client.ui;

import java.util.List;

import org.gwtbootstrap5.client.ui.base.HasAutoComplete;
import org.gwtbootstrap5.client.ui.base.HasId;
import org.gwtbootstrap5.client.ui.base.HasPlaceholder;
import org.gwtbootstrap5.client.ui.base.HasResponsiveness;
import org.gwtbootstrap5.client.ui.base.HasSize;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.base.mixin.BlankValidatorMixin;
import org.gwtbootstrap5.client.ui.base.mixin.ErrorHandlerMixin;
import org.gwtbootstrap5.client.ui.base.mixin.IdMixin;
import org.gwtbootstrap5.client.ui.constants.DeviceSize;
import org.gwtbootstrap5.client.ui.constants.InputSize;
import org.gwtbootstrap5.client.ui.constants.Styles;
import org.gwtbootstrap5.client.ui.form.error.ErrorHandler;
import org.gwtbootstrap5.client.ui.form.error.ErrorHandlerType;
import org.gwtbootstrap5.client.ui.form.error.HasErrorHandler;
import org.gwtbootstrap5.client.ui.form.validator.HasBlankValidator;
import org.gwtbootstrap5.client.ui.form.validator.HasValidators;
import org.gwtbootstrap5.client.ui.form.validator.ValidationChangedEvent.ValidationChangedHandler;
import org.gwtbootstrap5.client.ui.form.validator.Validator;

import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.ValueBoxBase;

/**
 * A {@link com.google.gwt.user.client.ui.SuggestBox} styled for Bootstrap 5.
 *
 * <p>The text box carries {@code form-control} and the suggestion popup is
 * rendered as a Bootstrap 5 {@code dropdown-menu} of {@code dropdown-item}
 * entries, rather than the Bootstrap 3 markup GwtBootstrap3 produced.</p>
 *
 * <p>Validation follows Bootstrap 5 as well: an invalid box gets
 * {@code is-invalid} and the message is shown in a sibling
 * {@code invalid-feedback} element, which {@link org.gwtbootstrap5.client.ui.form.error.DefaultErrorHandler}
 * creates if the surrounding {@link FormGroup} has no {@link HelpBlock} of its
 * own.</p>
 */
public class SuggestBox extends com.google.gwt.user.client.ui.SuggestBox implements HasId, HasResponsiveness,
        HasPlaceholder, HasAutoComplete, HasSize<InputSize>, HasEditorErrors<String>, HasErrorHandler,
        HasValidators<String>, HasBlankValidator<String> {

    /**
     * Renders the suggestion popup with the Bootstrap 5 dropdown classes.
     *
     * <p>GwtBootstrap3 positioned and sized the popup by hand from a resize
     * handler. Bootstrap 5 dropdowns need neither: the popup is styled as a
     * {@code dropdown-menu} and left to the browser.</p>
     */
    static class BootstrapSuggestionDisplay extends DefaultSuggestionDisplay {

        BootstrapSuggestionDisplay() {
            super();
            getPopupPanel().addStyleName(Styles.DROPDOWN_MENU);
            getPopupPanel().addStyleName("d-block");
            getPopupPanel().addStyleName("p-1");
        }

        @Override
        protected void showSuggestions(final com.google.gwt.user.client.ui.SuggestBox suggestBox,
                final java.util.Collection<? extends SuggestOracle.Suggestion> suggestions, final boolean isDisplayStringHTML,
                final boolean isAutoSelectEnabled, final SuggestionCallback callback) {
            super.showSuggestions(suggestBox, suggestions, isDisplayStringHTML, isAutoSelectEnabled, callback);
            final com.google.gwt.dom.client.Element popup = getPopupPanel().getElement();
            final com.google.gwt.dom.client.NodeList<com.google.gwt.dom.client.Element> items =
                    popup.getElementsByTagName("td");
            for (int i = 0; i < items.getLength(); i++) {
                items.getItem(i).addClassName("dropdown-item");
            }
            getPopupPanel().setWidth(suggestBox.getOffsetWidth() + "px");
        }
    }

    private final ErrorHandlerMixin<String> errorHandlerMixin = new ErrorHandlerMixin<String>(this);

    private final IdMixin<SuggestBox> idMixin = new IdMixin<SuggestBox>(this);

    private final BlankValidatorMixin<SuggestBox, String> validatorMixin =
            new BlankValidatorMixin<SuggestBox, String>(this, errorHandlerMixin.getErrorHandler());

    public SuggestBox() {
        this(new com.google.gwt.user.client.ui.MultiWordSuggestOracle());
    }

    /**
     * GWT's SuggestBox reaches for its box through getTextBox(), which casts to
     * com.google.gwt.user.client.ui.TextBoxBase. This library's TextBox descends
     * from the Bootstrap ValueBoxBase instead and would fail that cast, so the
     * default box is GWT's own, styled here with form-control.
     */
    public SuggestBox(final SuggestOracle oracle) {
        this(oracle, new com.google.gwt.user.client.ui.TextBox());
    }

    public SuggestBox(final SuggestOracle oracle, final ValueBoxBase<String> box) {
        this(oracle, box, new BootstrapSuggestionDisplay());
    }

    public SuggestBox(final SuggestOracle oracle, final ValueBoxBase<String> box,
            final SuggestionDisplay suggestDisplay) {
        super(oracle, box, suggestDisplay);
        // getTextBox() casts to com.google.gwt.user.client.ui.TextBoxBase, which
        // this library's TextBox does not extend -- it descends from the
        // Bootstrap ValueBoxBase instead. getValueBox() is the uncast accessor.
        getValueBox().addStyleName(Styles.FORM_CONTROL);
        addBlurHandler();
    }

    private void addBlurHandler() {
        getValueBox().addBlurHandler(new BlurHandler() {
            @Override
            public void onBlur(final BlurEvent event) {
                if (validatorMixin.getValidateOnBlur()) {
                    validate(true);
                }
            }
        });
    }

    // ---- identity, sizing, placeholder ------------------------------------

    @Override
    public void setId(final String id) {
        idMixin.setId(id);
    }

    @Override
    public String getId() {
        return idMixin.getId();
    }

    @Override
    public void setPlaceholder(final String placeholder) {
        getValueBox().getElement().setAttribute("placeholder", placeholder == null ? "" : placeholder);
    }

    @Override
    public String getPlaceholder() {
        return getValueBox().getElement().getAttribute("placeholder");
    }

    @Override
    public void setAutoComplete(final boolean autoComplete) {
        getValueBox().getElement().setAttribute("autocomplete", autoComplete ? "on" : "off");
    }

    @Override
    public String getAutoComplete() {
        return getValueBox().getElement().getAttribute("autocomplete");
    }

    @Override
    public void setSize(final InputSize size) {
        StyleHelper.addUniqueEnumStyleName(getValueBox(), InputSize.class, size == null ? InputSize.DEFAULT : size);
    }

    @Override
    public InputSize getSize() {
        return InputSize.fromStyleName(getValueBox().getStyleName());
    }

    @Override
    public void setVisibleOn(final DeviceSize deviceSize) {
        StyleHelper.setVisibleOn(this, deviceSize);
    }

    @Override
    public void setHiddenOn(final DeviceSize deviceSize) {
        StyleHelper.setHiddenOn(this, deviceSize);
    }

    /** Disables the underlying value box, as Bootstrap 5 expects on a form-control. */
    public void setEnabled(final boolean enabled) {
        getValueBox().setEnabled(enabled);
    }

    public boolean isEnabled() {
        return getValueBox().isEnabled();
    }

    // ---- validation --------------------------------------------------------

    @Override
    public void setErrorHandler(final ErrorHandler handler) {
        validatorMixin.setErrorHandler(handler);
        errorHandlerMixin.setErrorHandler(handler);
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return errorHandlerMixin.getErrorHandler();
    }

    @Override
    public void setErrorHandlerType(final ErrorHandlerType type) {
        validatorMixin.setErrorHandler(errorHandlerMixin.getErrorHandler());
        errorHandlerMixin.setErrorHandlerType(type);
    }

    @Override
    public ErrorHandlerType getErrorHandlerType() {
        return errorHandlerMixin.getErrorHandlerType();
    }

    @Override
    public void showErrors(final List<EditorError> errors) {
        errorHandlerMixin.showErrors(errors);
    }

    @Override
    public HandlerRegistration addValidationChangedHandler(final ValidationChangedHandler handler) {
        return validatorMixin.addValidationChangedHandler(handler);
    }

    @Override
    public void addValidator(final Validator<String> validator) {
        validatorMixin.addValidator(validator);
    }

    @Override
    public boolean removeValidator(final Validator<String> validator) {
        return validatorMixin.removeValidator(validator);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setValidators(final Validator<String>... validators) {
        validatorMixin.setValidators(validators);
    }

    @Override
    public boolean getValidateOnBlur() {
        return validatorMixin.getValidateOnBlur();
    }

    @Override
    public void setValidateOnBlur(final boolean validateOnBlur) {
        validatorMixin.setValidateOnBlur(validateOnBlur);
    }

    @Override
    public boolean getAllowBlank() {
        return validatorMixin.getAllowBlank();
    }

    @Override
    public void setAllowBlank(final boolean allowBlank) {
        validatorMixin.setAllowBlank(allowBlank);
    }

    @Override
    public void reset() {
        validatorMixin.reset();
    }

    @Override
    public boolean validate() {
        return validatorMixin.validate();
    }

    @Override
    public boolean validate(final boolean show) {
        return validatorMixin.validate(show);
    }
}
