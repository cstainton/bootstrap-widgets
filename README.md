# GWT Bootstrap

GWT Bootstrap is a maintained fork of GwtBootstrap3 with two deliberately separate tracks.

1. The Bootstrap 3 track is the drop-in maintenance build for existing GwtBootstrap3 applications. It keeps `org.gwtbootstrap3.*`, the original GWT module names, Bootstrap 3 markup, and Bootstrap 3 widget behaviour, while updating the build to GWT 2.13.1, Bootstrap 3.4.1, and jQuery 3.7.1.
2. The Bootstrap 5 track is a native migration build. It uses `io.instanto.bootstrap5.*`, Bootstrap 5 resources, Bootstrap 5 class names, Bootstrap 5 data attributes, and breaking API changes where Bootstrap 5 changed or removed Bootstrap 3 concepts.

The project also keeps TeaVM tracks for both versions. Both TeaVM tracks use the shared `teavm-gwt-compat` layer for the small GWT client surface they need, but the version-specific widgets remain separate. The TeaVM modules are not UiBinder/GWT DOM builds; they are direct Java widget APIs backed by TeaVM DOM/JSO calls.

## Coordinates

The artifacts publish under `io.instanto`, not the `org.gwtbootstrap3` groupId upstream uses.

The groupId was changed to avoid clashing with the original project. `org.gwtbootstrap3` is upstream's namespace: releasing a fork into it would put artifacts that upstream did not build alongside ones it did, in the same coordinates, where a build resolving `org.gwtbootstrap3:*` could pick up either. Maven Central would refuse it anyway, since it verifies that a publisher owns the namespace, but the reason to move is the clash rather than the rule.

The two tracks treat Java packages differently, on purpose.

The Bootstrap 3 track keeps **`org.gwtbootstrap3.*` unchanged**. That is what makes it a drop-in: an existing GwtBootstrap3 application swaps its two dependency coordinates and touches no source. The corollary is that upstream GwtBootstrap3 and this fork must never both be on the same classpath, since they declare the same classes.

The Bootstrap 5 track moves to **`io.instanto.bootstrap5.*`**. Nothing depends on it yet, so there is no drop-in compatibility to preserve, and the Bootstrap 5 widgets are not a version of anything upstream published. Upstream's copyright headers stay on every file they apply to, as the Apache License requires; the namespace changed, the attribution did not.

### Artifact naming

Artifacts are named `<backend>-<library>`, so the backend is the first thing you read:

| | Bootstrap 3 | Bootstrap 5 |
|---|---|---|
| GWT | `gwt-bootstrap3` (`-extras`, `-themes`) | `gwt-bootstrap5` (`-extras`, `-themes`) |
| TeaVM | `teavm-bootstrap3` | `teavm-bootstrap5` |

`teavm-gwt-compat` carries the two backends' shared seam: a minimal `com.google.gwt.*` client API backed by TeaVM DOM calls, so the widget sources compile unchanged on both.

The TeaVM builds are **separate artifactIds, not a `teavm` classifier**, because the two variants do not share a dependency set. A classifier attaches a second jar to the *same* groupId:artifactId:version, and a GAV has exactly one POM — so both jars would advertise the same dependencies. The GWT jar needs `org.gwtproject:gwt-user` and `gwt-dev` and must not drag TeaVM onto a consumer's classpath; the TeaVM jar needs `teavm-jso`, `teavm-jso-apis`, `teavm-classlib` and `teavm-gwt-compat` and must not drag in `gwt-user`. Maven has no variant-aware resolution to tell those apart (that is a Gradle module-metadata feature), so a classifier would hand every consumer both toolchains. Classifiers here are reserved for what they are for: `sources` and `javadoc`.

## Showcases

