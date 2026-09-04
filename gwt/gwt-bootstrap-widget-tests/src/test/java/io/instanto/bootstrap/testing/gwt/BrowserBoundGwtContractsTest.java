package io.instanto.bootstrap.testing.gwt;

import com.google.gwt.junit.client.GWTTestCase;

import io.instanto.bootstrap.testing.contracts.BrowserBoundGwtContracts;

public class BrowserBoundGwtContractsTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return "io.instanto.bootstrap.testing.gwt.BootstrapWidgetContractsTest";
    }

    public void testSpecializedElementFactoriesAndNarrowingRetainTheirTypes() {
        BrowserBoundGwtContracts.specializedElementFactoriesAndNarrowingRetainTheirTypes();
    }

    public void testWidgetLifecycleReportsAttachThenDetachAndClearsParent() {
        BrowserBoundGwtContracts.widgetLifecycleReportsAttachThenDetachAndClearsParent();
    }

    public void testDependentStyleNamesFollowThePrimaryStyle() {
        BrowserBoundGwtContracts.dependentStyleNamesFollowThePrimaryStyle();
    }

    public void testCheckboxValueBridgeUsesTheSubclassExtensionPointOnce() {
        BrowserBoundGwtContracts.checkboxValueBridgeUsesTheSubclassExtensionPointOnce();
    }
}
