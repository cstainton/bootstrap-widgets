package io.instanto.bootstrap.testing.bootstrap3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.CheckBoxButton;
import org.gwtbootstrap3.client.ui.RadioButton;
import org.gwtbootstrap3.client.ui.constants.Toggle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.teavm.jso.JSBody;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.junit.AttachJavaScript;
import org.teavm.junit.SkipJVM;
import org.teavm.junit.TeaVMTestRunner;

import com.google.gwt.user.client.ui.RootPanel;

@RunWith(TeaVMTestRunner.class)
@SkipJVM
public class Bootstrap3ControlBehaviourTest {
    @Test
    @AttachJavaScript({"jquery-3.7.1.min.cache.js", "bootstrap-3.4.1.min.cache.js"})
    public void nativeActivationTogglesButtonStateTwice() {
        Button button = new Button("Toggle");
        int[] clicks = {0};
        button.setDataToggle(Toggle.BUTTON);
        button.addClickHandler(event -> clicks[0]++);
        RootPanel.get().add(button);
        try {
            click(button.getElement().unwrap());
            assertTrue(button.isActive());
            assertEquals("true", button.getElement().getAttribute("aria-pressed"));

            click(button.getElement().unwrap());
            assertFalse(button.isActive());
            assertEquals("false", button.getElement().getAttribute("aria-pressed"));
            assertEquals(2, clicks[0]);
        } finally {
            button.removeFromParent();
        }
    }

    @Test
    @AttachJavaScript({"jquery-3.7.1.min.cache.js", "bootstrap-3.4.1.min.cache.js"})
    public void disabledToggleIgnoresNativeActivation() {
        Button button = new Button("Disabled");
        int[] clicks = {0};
        button.setDataToggle(Toggle.BUTTON);
        button.setEnabled(false);
        button.addClickHandler(event -> clicks[0]++);
        RootPanel.get().add(button);
        try {
            click(button.getElement().unwrap());
            assertFalse(button.isActive());
            assertEquals(0, clicks[0]);
        } finally {
            button.removeFromParent();
        }
    }

    @Test
    public void checkboxButtonsRetainIndependentValues() {
        CheckBoxButton first = new CheckBoxButton("One");
        CheckBoxButton second = new CheckBoxButton("Two");
        int[] firstChanges = {0};
        int[] secondChanges = {0};
        first.addValueChangeHandler(event -> firstChanges[0]++);
        second.addValueChangeHandler(event -> secondChanges[0]++);
        RootPanel.get().add(first);
        RootPanel.get().add(second);
        try {
            first.setValue(true, true);
            assertTrue(first.getValue());
            assertFalse(second.getValue());
            assertEquals(1, firstChanges[0]);
            assertEquals(0, secondChanges[0]);
        } finally {
            first.removeFromParent();
            second.removeFromParent();
        }
    }

    @Test
    public void nativeInputActivationUpdatesCheckboxButtonOnce() {
        CheckBoxButton button = new CheckBoxButton("One");
        int[] changes = {0};
        button.addValueChangeHandler(event -> changes[0]++);
        RootPanel.get().add(button);
        try {
            clickInput(button.getElement().unwrap());
            assertTrue(button.getValue());
            assertEquals(1, changes[0]);
        } finally {
            button.removeFromParent();
        }
    }

    @Test
    public void radioButtonsRemainExclusive() {
        RadioButton first = new RadioButton("fixture-radio", "One");
        RadioButton second = new RadioButton("fixture-radio", "Two");
        RootPanel.get().add(first);
        RootPanel.get().add(second);
        try {
            click(first.getElement().unwrap());
            assertTrue(first.getValue());
            assertFalse(second.getValue());

            click(second.getElement().unwrap());
            assertFalse(first.getValue());
            assertTrue(second.getValue());
        } finally {
            first.removeFromParent();
            second.removeFromParent();
        }
    }

    @Test
    public void loadingStateRestoresTextAndEnabledState() {
        Button button = new Button("Save");
        button.setDataLoadingText("Saving...");

        button.state().loading();
        assertEquals("Saving...", button.getText());
        assertFalse(button.isEnabled());
        assertEquals("true", button.getElement().getAttribute("aria-busy"));

        button.state().reset();
        assertEquals("Save", button.getText());
        assertTrue(button.isEnabled());
        assertFalse(button.getElement().hasAttribute("aria-busy"));
    }

    @JSBody(params = "element", script = "element.click();")
    private static native void click(HTMLElement element);

    @JSBody(params = "element", script = "element.querySelector('input').click();")
    private static native void clickInput(HTMLElement element);
}
