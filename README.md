# GwtBootstrap3 Modern Fork

This is a standalone fork of `org.gwtbootstrap3:gwtbootstrap3` and `org.gwtbootstrap3:gwtbootstrap3-extras`.

## Intent

- Preserve the original GwtBootstrap3 public Java API and GWT module names so consumers can use it as a version update rather than a port.
- Move the implementation and bundled resources to Bootstrap 5.3.x, not Bootstrap 3 or Bootstrap 4.
- Keep the original widget design where practical: GWT widgets, familiar constants, UiBinder-friendly APIs, and the existing extras module split.
- Add a narrow DOM/plugin abstraction as internals are modernized, allowing a future TeaVM backend without exposing TeaVM details to consumers.

## Current Bootstrap Target

- Bootstrap: `5.3.8`
- Bootstrap Icons: `1.13.1`
- Bootbox: `6.0.4`
- Bootstrap Datepicker: `1.10.1`
- Temporary jQuery compatibility bridge: `3.7.1`

The compatibility bridge exists only to keep legacy JSNI plugin calls working while internals are migrated toward Elemental2/JsInterop.

## Compatibility Approach

This fork should stay visually and structurally close to the original GwtBootstrap3 project. The first release line is intentionally an update fork:

- Keep existing package names, artifact IDs, GWT module names, and widget APIs.
- Prefer Bootstrap 3 class compatibility CSS over changing Java widgets where it preserves behaviour.
- Replace bundled JavaScript/CSS resources with Bootstrap 5-compatible versions where a compatible upstream exists.
- Only introduce internal adapters for runtime access; do not expose Domino, Elemental2, TeaVM, or another UI model through the public API.

## Build

```bash
mvn -f pom.xml -DskipTests package
```

Browser assets are vendored and pinned in `THIRD-PARTY-ASSETS.md`. npm is a maintainer-side source for refreshing those vendored files only; it is not part of the Maven build path for consumers.

Run the original showcase demo as a real GWT compile compatibility gate:

```bash
mvn -f gwtbootstrap3-showcase/pom.xml -DskipTests -Dgwt.forceCompilation=true gwt:compile
```

## Modernization Notes

- `PORTING.md` records the Bootstrap 5 and runtime migration strategy.
- `EXTRAS-INVENTORY.md` records each extras plugin, current fork state, candidate updates, and validation gates.
- `THIRD-PARTY-ASSETS.md` records browser asset versions and provenance.

## Modules

- `gwtbootstrap3-modern`: drop-in `org.gwtbootstrap3:gwtbootstrap3` core artifact.
- `gwtbootstrap3-extras-modern`: drop-in `org.gwtbootstrap3:gwtbootstrap3-extras` artifact.
- `gwtbootstrap3-showcase`: upstream showcase demo, used as a consumer compatibility gate and GitHub Pages source.
- `gwtbootstrap3-teavm`: experimental TeaVM sidecar for DOM/Bootstrap interop work.

## Consumer Shape

Existing consumers should eventually be able to replace only the version:

```xml
<dependency>
  <groupId>org.gwtbootstrap3</groupId>
  <artifactId>gwtbootstrap3</artifactId>
  <version>0.9.5-graffica-SNAPSHOT</version>
</dependency>
<dependency>
  <groupId>org.gwtbootstrap3</groupId>
  <artifactId>gwtbootstrap3-extras</artifactId>
  <version>0.9.5-graffica-SNAPSHOT</version>
</dependency>
```

## TeaVM Direction

Do not try to reimplement all of Elemental2 on TeaVM as the primary strategy. The safer path is a small internal DOM facade with two implementations:

- GWT implementation backed by Elemental2/JsInterop.
- TeaVM implementation backed by TeaVM JSO bindings, potentially generated from a small WebIDL subset.
