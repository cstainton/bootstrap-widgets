package io.instanto.bootstrap.testing.bootstrap5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.teavm.jso.JSBody;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.junit.AttachJavaScript;
import org.teavm.junit.SkipJVM;
import org.teavm.junit.TeaVMTestRunner;

import com.google.gwt.user.client.ui.RootPanel;

import io.instanto.bootstrap5.client.ui.Button;
import io.instanto.bootstrap5.client.ui.CheckBoxButton;
import io.instanto.bootstrap5.client.ui.RadioButton;
import io.instanto.bootstrap5.client.ui.constants.Toggle;

@RunWith(TeaVMTestRunner.class)
@SkipJVM
public class Bootstrap5ControlBehaviourTest {
    @Test
    @AttachJavaScript("bootstrap-5.3.8.bundle.min.cache.js")
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
    @AttachJavaScript("bootstrap-5.3.8.bundle.min.cache.js")
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
    public void nativeLabelActivationUpdatesCheckboxButtonOnce() {
        CheckBoxButton button = new CheckBoxButton("One");
        int[] changes = {0};
        button.addValueChangeHandler(event -> changes[0]++);
        RootPanel.get().add(button);
        try {
            clickLabel(button.getElement().unwrap());
            assertTrue(button.getValue());
            assertTrue(labelHasClass(button.getElement().unwrap(), "active"));
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
            clickLabel(first.getElement().unwrap());
            assertTrue(first.getValue());
            assertFalse(second.getValue());

            clickLabel(second.getElement().unwrap());
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

    @JSBody(params = "element", script = "element.querySelector('label').click();")
    private static native void clickLabel(HTMLElement element);

    @JSBody(params = {"element", "className"}, script =
            "return element.querySelector('label').classList.contains(className);")
    private static native boolean labelHasClass(HTMLElement element, String className);
}
