# GWT Bootstrap Modern Fork

This is a standalone fork of `org.gwtbootstrap3:gwt-bootstrap-modern` and `org.gwtbootstrap3:gwt-bootstrap-modern-extras`.

## Intent

- Preserve the original GwtBootstrap3 public Java API and GWT module names so GWT consumers can use it as a version update rather than a port.
- Move the implementation and bundled resources to Bootstrap 5.3.x, not Bootstrap 3 or Bootstrap 4.
- Keep the original widget design where practical: GWT widgets, familiar constants, UiBinder-friendly APIs, and the existing extras module split.
- Build a TeaVM-compatible backend in parallel by extracting DOM/plugin access behind small internal interfaces.

## Current Bootstrap Target

- Bootstrap: `5.3.8`
- Bootstrap Icons: `1.13.1`
- Bootbox: `6.0.4`
- Bootstrap Datepicker: `1.10.1`
- Temporary jQuery compatibility bridge: `3.7.1`

The compatibility bridge exists only to keep legacy JSNI plugin calls working while internals are migrated toward adapter-backed Bootstrap 5 integrations.

## Compatibility Approach

This fork should stay visually and structurally close to the original GwtBootstrap3 project. The first release line is intentionally an update fork:

- Keep existing package names and GWT module names, and widget APIs for the GWT artifacts.
- Prefer Bootstrap 3 class compatibility CSS over changing Java widgets where it preserves behaviour.
- Replace bundled JavaScript/CSS resources with Bootstrap 5-compatible versions where a compatible upstream exists.
- Introduce internal adapters for runtime access so the same widget concepts can be backed by GWT DOM or TeaVM JSO.

## Build

Build the full reactor, including the GWT-compatible artifacts and the TeaVM backend artifact:

```bash
mvn -f pom.xml -DskipTests install
```

Run the original showcase demo as a real GWT compile compatibility gate:

```bash
mvn -f gwt-bootstrap-modern-showcase/pom.xml -DskipTests -Dgwt.forceCompilation=true gwt:compile
```

Verify the TeaVM backend directly. This compiles `TeaVmSmokeApp` to JavaScript via TeaVM:

```bash
mvn -f teavm-bootstrap-modern/pom.xml -DskipTests package
ls teavm-bootstrap-modern/target/teavm/teavm-bootstrap-modern-smoke.js
```

Browser assets are vendored and pinned in `THIRD-PARTY-ASSETS.md`. npm is a maintainer-side source for refreshing those vendored files only; it is not part of the Maven build path for consumers.

## Modernization Notes

- `PORTING.md` records the Bootstrap 5 and runtime migration strategy.
- `EXTRAS-INVENTORY.md` records each extras plugin, current fork state, candidate updates, and validation gates.
- `THIRD-PARTY-ASSETS.md` records browser asset versions and provenance.

## Modules

- `gwt-bootstrap-modern`: GWT `org.gwtbootstrap3:gwt-bootstrap-modern` core artifact.
- `gwt-bootstrap-modern-extras`: GWT `org.gwtbootstrap3:gwt-bootstrap-modern-extras` artifact.
- `gwt-bootstrap-modern-showcase`: upstream showcase demo, used as a GWT consumer compatibility gate and GitHub Pages source.
- `teavm-bootstrap-modern`: TeaVM-compilable widget backend prototype for the same Bootstrap widget concepts.

## Consumer Shape

There are two intended consumer tracks:

- GWT track: consumers should eventually be able to move from the original artifact IDs to the modern artifact IDs while keeping source packages and GWT module names stable.
- TeaVM track: consumers use `org.gwtbootstrap3:teavm-bootstrap-modern` while the DOM/plugin adapter matures toward source-compatible widget facades.

```xml
<dependency>
  <groupId>org.gwtbootstrap3</groupId>
  <artifactId>gwt-bootstrap-modern</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
<dependency>
  <groupId>org.gwtbootstrap3</groupId>
  <artifactId>gwt-bootstrap-modern-extras</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

## TeaVM Direction

Do not try to reimplement all of Elemental2 on TeaVM as the primary strategy. The safer path is a small internal DOM facade with two implementations:

- GWT implementation backed by GWT DOM/JsInterop.
- TeaVM implementation backed by TeaVM JSO bindings, potentially generated from a small WebIDL subset.

The TeaVM artifact should become a backend for the GwtBootstrap widget model, not a permanently divergent UI library.
