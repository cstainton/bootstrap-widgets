/*
 * #%L
 * GWT Bootstrap
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
package org.gwtbootstrap3.demo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import org.gwtbootstrap3.demo.client.application.ApplicationView;
import org.gwtbootstrap3.demo.client.place.NameTokens;

/**
 * The showcase, routed on the history token.
 *
 * <p>This replaces GWT Platform's MVP wiring. Every page here was a presenter and a view:
 * fifty-five presenters that between them held nothing but a name token and a proxy
 * declaration, and the views that hold the actual content. The views remain, as ordinary
 * widgets built from their UiBinder templates; the presenters are this switch.</p>
 *
 * <p>Dropping the framework was not only about boilerplate. GWT Platform reaches its
 * proxies through deferred binding, as GIN does for injection, and TeaVM has no
 * equivalent, so no application using either can be compiled for that backend. Without
 * them the showcase is ordinary widget code, which both compilers understand.</p>
 */
public class GwtBootstrap3DemoEntryPoint implements EntryPoint {

    private ApplicationView shell;

    @Override
    public void onModuleLoad() {
        ScriptInjector.fromString(GwtBootstrap3DemoClientBundle.INSTANCE.prettify().getText())
                .setWindow(ScriptInjector.TOP_WINDOW)
                .inject();

        shell = new ApplicationView();
        RootPanel.get().add(shell);

        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(final ValueChangeEvent<String> event) {
                show(event.getValue());
            }
        });
        show(History.getToken());
    }

    private void show(final String rawToken) {
        final String token = rawToken == null || rawToken.isEmpty() ? NameTokens.HOME : rawToken;
        shell.setContent(pageFor(token));
        // What the framework's navigation handler did on every page change.
        Window.scrollTo(0, 0);
        shell.getNavbarCollapse().hide();
    }

    private Widget pageFor(final String token) {
        switch (token) {
            case NameTokens.AFFIX:
                return new org.gwtbootstrap3.demo.client.application.javascript.AffixView();
            case NameTokens.ALERTS:
                return new org.gwtbootstrap3.demo.client.application.components.AlertView();
            case NameTokens.ANIMATE:
                return new org.gwtbootstrap3.demo.client.application.extras.AnimateView();
            case NameTokens.BADGES:
                return new org.gwtbootstrap3.demo.client.application.components.BadgeView();
            case NameTokens.BOOTBOX:
                return new org.gwtbootstrap3.demo.client.application.extras.BootboxJSView();
            case NameTokens.BREADCRUMBS:
                return new org.gwtbootstrap3.demo.client.application.components.BreadcrumbView();
            case NameTokens.BUTTON_DROPDOWNS:
                return new org.gwtbootstrap3.demo.client.application.components.ButtonDropdownView();
            case NameTokens.BUTTON_GROUPS:
                return new org.gwtbootstrap3.demo.client.application.components.ButtonGroupView();
            case NameTokens.BUTTONS:
                return new org.gwtbootstrap3.demo.client.application.css.ButtonsView();
            case NameTokens.CARD:
                return new org.gwtbootstrap3.demo.client.application.extras.CardView();
            case NameTokens.CAROUSEL:
                return new org.gwtbootstrap3.demo.client.application.javascript.CarouselView();
            case NameTokens.CODE:
                return new org.gwtbootstrap3.demo.client.application.css.CodeView();
            case NameTokens.COLLAPSE:
                return new org.gwtbootstrap3.demo.client.application.javascript.CollapseView();
            case NameTokens.DATE_PICKER:
                return new org.gwtbootstrap3.demo.client.application.extras.DatePickerView();
            case NameTokens.DATETIME_PICKER:
                return new org.gwtbootstrap3.demo.client.application.extras.DateTimePickerView();
            case NameTokens.DROPDOWNS:
                return new org.gwtbootstrap3.demo.client.application.components.DropdownView();
            case NameTokens.FORMS:
                return new org.gwtbootstrap3.demo.client.application.css.FormsView();
            case NameTokens.FORMS_WITH_VALIDATION:
                return new org.gwtbootstrap3.demo.client.application.css.validation.FormsWithValidationView();
            case NameTokens.FULL_CALENDAR:
                return new org.gwtbootstrap3.demo.client.application.extras.FullCalendarView();
            case NameTokens.GALLERY:
                return new org.gwtbootstrap3.demo.client.application.extras.GalleryView();
            case NameTokens.GRID_SYSTEM:
                return new org.gwtbootstrap3.demo.client.application.css.GridSystemView();
            case NameTokens.HOME:
                return new org.gwtbootstrap3.demo.client.application.general.HomeView();
            case NameTokens.ICONS:
                return new org.gwtbootstrap3.demo.client.application.components.IconView();
            case NameTokens.IMAGES:
                return new org.gwtbootstrap3.demo.client.application.css.ImagesView();
            case NameTokens.INPUT_GROUPS:
                return new org.gwtbootstrap3.demo.client.application.components.InputGroupView();
            case NameTokens.JUMBOTRON:
                return new org.gwtbootstrap3.demo.client.application.components.JumbotronView();
            case NameTokens.LABELS:
                return new org.gwtbootstrap3.demo.client.application.components.LabelView();
            case NameTokens.LIST_GROUP:
                return new org.gwtbootstrap3.demo.client.application.components.ListGroupView();
            case NameTokens.MARKDOWN:
                return new org.gwtbootstrap3.demo.client.application.extras.MarkdownView();
            case NameTokens.MEDIA_OBJECTS:
                return new org.gwtbootstrap3.demo.client.application.components.MediaObjectView();
            case NameTokens.MODALS:
                return new org.gwtbootstrap3.demo.client.application.javascript.ModalView();
            case NameTokens.NAVBAR:
                return new org.gwtbootstrap3.demo.client.application.components.NavbarView();
            case NameTokens.NAVS:
                return new org.gwtbootstrap3.demo.client.application.components.NavView();
            case NameTokens.NOTIFY:
                return new org.gwtbootstrap3.demo.client.application.extras.NotifyView();
            case NameTokens.OFFLINE:
                return new org.gwtbootstrap3.demo.client.application.extras.OfflineView();
            case NameTokens.PAGE_HEADER:
                return new org.gwtbootstrap3.demo.client.application.components.PageHeaderView();
            case NameTokens.PAGINATION:
                return new org.gwtbootstrap3.demo.client.application.components.PaginationView();
            case NameTokens.PANELS:
                return new org.gwtbootstrap3.demo.client.application.components.PanelView();
            case NameTokens.POPOVER:
                return new org.gwtbootstrap3.demo.client.application.javascript.PopoverView();
            case NameTokens.PROGRESS_BARS:
                return new org.gwtbootstrap3.demo.client.application.components.ProgressBarView();
            case NameTokens.RESPONSIVE_UTILITIES:
                return new org.gwtbootstrap3.demo.client.application.css.ResponsiveUtilitiesView();
            case NameTokens.SELECT:
                return new org.gwtbootstrap3.demo.client.application.extras.BootstrapSelectView();
            case NameTokens.SETUP:
                return new org.gwtbootstrap3.demo.client.application.general.SetupView();
            case NameTokens.SLIDER:
                return new org.gwtbootstrap3.demo.client.application.extras.SliderView();
            case NameTokens.SUGGEST_BOX:
                return new org.gwtbootstrap3.demo.client.application.components.SuggestBoxView();
            case NameTokens.SUMMERNOTE:
                return new org.gwtbootstrap3.demo.client.application.extras.SummernoteView();
            case NameTokens.TABLES:
                return new org.gwtbootstrap3.demo.client.application.css.TablesView();
            case NameTokens.TABS:
                return new org.gwtbootstrap3.demo.client.application.javascript.TabView();
            case NameTokens.TAGSINPUT:
                return new org.gwtbootstrap3.demo.client.application.extras.TagsInputView();
            case NameTokens.THUMBNAILS:
                return new org.gwtbootstrap3.demo.client.application.components.ThumbnailView();
            case NameTokens.TOGGLESWITCH:
                return new org.gwtbootstrap3.demo.client.application.extras.ToggleSwitchView();
            case NameTokens.TOOLTIPS:
                return new org.gwtbootstrap3.demo.client.application.javascript.TooltipView();
            case NameTokens.TYPEAHEAD:
                return new org.gwtbootstrap3.demo.client.application.extras.TypeaheadView();
            case NameTokens.TYPOGRAPHY:
                return new org.gwtbootstrap3.demo.client.application.css.TypographyView();
            case NameTokens.WELLS:
                return new org.gwtbootstrap3.demo.client.application.components.WellView();
            default:
                GWT.log("No page for history token '" + token + "'; showing home");
                return new org.gwtbootstrap3.demo.client.application.general.HomeView();
        }
    }
}
