# GWT Bootstrap 3 TeaVM Widget Backend

This module is the experimental TeaVM track for the Bootstrap 3 compatibility work. It is intentionally built as a separate artifact so existing GWT consumers can keep using the normal GwtBootstrap3-compatible modules while the non-GWT implementation matures.

The target is a TeaVM-backed widget API that preserves the way users express Bootstrap 3 UI in Java, but without depending on UiBinder or GWT DOM classes. It uses the shared `teavm-gwt-compat` module for the small GWT client surface needed by TeaVM entry points and root panel integration.

Current scope:

- A small widget facade: `Widget`, `Panel`, `FlowPanel`, `RootPanel`, `Container`, `Row`, `Column`, `Button`, `Anchor`, `Label`, `Paragraph`, `Heading`, and `Modal`.
- Shared GWT compatibility entry point/root panel support from `teavm-gwt-compat`.
- Plain TeaVM mounting through `Mount.toBody(...)` and `Mount.toElement(...)`, so `RootPanel` is optional.
- TeaVM DOM wrappers for attributes, class names, visibility, text/html, child management, and lookup.
- Modal interop through TeaVM JSO using Bootstrap 3 jQuery plugin semantics.

Compatibility direction:

- Phase 1 keeps the TeaVM API separate under `org.gwtbootstrap3.teavm.*` while the backend shape settles.
- Phase 2 can add same-package compatibility classes where practical, so applications can move selected UI code from GWT to TeaVM with minimal source changes.
- Event, value, form, and lifecycle APIs need careful mapping because the original GwtBootstrap3 surface inherits many GWT widget and handler types.

The same architecture split applies to TeaVM: Bootstrap 3-compatible widgets and Bootstrap 5-native widgets should be separate tracks, with separate showcases.
