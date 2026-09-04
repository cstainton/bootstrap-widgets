package io.instanto.bootstrap.testing.bootstrap5;

import static io.instanto.mockatcha.dom.Expect.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.google.gwt.user.client.ui.RootPanel;
import io.instanto.cucumber.tea.AfterScenario;
import io.instanto.cucumber.tea.BeforeScenario;
import io.instanto.cucumber.tea.CucumberScript;
import io.instanto.cucumber.tea.CucumberSuite;
import io.instanto.cucumber.tea.Given;
import io.instanto.cucumber.tea.Then;
import io.instanto.cucumber.tea.When;
import io.instanto.mockatcha.dom.Dom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import io.instanto.bootstrap5.client.ui.Button;
import io.instanto.bootstrap5.client.ui.CheckBoxButton;
import io.instanto.bootstrap5.client.ui.RadioButton;
import io.instanto.bootstrap5.client.ui.constants.ButtonSize;
import io.instanto.bootstrap5.client.ui.constants.ButtonType;
import io.instanto.bootstrap5.client.ui.constants.Toggle;
import org.teavm.jso.dom.html.HTMLElement;

@CucumberSuite(
        value = "features/buttons.feature",
        scripts = @CucumberScript(
                resource = "bootstrap-5.3.8.bundle.min.cache.js",
                path = "bootstrap-5.3.8.bundle.min.cache.js"))
public class Bootstrap5ButtonSteps {
    private RootPanel host;
    private Button button;
    private CheckBoxButton checkBoxButton;
    private CheckBoxButton[] checkBoxButtons;
    private RadioButton[] radioButtons;
    private final List<Button> typeButtons = new ArrayList<>();
    private int clicks;
    private int valueChanges;
    private Object lastEventSource;
    private int[] checkBoxChanges;
    private String loadingText;

    @BeforeScenario
    public void createHost() {
        Dom.reset();
        Dom.container().setAttribute("id", "bootstrap5-widget-test-host");
        host = RootPanel.get("bootstrap5-widget-test-host");
    }

    @AfterScenario
    public void removeFixture() {
        if (host != null) {
            host.clear();
        }
        Dom.reset();
    }

    @Given("fixture {string} is mounted")
    public void mountedFixture(String fixture) {
        createFixture(fixture, true);
    }

    @Given("fixture {string} is constructed")
    public void constructedFixture(String fixture) {
        createFixture(fixture, false);
    }

    @Given("Bootstrap 3 showcase route {string} section {string} defines the baseline")
    public void referenceShowcaseDefinesBaseline(String route, String section) {
        assertFalse(route.isEmpty());
        assertFalse(section.isEmpty());
    }

    private void createFixture(String fixture, boolean mount) {
        if (fixture.startsWith("behaviour/toggle-button/")) {
            button = new Button("Toggle");
            button.setDataToggle(Toggle.BUTTON);
            if (fixture.endsWith("disabled")) {
                button.setEnabled(false);
            }
            button.addClickHandler(event -> {
                clicks++;
                lastEventSource = event.getSource();
            });
            mount(button, mount);
            return;
        }
        if (fixture.equals("behaviour/check-box-button/basic")) {
            checkBoxButton = new CheckBoxButton("Choice");
            checkBoxButton.addValueChangeHandler(event -> {
                valueChanges++;
                lastEventSource = event.getSource();
            });
            mount(checkBoxButton, mount);
            return;
        }
        if (fixture.equals("behaviour/check-box-buttons/independent")) {
            checkBoxButtons = new CheckBoxButton[] {
                new CheckBoxButton("One"), new CheckBoxButton("Two"), new CheckBoxButton("Three")
            };
            checkBoxChanges = new int[checkBoxButtons.length];
            for (int i = 0; i < checkBoxButtons.length; i++) {
                final int index = i;
                checkBoxButtons[i].addValueChangeHandler(event -> checkBoxChanges[index]++);
                mount(checkBoxButtons[i], mount);
            }
            return;
        }
        if (fixture.equals("behaviour/radio-buttons/exclusive")) {
            radioButtons = new RadioButton[] {
                new RadioButton("fixture-radio", "One"),
                new RadioButton("fixture-radio", "Two")
            };
            radioButtons[0].setValue(true);
            for (RadioButton radio : radioButtons) {
                mount(radio, mount);
            }
            return;
        }
        if (fixture.equals("behaviour/button/loading") || fixture.equals("behaviour/button/sizes")) {
            button = new Button("Save");
            loadingText = "Saving...";
            button.setDataLoadingText(loadingText);
            mount(button, mount);
            return;
        }
        if (fixture.equals("behaviour/button/types")) {
            for (ButtonType type : ButtonType.values()) {
                Button typed = new Button(type.name());
                typed.setType(type);
                typeButtons.add(typed);
                mount(typed, mount);
            }
            return;
        }
        throw new IllegalArgumentException("Unknown fixture: " + fixture);
    }

