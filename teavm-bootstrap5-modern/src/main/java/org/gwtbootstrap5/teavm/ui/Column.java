package org.gwtbootstrap5.teavm.ui;

public class Column extends FlowPanel {

    public Column() {
        addStyleName("col");
    }

    public Column(final String bootstrapSizeClass) {
        addStyleName(bootstrapSizeClass == null || bootstrapSizeClass.trim().isEmpty()
                ? "col"
                : bootstrapSizeClass.trim());
    }

    public static Column md(final int span) {
        return new Column("col-md-" + span);
    }
}
