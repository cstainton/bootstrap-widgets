@p1 @gwt3 @teavm3 @gwt5 @teavm5
Feature: Suggest box interaction and lifecycle
  Suggest boxes filter their oracle, support keyboard selection and remove
  transient popup state when detached.

  @api @rendered @functional @dom-contract
  Scenario: SUG-001 Entered text filters the visible suggestion popup
    Given fixture "behaviour/suggest-box/basic" is mounted
    Given Bootstrap 3 showcase route "suggestBox" section "Basic" defines the baseline
    When the user enters "Uni" in the suggest box
    Then only matching suggestions are visible
    And the popup width matches the suggest box width
    And the popup is positioned beneath the suggest box

  @api @rendered @functional
  Scenario: SUG-002 Keyboard selection commits one oracle suggestion
    Given fixture "behaviour/suggest-box/basic" is mounted
    Given Bootstrap 3 showcase route "suggestBox" section "Basic" defines the baseline
    And matching suggestions are visible for "Uni"
    When the user selects the first suggestion with the keyboard
    Then the suggest box value is "United Kingdom"
    And one suggestion event is reported with the suggest box as source
    And the suggestion popup is hidden

  @api @rendered @functional @dom-contract
  Scenario: SUG-003 A query without matches closes the suggestion popup
    Given fixture "behaviour/suggest-box/basic" is mounted
    Given Bootstrap 3 showcase route "suggestBox" section "Basic" defines the baseline
    And matching suggestions are visible
    When the user enters a query that has no matches
    Then the suggestion popup is hidden
    And no suggestion event is reported

  @api @rendered @functional @dom-contract
  Scenario: SUG-004 Suggestion popup lifecycle follows its owning widget
    Given fixture "behaviour/suggest-box/lifecycle" is mounted
    Given Bootstrap 3 showcase route "suggestBox" section "Basic" defines the baseline
    And the suggestion popup is visible
    When the suggest box is detached
    Then its suggestion popup is removed from the document
    When the suggest box is mounted again and queried
    Then exactly one replacement suggestion popup is visible
    And one keyboard selection reports one suggestion event