    private void mount(com.google.gwt.user.client.ui.Widget widget, boolean mounted) {
        widget.getElement().setAttribute("data-testid", widget.getClass().getSimpleName());
        if (mounted) {
            host.add(widget);
        }
    }

    @Given("the toggle button is inactive")
    public void toggleButtonIsInactive() {
        assertFalse(button.isActive());
    }

    @Given("the toggle button is active")
    public void toggleButtonIsActive() {
        assertTrue(button.isActive());
    }

    @Given("the toggle button is disabled")
    public void toggleButtonIsDisabled() {
        expect(element(button)).toBeDisabled();
    }

    @When("the user activates the toggle button")
    public void activateToggleButton() {
        Dom.click(element(button));
        Dom.waitFor(() -> assertEquals(Boolean.toString(button.isActive()),
                button.getElement().getAttribute("aria-pressed")));
    }

    @Then("the toggle button has the active state class")
    public void toggleHasActiveClass() {
        expect(element(button)).toHaveClass("active");
    }

    @Then("the toggle button does not have the active state class")
    public void toggleDoesNotHaveActiveClass() {
        expect(element(button)).not().toHaveClass("active");
    }

    @Then("the toggle button has aria-pressed {string}")
    public void toggleHasPressedState(String state) {
        expect(element(button)).toHaveAttribute("aria-pressed", state);
    }

    @Then("one click is reported with the toggle button as source")
    public void oneToggleClickIsReported() {
        assertEquals(1, clicks);
        assertSame(button, lastEventSource);
    }

    @Then("no click or value change is reported")
    public void noControlEventIsReported() {
        assertEquals(0, clicks);
        assertEquals(0, valueChanges);
    }

    @When("the checkbox button value is set to true without firing events")
    public void setCheckboxSilently() {
        checkBoxButton.setValue(true, false);
    }

    @Then("the checkbox button value is true")
    public void checkboxValueIsTrue() {
        assertTrue(checkBoxButton.getValue());
    }

    @Then("no value change is reported")
    public void noValueChangeIsReported() {
        assertEquals(0, valueChanges);
    }

    @When("the checkbox button value is set to false and events are requested")
    public void setCheckboxAndFire() {
        checkBoxButton.setValue(false, true);
    }

    @Then("one value change is reported with the checkbox button as source")
    public void oneCheckboxValueChangeIsReported() {
        assertEquals(1, valueChanges);
        assertSame(checkBoxButton, lastEventSource);
    }

    @When("the user activates the first checkbox button")
    public void activateFirstCheckbox() {
        clickChoice(checkBoxButtons[0]);
    }

    @When("the user activates the third checkbox button")
    public void activateThirdCheckbox() {
        clickChoice(checkBoxButtons[2]);
    }

    @Then("the first and third checkbox button values are true")
    public void firstAndThirdCheckboxesAreSelected() {
        Dom.waitFor(() -> {
            assertTrue(checkBoxButtons[0].getValue());
            assertTrue(checkBoxButtons[2].getValue());
        });
    }

    @Then("the second checkbox button value is false")
    public void secondCheckboxIsNotSelected() {
        assertFalse(checkBoxButtons[1].getValue());
    }

    @Then("each changed checkbox button reports one value change")
    public void changedCheckboxesReportOnce() {
        Dom.waitFor(() -> assertTrue(Arrays.equals(new int[] {1, 0, 1}, checkBoxChanges)));
    }

    @Given("the first radio button is selected")
    public void firstRadioIsSelected() {
        assertTrue(radioButtons[0].getValue());
    }

    @When("the user activates the second radio button")
    public void activateSecondRadio() {
        clickChoice(radioButtons[1]);
    }

