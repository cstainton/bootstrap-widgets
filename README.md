# GWT Bootstrap Modern

GWT Bootstrap Modern is a maintained fork of GwtBootstrap3 with two deliberately separate tracks.

1. The Bootstrap 3 track is the drop-in maintenance build for existing GwtBootstrap3 applications. It keeps `org.gwtbootstrap3.*`, the original GWT module names, Bootstrap 3 markup, and Bootstrap 3 widget behaviour, while updating the build to GWT 2.13.1, Bootstrap 3.4.1, and jQuery 3.7.1.
2. The Bootstrap 5 track is a native migration build. It uses `org.gwtbootstrap5.*`, Bootstrap 5 resources, Bootstrap 5 class names, Bootstrap 5 data attributes, and breaking API changes where Bootstrap 5 changed or removed Bootstrap 3 concepts.

The project also keeps TeaVM tracks for both versions. Both TeaVM tracks use the shared `gwt-teavm-compat` layer for the small GWT client surface they need, but the version-specific widgets remain separate. The TeaVM modules are not UiBinder/GWT DOM builds; they are direct Java widget APIs backed by TeaVM DOM/JSO calls.

## Showcases

- [Bootstrap 3-compatible GWT showcase](https://cstainton.github.io/gwtbootstrap-modern/)
- [Bootstrap 5-native GWT showcase](https://cstainton.github.io/gwtbootstrap-modern/bootstrap5/)
- [TeaVM Bootstrap 3 smoke page](https://cstainton.github.io/gwtbootstrap-modern/teavm.html)
- [TeaVM Bootstrap 5 smoke page](https://cstainton.github.io/gwtbootstrap-modern/teavm-bootstrap5.html)

## Status

This project is under active migration.

The Bootstrap 3 compatibility build and original showcase compile with GWT 2.13.1. The Bootstrap 5 module is a native widget track with top-level coverage for the current GwtBootstrap3 widget catalogue. Its compiled showcase covers layout, buttons, cards, alerts, badges, list groups, linked groups, typography helpers, icons, images, thumbnails, dropdowns, modals, navbars, navs, tabs, progress bars, input groups, form adapters, value boxes, radio groups, collapse, tooltips, popovers, carousels, pagination, and Bootstrap 3 panel/well concepts mapped to Bootstrap 5 card/utility idioms. The TeaVM modules are experimental and currently contain compile-checked subsets for Bootstrap 3-compatible and Bootstrap 5-native rendering.

## Migration Paths

Use the Bootstrap 3 artifacts when you want the safest replacement for an existing GwtBootstrap3 dependency:

```xml
<dependency>
  <groupId>org.gwtbootstrap3</groupId>
  <artifactId>gwt-bootstrap3-modern</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>

<dependency>
  <groupId>org.gwtbootstrap3</groupId>
  <artifactId>gwt-bootstrap3-modern-extras</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

Use the Bootstrap 5 artifact when you are ready to migrate templates and code to Bootstrap 5 concepts:

```xml
<dependency>
  <groupId>org.gwtbootstrap3</groupId>
  <artifactId>gwt-bootstrap5-modern</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

TeaVM Bootstrap 3 experiment:

```xml
<dependency>
  <groupId>org.gwtbootstrap3</groupId>
  <artifactId>teavm-bootstrap3-modern</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

TeaVM Bootstrap 5 experiment:

```xml
<dependency>
  <groupId>org.gwtbootstrap3</groupId>
  <artifactId>teavm-bootstrap5-modern</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

## Modules

- `gwt-bootstrap3-modern`: GwtBootstrap3-compatible core widgets backed by Bootstrap 3.4.1 and jQuery 3.7.1.
- `gwt-bootstrap3-modern-extras`: GwtBootstrap3-compatible extras widgets and third-party integrations.
- `gwt-bootstrap3-modern-showcase`: original GWT showcase used for visual and compile testing of the Bootstrap 3 compatibility modules.
- `gwt-bootstrap5-modern`: Bootstrap 5-native GWT widgets and resources under `org.gwtbootstrap5.*`.
- `gwt-bootstrap5-modern-showcase`: Bootstrap 5-native GWT showcase.
- `gwt-teavm-compat`: shared minimal GWT client API subset backed by TeaVM DOM APIs.
- `teavm-bootstrap3-modern`: experimental Bootstrap 3-compatible TeaVM widget backend using `gwt-teavm-compat`, without UiBinder.
- `teavm-bootstrap5-modern`: experimental Bootstrap 5-native TeaVM widget backend using `gwt-teavm-compat`, without UiBinder.

Planned module:

- `teavm-bootstrap5-modern-showcase`: fuller TeaVM showcase for the Bootstrap 5-native widget set.

## Bootstrap 5 Coverage

`BOOTSTRAP5-PORTING.md` tracks Bootstrap 5 coverage against the current GwtBootstrap3 top-level widget catalogue. The Bootstrap 5 module now represents the current GwtBootstrap3 top-level widget catalogue, plus Bootstrap 5-specific helper classes. The showcase provides visible coverage for common content, status, navigation, overlay, dropdown, progress, basic form, value, radio group, media, image, tab, collapse, tooltip, popover, carousel and pagination widgets. The compatibility story belongs to Bootstrap 3; Bootstrap 5 keeps native markup and behaviour.

The Bootstrap 5 migration path is widget-by-widget: port the Bootstrap 3 widget concept, replace Bootstrap 3 styles and markup with Bootstrap 5 equivalents, and replace jQuery plugin behaviour with Bootstrap 5 JavaScript APIs or direct DOM behaviour.

## Build

Build and install the Maven reactor:

```bash
mvn -f pom.xml -DskipTests install
```

Compile the Bootstrap 3 compatibility showcase:

```bash
mvn -f gwt-bootstrap3-modern-showcase/pom.xml -DskipTests -Dgwt.forceCompilation=true gwt:compile
```

Compile the Bootstrap 5-native showcase:

```bash
mvn -f gwt-bootstrap5-modern-showcase/pom.xml -DskipTests -Dgwt.forceCompilation=true gwt:compile
```

Compile the TeaVM Bootstrap 3 smoke target:

```bash
mvn -f teavm-bootstrap3-modern/pom.xml -DskipTests package
```

Compile the TeaVM Bootstrap 5 smoke target:

```bash
mvn -f teavm-bootstrap5-modern/pom.xml -DskipTests package
```

## Showcase Details

GitHub Pages serves the Bootstrap 3-compatible showcase:

[https://cstainton.github.io/gwtbootstrap-modern/](https://cstainton.github.io/gwtbootstrap-modern/)

The Bootstrap 5-native GWT showcase is published at:

[https://cstainton.github.io/gwtbootstrap-modern/bootstrap5/](https://cstainton.github.io/gwtbootstrap-modern/bootstrap5/)

The TeaVM Bootstrap 3 smoke page is published at:

[https://cstainton.github.io/gwtbootstrap-modern/teavm.html](https://cstainton.github.io/gwtbootstrap-modern/teavm.html)

The TeaVM Bootstrap 5 smoke page is published at:

[https://cstainton.github.io/gwtbootstrap-modern/teavm-bootstrap5.html](https://cstainton.github.io/gwtbootstrap-modern/teavm-bootstrap5.html)

## Notes

- Java package names remain `org.gwtbootstrap3.*` for source compatibility in the Bootstrap 3 compatibility modules.
- Bootstrap 5 widgets use `org.gwtbootstrap5.*` and should not change `org.gwtbootstrap3` semantics.
- GWT module names remain based on the original GwtBootstrap3 modules for the Bootstrap 3 compatibility track.
- Browser assets are tracked in `THIRD-PARTY-ASSETS.md`.
