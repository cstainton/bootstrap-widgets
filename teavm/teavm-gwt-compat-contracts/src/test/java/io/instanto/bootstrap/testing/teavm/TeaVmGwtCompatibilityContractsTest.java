package io.instanto.bootstrap.testing.teavm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.teavm.junit.SkipJVM;
import org.teavm.junit.TeaVMTestRunner;

import io.instanto.bootstrap.testing.contracts.JvmSafeGwtContracts;
import io.instanto.bootstrap.testing.contracts.BrowserBoundGwtContracts;

@RunWith(TeaVMTestRunner.class)
@SkipJVM
public class TeaVmGwtCompatibilityContractsTest {
    @Test
    public void safeHtmlEscapesAndPreservesTrustedFragments() {
        JvmSafeGwtContracts.safeHtmlEscapesAndPreservesTrustedFragments();
    }

    @Test
    public void handlerManagerPreservesOrderAndSource() {
        JvmSafeGwtContracts.handlerManagerPreservesOrderAndSource();
    }

    @Test
    public void handlerMutationIsDeferredUntilTheNextDispatch() {
        JvmSafeGwtContracts.handlerMutationIsDeferredUntilTheNextDispatch();
    }

    @Test
    public void removedHandlerDoesNotReceiveLaterEvents() {
        JvmSafeGwtContracts.removedHandlerDoesNotReceiveLaterEvents();
    }

    @Test
    public void valueChangesRespectExplicitEventSuppression() {
        JvmSafeGwtContracts.valueChangesRespectExplicitEventSuppression();
    }

    @Test
    public void specializedElementFactoriesAndNarrowingRetainTheirTypes() {
        BrowserBoundGwtContracts.specializedElementFactoriesAndNarrowingRetainTheirTypes();
    }

    @Test
    public void mouseEventTargetsRetainTheirUnderlyingElements() {
        Element related = Document.get().createDivElement();
        NativeEvent event = Document.get().createMouseOverEvent(1, 2, 3, 4, 5,
                false, false, false, false, 0, related);

        EventTarget relatedTarget = event.getRelatedEventTarget();
        assertNotNull("related event target must be present", relatedTarget);

        Element narrowed = Element.as(relatedTarget);
        narrowed.setAttribute("data-related-target", "retained");
        assertEquals("Element.as must retain the related target's underlying element",
                "retained", related.getAttribute("data-related-target"));
    }

    @Test
    public void widgetLifecycleReportsAttachThenDetachAndClearsParent() {
        BrowserBoundGwtContracts.widgetLifecycleReportsAttachThenDetachAndClearsParent();
    }

    @Test
    public void dependentStyleNamesFollowThePrimaryStyle() {
        BrowserBoundGwtContracts.dependentStyleNamesFollowThePrimaryStyle();
    }

    @Test
    public void checkboxValueBridgeUsesTheSubclassExtensionPointOnce() {
        BrowserBoundGwtContracts.checkboxValueBridgeUsesTheSubclassExtensionPointOnce();
    }
}