    @Then("only the second radio button is selected")
    public void onlySecondRadioIsSelected() {
        Dom.waitFor(() -> {
            assertFalse(radioButtons[0].getValue());
            assertTrue(radioButtons[1].getValue());
        });
    }

    @Then("the first radio button has aria-pressed {string}")
    public void firstRadioHasPressedState(String state) {
        assertEquals(state, Boolean.toString(radioButtons[0].getValue()));
    }

    @Then("the second radio button has aria-pressed {string}")
    public void secondRadioHasPressedState(String state) {
        assertEquals(state, Boolean.toString(radioButtons[1].getValue()));
    }

    @Given("the button text is {string}")
    public void buttonTextIs(String text) {
        assertEquals(text, button.getText());
    }

    @Given("the loading text is {string}")
    public void loadingTextIs(String text) {
        assertEquals(text, loadingText);
    }

    @When("loading state is started")
    public void startLoading() {
        button.state().loading();
    }

    @When("loading state is reset")
    public void resetLoading() {
        button.state().reset();
    }

    @Then("the button is disabled")
    public void buttonIsDisabled() {
        assertFalse(button.isEnabled());
    }

    @Then("the button is enabled")
    public void buttonIsEnabled() {
        assertTrue(button.isEnabled());
    }

    @Then("the button has aria-busy {string}")
    public void buttonHasBusyState(String state) {
        assertEquals(state, button.getElement().getAttribute("aria-busy"));
    }

    @Then("the button has no aria-busy attribute")
    public void buttonHasNoBusyState() {
        assertFalse(button.getElement().hasAttribute("aria-busy"));
    }

    @When("every supported button type is assigned")
    public void everyTypeIsAssigned() {
        assertEquals(ButtonType.values().length, typeButtons.size());
    }

    @Then("each button reports the assigned type")
    public void eachButtonReportsItsType() {
        for (int i = 0; i < typeButtons.size(); i++) {
            assertEquals(ButtonType.values()[i], typeButtons.get(i).getType());
        }
    }

    @Then("each button has exactly one matching framework type class")
    public void eachButtonHasOneTypeClass() {
        for (int i = 0; i < typeButtons.size(); i++) {
            ButtonType assigned = ButtonType.values()[i];
            expect(element(typeButtons.get(i))).toHaveClass(assigned.getCssName());
            assertEquals(1, countTypeClasses(typeButtons.get(i)));
        }
    }

    @Then("no type falls through to the default class")
    public void noTypeFallsThrough() {
        for (int i = 0; i < typeButtons.size(); i++) {
            ButtonType assigned = ButtonType.values()[i];
            if (assigned != ButtonType.DEFAULT
                    && !assigned.getCssName().equals(ButtonType.DEFAULT.getCssName())) {
                expect(element(typeButtons.get(i))).not().toHaveClass(ButtonType.DEFAULT.getCssName());
            }
        }
    }

    private int countTypeClasses(Button typed) {
        int count = 0;
        List<String> counted = new ArrayList<>();
        for (ButtonType candidate : ButtonType.values()) {
            if (!counted.contains(candidate.getCssName()) && hasClass(typed, candidate.getCssName())) {
                counted.add(candidate.getCssName());
                count++;
            }
        }
        return count;
    }

    @When("a button changes from large to small")
    public void changeButtonSize() {
        button.setSize(ButtonSize.LARGE);
        button.setSize(ButtonSize.SMALL);
    }

    @Then("the button reports the small size")
    public void buttonReportsSmallSize() {
        assertEquals(ButtonSize.SMALL, button.getSize());
    }

    @Then("the small size class is present")
    public void smallSizeClassIsPresent() {
        expect(element(button)).toHaveClass(ButtonSize.SMALL.getCssName());
    }

    @Then("the large size class is absent")
    public void largeSizeClassIsAbsent() {
        expect(element(button)).not().toHaveClass(ButtonSize.LARGE.getCssName());
    }

    private static HTMLElement element(com.google.gwt.user.client.ui.Widget widget) {
        return widget.getElement().unwrap();
    }

    private static void clickChoice(com.google.gwt.user.client.ui.Widget widget) {
        Dom.click(Dom.within(element(widget)).find("label"));
    }

    private static boolean hasClass(com.google.gwt.user.client.ui.Widget widget, String className) {
        String classes = " " + widget.getStyleName() + " ";
        return classes.contains(" " + className + " ");
    }
}
