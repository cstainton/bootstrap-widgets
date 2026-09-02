package org.gwtbootstrap5.client.ui;

import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap5.client.ui.base.HasType;
import org.gwtbootstrap5.client.ui.constants.ProgressType;

public class Progress extends ElementPanel implements HasType<ProgressType> {

    private ProgressType type = ProgressType.DEFAULT;
    private boolean active;

    public Progress() {
        super("div");
        addStyleName("progress");
    }

    public void addBar(ProgressBar bar) {
        add(bar);
    }

    @Override
    public void add(Widget child) {
        decorateProgressBar(child);
        super.add(child);
    }

    @Override
    public void setType(ProgressType type) {
        this.type = type == null ? ProgressType.DEFAULT : type;
        updateBars();
    }

    @Override
    public ProgressType getType() {
        return type;
    }

    public void setActive(boolean active) {
        this.active = active;
        updateBars();
    }

    public boolean isActive() {
        return active;
    }

    private void updateBars() {
        for (int i = 0; i < getWidgetCount(); i++) {
            decorateProgressBar(getWidget(i));
        }
    }

    private void decorateProgressBar(Widget child) {
        if (child instanceof ProgressBar) {
            ProgressBar progressBar = (ProgressBar) child;
            progressBar.setStriped(type == ProgressType.STRIPED);
            progressBar.setAnimated(active);
        }
    }
}
