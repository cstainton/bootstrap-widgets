package org.gwtbootstrap5.client.internal.dom;

/**
 * Minimal DOM surface used by the Bootstrap integration layer.
 *
 * Keep this deliberately small: GWT code can back it with Elemental2, and a future
 * TeaVM build can back the same contract with TeaVM JSO bindings generated from WebIDL.
 */
public interface BootstrapDomElement {

    void addClass(String className);

    void removeClass(String className);

    boolean hasClass(String className);

    String getAttribute(String name);

    void setAttribute(String name, String value);

    void removeAttribute(String name);

    Object unwrap();
}
