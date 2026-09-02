package org.gwtbootstrap5.client.ui;

public enum HeadingSize {
    H1(1),
    H2(2),
    H3(3),
    H4(4),
    H5(5),
    H6(6);

    private final int size;

    HeadingSize(int size) {
        this.size = size;
    }

    public int size() {
        return size;
    }
}
