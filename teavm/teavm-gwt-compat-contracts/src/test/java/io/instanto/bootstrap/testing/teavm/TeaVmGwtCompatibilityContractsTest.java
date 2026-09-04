package io.instanto.bootstrap.testing.teavm;

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