- [Bootstrap 3-compatible GWT showcase](https://cstainton.github.io/gwtbootstrap/)
- [Bootstrap 5-native GWT showcase](https://cstainton.github.io/gwtbootstrap/bootstrap5/)
- [TeaVM Bootstrap 3 smoke page](https://cstainton.github.io/gwtbootstrap/teavm.html)
- [TeaVM Bootstrap 5 smoke page](https://cstainton.github.io/gwtbootstrap/teavm-bootstrap5.html)

## Status

This project is under active migration.

The Bootstrap 3 compatibility build and original showcase compile with GWT 2.13.1. The Bootstrap 5 module is a native widget track with top-level coverage for the current GwtBootstrap3 widget catalogue. Its compiled showcase covers layout, buttons, cards, alerts, badges, list groups, linked groups, typography helpers, icons, images, thumbnails, dropdowns, modals, navbars, navs, tabs, progress bars, input groups, form adapters, value boxes, radio groups, collapse, tooltips, popovers, carousels, pagination, and Bootstrap 3 panel/well concepts mapped to Bootstrap 5 card/utility idioms. The TeaVM modules are experimental and currently contain compile-checked subsets for Bootstrap 3-compatible and Bootstrap 5-native rendering.

## Migration Paths

Use the Bootstrap 3 artifacts when you want the safest replacement for an existing GwtBootstrap3 dependency:

```xml
<dependency>
  <groupId>io.instanto</groupId>
  <artifactId>gwt-bootstrap3</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>

<dependency>
  <groupId>io.instanto</groupId>
  <artifactId>gwt-bootstrap3-extras</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

Use the Bootstrap 5 artifact when you are ready to migrate templates and code to Bootstrap 5 concepts:

```xml
<dependency>
  <groupId>io.instanto</groupId>
  <artifactId>gwt-bootstrap5</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

TeaVM Bootstrap 3 experiment:

```xml
<dependency>
  <groupId>io.instanto</groupId>
  <artifactId>teavm-bootstrap3</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

TeaVM Bootstrap 5 experiment:

```xml
<dependency>
  <groupId>io.instanto</groupId>
  <artifactId>teavm-bootstrap5</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

## Modules

- `gwt-bootstrap3`: GwtBootstrap3-compatible core widgets backed by Bootstrap 3.4.1 and jQuery 3.7.1.
- `gwt-bootstrap3-extras`: GwtBootstrap3-compatible extras widgets and third-party integrations.
- `gwt-bootstrap3-showcase`: original GWT showcase used for visual and compile testing of the Bootstrap 3 compatibility modules.
- `gwt-bootstrap5`: Bootstrap 5-native GWT widgets and resources under `io.instanto.bootstrap5.*`.
- `gwt-bootstrap5-showcase`: Bootstrap 5-native GWT showcase.
- `teavm-gwt-compat`: shared minimal GWT client API subset backed by TeaVM DOM APIs.
- `teavm-bootstrap3`: experimental Bootstrap 3-compatible TeaVM widget backend using `teavm-gwt-compat`, without UiBinder.
- `teavm-bootstrap5`: experimental Bootstrap 5-native TeaVM widget backend using `teavm-gwt-compat`, without UiBinder.

Planned module:

- `teavm-bootstrap5-showcase`: fuller TeaVM showcase for the Bootstrap 5-native widget set.

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
mvn -f gwt-bootstrap3-showcase/pom.xml -DskipTests -Dgwt.forceCompilation=true gwt:compile
```

Compile the Bootstrap 5-native showcase:

```bash
mvn -f gwt-bootstrap5-showcase/pom.xml -DskipTests -Dgwt.forceCompilation=true gwt:compile
```

Compile the TeaVM Bootstrap 3 smoke target:

```bash
mvn -f teavm-bootstrap3/pom.xml -DskipTests package
```

Compile the TeaVM Bootstrap 5 smoke target:

```bash
mvn -f teavm-bootstrap5/pom.xml -DskipTests package
```

## Showcase Details

GitHub Pages serves the Bootstrap 3-compatible showcase:

[https://cstainton.github.io/gwtbootstrap/](https://cstainton.github.io/gwtbootstrap/)

The Bootstrap 5-native GWT showcase is published at:

[https://cstainton.github.io/gwtbootstrap/bootstrap5/](https://cstainton.github.io/gwtbootstrap/bootstrap5/)

The TeaVM Bootstrap 3 smoke page is published at:

[https://cstainton.github.io/gwtbootstrap/teavm.html](https://cstainton.github.io/gwtbootstrap/teavm.html)

The TeaVM Bootstrap 5 smoke page is published at:

[https://cstainton.github.io/gwtbootstrap/teavm-bootstrap5.html](https://cstainton.github.io/gwtbootstrap/teavm-bootstrap5.html)

## Notes

- Java package names remain `org.gwtbootstrap3.*` for source compatibility in the Bootstrap 3 compatibility modules.
- Bootstrap 5 widgets use `io.instanto.bootstrap5.*` and should not change `org.gwtbootstrap3` semantics.
- GWT module names remain based on the original GwtBootstrap3 modules for the Bootstrap 3 compatibility track.
- Browser assets are tracked in `THIRD-PARTY-ASSETS.md`.
