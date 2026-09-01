# GWT Bootstrap Modern TeaVM Widget Backend

This module is the TeaVM track for `gwtbootstrap-modern`. It is intentionally built as a separate artifact so existing GWT consumers can keep using the normal GwtBootstrap3-compatible modules while we grow a non-GWT implementation.

The target is not just Bootstrap JavaScript helpers. The target is a TeaVM-backed widget API that preserves the way GwtBootstrap3 users express Bootstrap UI in Java, but without depending on GWT DOM classes.

Current scope:

- A small widget facade: `Widget`, `Panel`, `FlowPanel`, `RootPanel`, `Container`, `Row`, `Column`, `Button`, `Anchor`, `Label`, `Paragraph`, `Heading`, and `Modal`.
- TeaVM DOM wrappers for attributes, class names, visibility, text/html, child management, and lookup.
- Bootstrap 5 modal interop through TeaVM JSO.

Compatibility direction:

- Phase 1 keeps the TeaVM API separate under `org.gwtbootstrap3.teavm.*` while the backend shape settles.
- Phase 2 can add same-package compatibility classes where practical, so applications can move selected UI code from GWT to TeaVM with minimal source changes.
- Event, value, form, and lifecycle APIs need careful mapping because the original GwtBootstrap3 surface inherits many GWT widget and handler types.
