# GWT Bootstrap 5 Modern TeaVM Widget Backend

This module is the experimental TeaVM track for Bootstrap 5-native widgets. It is separate from `teavm-bootstrap3-modern` because Bootstrap 5 has different class names, JavaScript APIs, and component semantics.

The target is a TeaVM-backed widget API for Bootstrap 5 without UiBinder or GWT DOM classes. It uses the shared `gwt-teavm-compat` module for the small GWT client surface needed by TeaVM entry points and root panel integration.

Current scope:

- A small widget facade: `Widget`, `Panel`, `FlowPanel`, `RootPanel`, `Container`, `Row`, `Column`, `Button`, `Anchor`, `Label`, `Paragraph`, `Heading`, `Card`, and `Modal`.
- Shared GWT compatibility entry point/root panel support from `gwt-teavm-compat`.
- Plain TeaVM mounting through `Mount.toBody(...)` and `Mount.toElement(...)`, so `RootPanel` is optional.
- TeaVM DOM wrappers for attributes, class names, visibility, text/html, child management, and lookup.
- Modal interop through TeaVM JSO using Bootstrap 5's `bootstrap.Modal` API.

Compatibility direction:

- Keep Bootstrap 5 TeaVM code under `org.gwtbootstrap5.teavm.*`.
- Do not expose `org.gwtbootstrap3` facades from this module.
- Port Bootstrap 3 widget concepts only where Bootstrap 5 has a clear equivalent.
- Prefer Bootstrap 5 naming and behaviour over compatibility shims.
