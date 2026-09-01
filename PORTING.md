# Porting Notes

## Source Baseline

This fork is based on the latest upstream GwtBootstrap3 projects rather than the old `0.9.4` jar snapshot:

- `gwtbootstrap3` upstream `master`: `66b482b4ec5db54931ff76c4a66454ea4d39637f`
- `gwtbootstrap3-extras` upstream `master`: `3b4c82f547f39fbfcf9d44b4b912ea3fdd61c13b`
- `gwtbootstrap3-demo` upstream `master`: `d4fc399914ac386220fc351dbd707592afa34224`

Upstream still declares old `com.google.gwt` GWT `2.8.0`. This fork deliberately targets `org.gwtproject:gwt-user` and `org.gwtproject:gwt-dev` `2.13.1` to match TMS.

## Compatibility Rules

- Keep public package names and GWT module names stable for drop-in consumers.
- Prefer resource and CSS compatibility over Java API churn.
- Keep Bootstrap 5 implementation details inside the fork; do not leak Domino, Elemental2, or TeaVM types into the existing GWT API.
- Use the TeaVM widget backend for experiments until the DOM/plugin seam is strong enough to support an alternate backend.

## Bootstrap 5 Strategy

Core Bootstrap resources are replaced with Bootstrap 5.3.x plus a narrow jQuery compatibility bridge. The bridge maps common legacy calls such as `.modal()`, `.collapse()`, `.tooltip()`, `.popover()`, `.tab()`, `.carousel()`, `.alert()`, `.button()`, `.dropdown()`, and `.scrollspy()` onto Bootstrap 5 classes where practical.

CSS compatibility covers common Bootstrap 3-era classes still emitted by the widgets, including panels, wells, labels, pull helpers, old navbar shapes, and input-group add-ons.

## GWT And TeaVM Outputs

This repository should keep two buildable outputs:

1. A GWT-compatible output: `gwt-bootstrap-modern`, `gwt-bootstrap-modern-extras`, and the original showcase compiled by GWT 2.13.x.
2. A TeaVM-compatible output: `teavm-bootstrap-modern`, compiled by TeaVM to JavaScript as part of its Maven build.

## TeaVM Direction

The TeaVM module is currently a widget backend prototype, not a complete replacement for the GWT implementation. The intended route is:

1. Keep stabilising the internal DOM/plugin abstraction in the GWT implementation.
2. Move direct JSNI/jQuery calls behind small interfaces.
3. Provide TeaVM JSO-backed implementations for those interfaces.
4. Share widget behaviour above that adapter where possible.
5. Add same-package compatibility facades only when the backend contracts are stable enough to avoid a divergent second library.

A broad Elemental2-on-TeaVM compatibility layer is not the preferred first move. A small WebIDL-driven set of TeaVM JSO bindings may be useful later, but only for browser APIs actually needed by this library.
