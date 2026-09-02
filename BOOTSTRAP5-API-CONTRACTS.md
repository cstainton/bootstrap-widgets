# Bootstrap 5 API Contract Audit

Generated from the compiled Bootstrap 3 and Bootstrap 5 public APIs. Bootstrap 3 package names are normalized to `org.gwtbootstrap5` before comparison.

- Shared inventory: `126/126`
- Full normalized public contract: `36/126`
- Reduced or missing contract: `90/126`
- Widgets missing one or more assignable interfaces: `75`
- Distinct missing interface contracts: `94`
- Widgets missing one or more public API members: `90`

| Widget | Present | Contract | Missing interfaces | Missing API members |
|---|---:|---|---:|---:|
| `Abbreviation` | yes | Reduced | 1 (IsEditor) | 3 |
| `Affix` | yes | Full | 0 | 0 |
| `Alert` | yes | Reduced | 0 | 2 |
| `Anchor` | yes | Reduced | 3 (HasHref, HasTarget, HasTargetHistoryToken) | 9 |
| `AnchorButton` | yes | Reduced | 12 (HasActive, HasAllMouseHandlers, HasHref, HasMouseDownHandlers, HasMouseMoveHandlers, HasMouseOutHandlers, HasMouseOverHandlers, HasMouseUpHandlers, HasMouseWheelHandlers, HasSize, HasTargetHistoryToken, HasType) | 42 |
| `AnchorListItem` | yes | Full | 0 | 0 |
| `Badge` | yes | Full | 0 | 0 |
| `BlockQuote` | yes | Full | 0 | 0 |
| `BooleanRadioGroup` | yes | Reduced | 14 (Editor, HasBlankValidator, HasDataSpy, HasEditorErrors, HasErrorHandler, HasName, HasValidators, HasValue, HasValueChangeHandlers, InsertPanel, InsertPanel$ForIsWidget, LeafValueEditor, TakesValue, ValidationChangedEvent$HasValidationChangedHandlers) | 54 |
| `Breadcrumbs` | yes | Reduced | 0 | 2 |
| `Button` | yes | Reduced | 10 (HasActive, HasBadge, HasIcon, HasIconPosition, HasInlineStyle, HasWidgets, HasWidgets$ForIsWidget, IndexedPanel, IndexedPanel$ForIsWidget, Iterable) | 100 |
| `ButtonGroup` | yes | Reduced | 6 (HasDataSpy, HasJustified, HasName, HasSize, InsertPanel, InsertPanel$ForIsWidget) | 22 |
| `ButtonToolBar` | yes | Reduced | 3 (HasDataSpy, InsertPanel, InsertPanel$ForIsWidget) | 7 |
| `Caption` | yes | Full | 0 | 0 |
| `Carousel` | yes | Reduced | 0 | 9 |
| `CarouselCaption` | yes | Full | 0 | 0 |
| `CarouselControl` | yes | Reduced | 1 (HasHref) | 10 |
| `CarouselIndicator` | yes | Reduced | 1 (HasActive) | 2 |
| `CarouselIndicators` | yes | Reduced | 0 | 2 |
| `CarouselInner` | yes | Full | 0 | 0 |
| `CarouselSlide` | yes | Reduced | 1 (HasActive) | 3 |
| `CheckBox` | yes | Reduced | 46 (Focusable, HasAllDragAndDropHandlers, HasAllFocusHandlers, HasAllGestureHandlers, HasAllKeyHandlers, HasAllMouseHandlers, HasAllTouchHandlers, HasBlurHandlers, HasChangeHandlers, HasDirectionEstimator, HasDirectionalSafeHtml, HasDirectionalText, HasDragEndHandlers, HasDragEnterHandlers, HasDragHandlers, HasDragLeaveHandlers, HasDragOverHandlers, HasDragStartHandlers, HasDropHandlers, HasEnabled, HasFocus, HasFocusHandlers, HasGestureChangeHandlers, HasGestureEndHandlers, HasGestureStartHandlers, HasKeyDownHandlers, HasKeyPressHandlers, HasKeyUpHandlers, HasMouseDownHandlers, HasMouseMoveHandlers, HasMouseOutHandlers, HasMouseOverHandlers, HasMouseUpHandlers, HasMouseWheelHandlers, HasName, HasSafeHtml, HasTouchCancelHandlers, HasTouchEndHandlers, HasTouchMoveHandlers, HasTouchStartHandlers, HasWordWrap, IsEditor, SourcesClickEvents, SourcesFocusEvents, SourcesKeyboardEvents, SourcesMouseEvents) | 108 |
| `CheckBoxButton` | yes | Reduced | 46 (Focusable, HasAllDragAndDropHandlers, HasAllFocusHandlers, HasAllGestureHandlers, HasAllKeyHandlers, HasAllMouseHandlers, HasAllTouchHandlers, HasBlurHandlers, HasChangeHandlers, HasDirectionEstimator, HasDirectionalSafeHtml, HasDirectionalText, HasDragEndHandlers, HasDragEnterHandlers, HasDragHandlers, HasDragLeaveHandlers, HasDragOverHandlers, HasDragStartHandlers, HasDropHandlers, HasEnabled, HasFocus, HasFocusHandlers, HasGestureChangeHandlers, HasGestureEndHandlers, HasGestureStartHandlers, HasKeyDownHandlers, HasKeyPressHandlers, HasKeyUpHandlers, HasMouseDownHandlers, HasMouseMoveHandlers, HasMouseOutHandlers, HasMouseOverHandlers, HasMouseUpHandlers, HasMouseWheelHandlers, HasName, HasSafeHtml, HasTouchCancelHandlers, HasTouchEndHandlers, HasTouchMoveHandlers, HasTouchStartHandlers, HasWordWrap, IsEditor, SourcesClickEvents, SourcesFocusEvents, SourcesKeyboardEvents, SourcesMouseEvents) | 106 |
| `Code` | yes | Reduced | 1 (IsEditor) | 3 |
| `Collapse` | yes | Reduced | 0 | 5 |
| `Column` | yes | Full | 0 | 0 |
| `Container` | yes | Reduced | 4 (HasId, HasInlineStyle, HasPull, HasResponsiveness) | 30 |
| `Description` | yes | Reduced | 0 | 1 |
| `DescriptionData` | yes | Reduced | 2 (DescriptionComponent, IsEditor) | 3 |
| `DescriptionTitle` | yes | Reduced | 2 (DescriptionComponent, IsEditor) | 3 |
| `Divider` | yes | Full | 0 | 0 |
| `DoubleBox` | yes | Full | 0 | 0 |
| `DoubleRadioGroup` | yes | Reduced | 14 (Editor, HasBlankValidator, HasDataSpy, HasEditorErrors, HasErrorHandler, HasName, HasValidators, HasValue, HasValueChangeHandlers, InsertPanel, InsertPanel$ForIsWidget, LeafValueEditor, TakesValue, ValidationChangedEvent$HasValidationChangedHandlers) | 54 |
| `DropDown` | yes | Full | 0 | 0 |
| `DropDownHeader` | yes | Full | 0 | 0 |
| `DropDownMenu` | yes | Reduced | 0 | 4 |
| `FieldSet` | yes | Reduced | 1 (HasEnabled) | 4 |
| `Form` | yes | Reduced | 2 (FormPanelImplHost, HasType) | 27 |
| `FormControlStatic` | yes | Reduced | 1 (IsEditor) | 3 |
| `FormGroup` | yes | Full | 0 | 0 |
| `FormLabel` | yes | Reduced | 1 (IsEditor) | 3 |
| `Heading` | yes | Reduced | 3 (HasAlignment, HasEmphasis, HasSubText) | 15 |
| `HelpBlock` | yes | Reduced | 1 (IsEditor) | 6 |
| `Icon` | yes | Reduced | 0 | 4 |
| `IconStack` | yes | Reduced | 1 (HasSize) | 7 |
| `Image` | yes | Reduced | 2 (HasPull, HasResponsiveness) | 14 |
| `ImageAnchor` | yes | Reduced | 3 (HasHref, HasTarget, HasTargetHistoryToken) | 9 |
| `InlineCheckBox` | yes | Reduced | 46 (Focusable, HasAllDragAndDropHandlers, HasAllFocusHandlers, HasAllGestureHandlers, HasAllKeyHandlers, HasAllMouseHandlers, HasAllTouchHandlers, HasBlurHandlers, HasChangeHandlers, HasDirectionEstimator, HasDirectionalSafeHtml, HasDirectionalText, HasDragEndHandlers, HasDragEnterHandlers, HasDragHandlers, HasDragLeaveHandlers, HasDragOverHandlers, HasDragStartHandlers, HasDropHandlers, HasEnabled, HasFocus, HasFocusHandlers, HasGestureChangeHandlers, HasGestureEndHandlers, HasGestureStartHandlers, HasKeyDownHandlers, HasKeyPressHandlers, HasKeyUpHandlers, HasMouseDownHandlers, HasMouseMoveHandlers, HasMouseOutHandlers, HasMouseOverHandlers, HasMouseUpHandlers, HasMouseWheelHandlers, HasName, HasSafeHtml, HasTouchCancelHandlers, HasTouchEndHandlers, HasTouchMoveHandlers, HasTouchStartHandlers, HasWordWrap, IsEditor, SourcesClickEvents, SourcesFocusEvents, SourcesKeyboardEvents, SourcesMouseEvents) | 108 |
| `InlineHelpBlock` | yes | Reduced | 1 (IsEditor) | 6 |
| `InlineRadio` | yes | Reduced | 46 (Focusable, HasAllDragAndDropHandlers, HasAllFocusHandlers, HasAllGestureHandlers, HasAllKeyHandlers, HasAllMouseHandlers, HasAllTouchHandlers, HasBlurHandlers, HasChangeHandlers, HasDirectionEstimator, HasDirectionalSafeHtml, HasDirectionalText, HasDragEndHandlers, HasDragEnterHandlers, HasDragHandlers, HasDragLeaveHandlers, HasDragOverHandlers, HasDragStartHandlers, HasDropHandlers, HasEnabled, HasFocus, HasFocusHandlers, HasGestureChangeHandlers, HasGestureEndHandlers, HasGestureStartHandlers, HasKeyDownHandlers, HasKeyPressHandlers, HasKeyUpHandlers, HasMouseDownHandlers, HasMouseMoveHandlers, HasMouseOutHandlers, HasMouseOverHandlers, HasMouseUpHandlers, HasMouseWheelHandlers, HasName, HasSafeHtml, HasTouchCancelHandlers, HasTouchEndHandlers, HasTouchMoveHandlers, HasTouchStartHandlers, HasWordWrap, IsEditor, SourcesClickEvents, SourcesFocusEvents, SourcesKeyboardEvents, SourcesMouseEvents) | 109 |
| `Input` | yes | Full | 0 | 0 |
| `InputGroup` | yes | Reduced | 3 (HasDataSpy, InsertPanel, InsertPanel$ForIsWidget) | 7 |
| `InputGroupAddon` | yes | Reduced | 3 (HasBadge, HasIcon, HasIconPosition) | 50 |
| `InputGroupButton` | yes | Full | 0 | 0 |
| `IntegerBox` | yes | Full | 0 | 0 |
| `IntegerRadioGroup` | yes | Reduced | 14 (Editor, HasBlankValidator, HasDataSpy, HasEditorErrors, HasErrorHandler, HasName, HasValidators, HasValue, HasValueChangeHandlers, InsertPanel, InsertPanel$ForIsWidget, LeafValueEditor, TakesValue, ValidationChangedEvent$HasValidationChangedHandlers) | 54 |
| `IsClosable` | yes | Full | 0 | 0 |
| `Jumbotron` | yes | Full | 0 | 0 |
| `Label` | yes | Reduced | 8 (HasAllMouseHandlers, HasMouseDownHandlers, HasMouseMoveHandlers, HasMouseOutHandlers, HasMouseOverHandlers, HasMouseUpHandlers, HasMouseWheelHandlers, IsEditor) | 17 |
| `Lead` | yes | Full | 0 | 0 |
| `Legend` | yes | Reduced | 1 (IsEditor) | 3 |
| `LinkedGroup` | yes | Full | 0 | 0 |
| `LinkedGroupItem` | yes | Reduced | 3 (HasActive, HasHref, HasTargetHistoryToken) | 9 |
| `LinkedGroupItemText` | yes | Full | 0 | 0 |
| `ListBox` | yes | Reduced | 1 (HasId) | 4 |
| `ListDropDown` | yes | Reduced | 2 (HasActive, HasEnabled) | 8 |
| `ListGroup` | yes | Reduced | 0 | 4 |
| `ListGroupItem` | yes | Full | 0 | 0 |
| `ListItem` | yes | Full | 0 | 0 |
| `LongBox` | yes | Full | 0 | 0 |
| `MediaBody` | yes | Full | 0 | 0 |
| `MediaList` | yes | Reduced | 0 | 4 |
| `Modal` | yes | Reduced | 1 (IsClosable) | 7 |
| `ModalBody` | yes | Reduced | 4 (HasDataSpy, InsertPanel, InsertPanel$ForIsWidget, ModalComponent) | 7 |
| `ModalComponent` | yes | Full | 0 | 0 |
| `ModalFooter` | yes | Reduced | 4 (HasDataSpy, InsertPanel, InsertPanel$ForIsWidget, ModalComponent) | 7 |
| `ModalHeader` | yes | Reduced | 5 (HasDataSpy, InsertPanel, InsertPanel$ForIsWidget, IsClosable, ModalComponent) | 9 |
| `ModalSize` | yes | Reduced | 1 (Style$HasCssName) | 4 |
| `Nav` | yes | Reduced | 1 (HasJustified) | 8 |
| `NavPills` | yes | Reduced | 2 (HasJustified, HasStacked) | 12 |
| `NavTabs` | yes | Reduced | 1 (HasJustified) | 8 |
| `Navbar` | yes | Reduced | 1 (HasType) | 8 |
| `NavbarBrand` | yes | Reduced | 3 (HasHref, HasTarget, HasTargetHistoryToken) | 9 |
| `NavbarButton` | yes | Reduced | 12 (HasActive, HasBadge, HasHref, HasIcon, HasIconPosition, HasInlineStyle, HasTargetHistoryToken, HasWidgets, HasWidgets$ForIsWidget, IndexedPanel, IndexedPanel$ForIsWidget, Iterable) | 109 |
| `NavbarCollapse` | yes | Reduced | 3 (HasDataSpy, InsertPanel, InsertPanel$ForIsWidget) | 11 |
| `NavbarCollapseButton` | yes | Reduced | 1 (IsRenderable) | 8 |
| `NavbarForm` | yes | Reduced | 1 (FormPanelImplHost) | 20 |
| `NavbarHeader` | yes | Reduced | 3 (HasDataSpy, InsertPanel, InsertPanel$ForIsWidget) | 7 |
| `NavbarLink` | yes | Reduced | 3 (HasHref, HasTarget, HasTargetHistoryToken) | 9 |
| `NavbarNav` | yes | Reduced | 0 | 4 |
| `NavbarText` | yes | Full | 0 | 0 |
| `PageHeader` | yes | Reduced | 1 (HasSubText) | 4 |
| `Pager` | yes | Reduced | 0 | 4 |
| `Pagination` | yes | Reduced | 0 | 5 |
| `Panel` | yes | Full | 0 | 0 |
| `PanelBody` | yes | Full | 0 | 0 |
| `PanelCollapse` | yes | Reduced | 0 | 6 |
| `PanelFooter` | yes | Full | 0 | 0 |
| `PanelGroup` | yes | Full | 0 | 0 |
| `PanelHeader` | yes | Full | 0 | 0 |
| `Popover` | yes | Reduced | 3 (AcceptsOneWidget, HasHover, HasOneWidget) | 66 |
| `Pre` | yes | Reduced | 1 (IsEditor) | 4 |
| `Progress` | yes | Reduced | 1 (HasActive) | 2 |
| `ProgressBar` | yes | Full | 0 | 0 |
| `Radio` | yes | Reduced | 46 (Focusable, HasAllDragAndDropHandlers, HasAllFocusHandlers, HasAllGestureHandlers, HasAllKeyHandlers, HasAllMouseHandlers, HasAllTouchHandlers, HasBlurHandlers, HasChangeHandlers, HasDirectionEstimator, HasDirectionalSafeHtml, HasDirectionalText, HasDragEndHandlers, HasDragEnterHandlers, HasDragHandlers, HasDragLeaveHandlers, HasDragOverHandlers, HasDragStartHandlers, HasDropHandlers, HasEnabled, HasFocus, HasFocusHandlers, HasGestureChangeHandlers, HasGestureEndHandlers, HasGestureStartHandlers, HasKeyDownHandlers, HasKeyPressHandlers, HasKeyUpHandlers, HasMouseDownHandlers, HasMouseMoveHandlers, HasMouseOutHandlers, HasMouseOverHandlers, HasMouseUpHandlers, HasMouseWheelHandlers, HasName, HasSafeHtml, HasTouchCancelHandlers, HasTouchEndHandlers, HasTouchMoveHandlers, HasTouchStartHandlers, HasWordWrap, IsEditor, SourcesClickEvents, SourcesFocusEvents, SourcesKeyboardEvents, SourcesMouseEvents) | 108 |
| `RadioButton` | yes | Reduced | 51 (Focusable, HasActive, HasAllDragAndDropHandlers, HasAllFocusHandlers, HasAllGestureHandlers, HasAllKeyHandlers, HasAllMouseHandlers, HasAllTouchHandlers, HasBlurHandlers, HasChangeHandlers, HasDirectionEstimator, HasDirectionalSafeHtml, HasDirectionalText, HasDragEndHandlers, HasDragEnterHandlers, HasDragHandlers, HasDragLeaveHandlers, HasDragOverHandlers, HasDragStartHandlers, HasDropHandlers, HasEnabled, HasFocus, HasFocusHandlers, HasGestureChangeHandlers, HasGestureEndHandlers, HasGestureStartHandlers, HasIcon, HasIconPosition, HasKeyDownHandlers, HasKeyPressHandlers, HasKeyUpHandlers, HasMouseDownHandlers, HasMouseMoveHandlers, HasMouseOutHandlers, HasMouseOverHandlers, HasMouseUpHandlers, HasMouseWheelHandlers, HasName, HasSafeHtml, HasSize, HasTouchCancelHandlers, HasTouchEndHandlers, HasTouchMoveHandlers, HasTouchStartHandlers, HasType, HasWordWrap, IsEditor, SourcesClickEvents, SourcesFocusEvents, SourcesKeyboardEvents, SourcesMouseEvents) | 166 |
| `Row` | yes | Reduced | 4 (HasId, HasInlineStyle, HasPull, HasResponsiveness) | 30 |
| `ScrollSpy` | yes | Reduced | 0 | 2 |
| `SimpleCheckBox` | yes | Reduced | 41 (Focusable, HasAllDragAndDropHandlers, HasAllFocusHandlers, HasAllGestureHandlers, HasAllKeyHandlers, HasAllMouseHandlers, HasAllTouchHandlers, HasBlurHandlers, HasChangeHandlers, HasDragEndHandlers, HasDragEnterHandlers, HasDragHandlers, HasDragLeaveHandlers, HasDragOverHandlers, HasDragStartHandlers, HasDropHandlers, HasEnabled, HasFocus, HasFocusHandlers, HasGestureChangeHandlers, HasGestureEndHandlers, HasGestureStartHandlers, HasKeyDownHandlers, HasKeyPressHandlers, HasKeyUpHandlers, HasMouseDownHandlers, HasMouseMoveHandlers, HasMouseOutHandlers, HasMouseOverHandlers, HasMouseUpHandlers, HasMouseWheelHandlers, HasName, HasTouchCancelHandlers, HasTouchEndHandlers, HasTouchMoveHandlers, HasTouchStartHandlers, IsEditor, SourcesClickEvents, SourcesFocusEvents, SourcesKeyboardEvents, SourcesMouseEvents) | 88 |
| `SimpleRadioButton` | yes | Reduced | 41 (Focusable, HasAllDragAndDropHandlers, HasAllFocusHandlers, HasAllGestureHandlers, HasAllKeyHandlers, HasAllMouseHandlers, HasAllTouchHandlers, HasBlurHandlers, HasChangeHandlers, HasDragEndHandlers, HasDragEnterHandlers, HasDragHandlers, HasDragLeaveHandlers, HasDragOverHandlers, HasDragStartHandlers, HasDropHandlers, HasEnabled, HasFocus, HasFocusHandlers, HasGestureChangeHandlers, HasGestureEndHandlers, HasGestureStartHandlers, HasKeyDownHandlers, HasKeyPressHandlers, HasKeyUpHandlers, HasMouseDownHandlers, HasMouseMoveHandlers, HasMouseOutHandlers, HasMouseOverHandlers, HasMouseUpHandlers, HasMouseWheelHandlers, HasName, HasTouchCancelHandlers, HasTouchEndHandlers, HasTouchMoveHandlers, HasTouchStartHandlers, IsEditor, SourcesClickEvents, SourcesFocusEvents, SourcesKeyboardEvents, SourcesMouseEvents) | 89 |
| `StringRadioGroup` | yes | Reduced | 14 (Editor, HasBlankValidator, HasDataSpy, HasEditorErrors, HasErrorHandler, HasName, HasValidators, HasValue, HasValueChangeHandlers, InsertPanel, InsertPanel$ForIsWidget, LeafValueEditor, TakesValue, ValidationChangedEvent$HasValidationChangedHandlers) | 54 |
| `SubmitButton` | yes | Reduced | 10 (HasActive, HasBadge, HasIcon, HasIconPosition, HasInlineStyle, HasWidgets, HasWidgets$ForIsWidget, IndexedPanel, IndexedPanel$ForIsWidget, Iterable) | 99 |
| `SuggestBox` | yes | Reduced | 11 (Editor, HasAutoComplete, HasBlankValidator, HasEditorErrors, HasErrorHandler, HasId, HasPlaceholder, HasResponsiveness, HasSize, HasValidators, ValidationChangedEvent$HasValidationChangedHandlers) | 60 |
| `TabContent` | yes | Full | 0 | 0 |
| `TabListItem` | yes | Reduced | 9 (Focusable, HasActive, HasBadge, HasEnabled, HasHref, HasIcon, HasIconPosition, HasTarget, HasTargetHistoryToken) | 80 |
| `TabPane` | yes | Reduced | 1 (HasActive) | 2 |
| `TabPanel` | yes | Reduced | 1 (HasTabPosition) | 4 |
| `TextArea` | yes | Full | 0 | 0 |
| `TextBox` | yes | Full | 0 | 0 |
| `ThumbnailLink` | yes | Reduced | 3 (HasHref, HasTarget, HasTargetHistoryToken) | 9 |
| `ThumbnailPanel` | yes | Reduced | 4 (HasId, HasInlineStyle, HasPull, HasResponsiveness) | 30 |
| `Tooltip` | yes | Reduced | 3 (AcceptsOneWidget, HasHover, HasOneWidget) | 64 |
| `TooltipHelpBlock` | yes | Reduced | 3 (AcceptsOneWidget, HasHover, HasOneWidget) | 69 |
| `ValueListBox` | yes | Reduced | 11 (Editor, HasBlankValidator, HasEditorErrors, HasErrorHandler, HasId, HasName, HasPlaceholder, HasResponsiveness, HasSize, HasValidators, ValidationChangedEvent$HasValidationChangedHandlers) | 57 |
| `VerticalButtonGroup` | yes | Reduced | 6 (HasDataSpy, HasJustified, HasName, HasSize, InsertPanel, InsertPanel$ForIsWidget) | 22 |
| `Well` | yes | Reduced | 1 (HasSize) | 6 |

## Interpretation

`Full` means the Bootstrap 5 class retains every normalized Bootstrap 3 public interface and API member. Additional Bootstrap 5-native APIs are allowed and do not reduce the score. Deliberate Bootstrap 5 deviations should be documented in `BOOTSTRAP5-PORTING.md`.
