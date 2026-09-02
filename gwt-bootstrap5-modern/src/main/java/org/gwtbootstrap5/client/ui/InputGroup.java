package org.gwtbootstrap5.client.ui;

import org.gwtbootstrap5.client.ui.base.HasSize;
import org.gwtbootstrap5.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap5.client.ui.constants.InputGroupSize;

public class InputGroup extends ElementPanel implements HasSize<InputGroupSize> {

    public InputGroup() {
        super("div");
        addStyleName("input-group");
    }

    @Override
    public void setSize(InputGroupSize size) {
        StyleHelper.addUniqueEnumStyleName(this, InputGroupSize.class, size == null ? InputGroupSize.DEFAULT : size);
    }

    @Override
    public InputGroupSize getSize() {
        return InputGroupSize.fromStyleName(getStyleName());
    }

    public void setLarge(boolean large) {
        setSize(large ? InputGroupSize.LARGE : InputGroupSize.DEFAULT);
    }

    public void setSmall(boolean small) {
        setSize(small ? InputGroupSize.SMALL : InputGroupSize.DEFAULT);
    }

    public boolean isLarge() {
        return getSize() == InputGroupSize.LARGE;
    }

    public boolean isSmall() {
        return getSize() == InputGroupSize.SMALL;
    }
}
