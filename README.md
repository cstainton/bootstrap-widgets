# GWT Bootstrap Modern

GWT Bootstrap Modern is a fork of GwtBootstrap3 that brings the library inline with modern Bootstrap while keeping the existing GWT widget API and module names where possible.

The main target is GWT applications that currently use GwtBootstrap3 and need a maintained Bootstrap 5 based version.

## Status

This project is under active migration.

The GWT build and showcase compile with GWT 2.13.1. The TeaVM module is experimental and currently contains a small compile-checked widget subset.

## Artifacts

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

TeaVM experiment:

```xml
<dependency>
  <groupId>org.gwtbootstrap3</groupId>
  <artifactId>teavm-bootstrap-modern</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

## Modules

- `gwt-bootstrap-modern`: core GWT widgets.
- `gwt-bootstrap-modern-extras`: extras widgets and third-party integrations.
- `gwt-bootstrap-modern-showcase`: GWT showcase used for visual and compile testing.
- `teavm-bootstrap-modern`: experimental TeaVM build target.

## Build

Build and install the Maven reactor:

```bash
mvn -f pom.xml -DskipTests install
```

Compile the GWT showcase:

```bash
mvn -f gwt-bootstrap-modern-showcase/pom.xml -DskipTests -Dgwt.forceCompilation=true gwt:compile
```

Compile the TeaVM smoke target:

```bash
mvn -f teavm-bootstrap-modern/pom.xml -DskipTests package
```

## Showcase

GitHub Pages serves the GWT showcase:

https://cstainton.github.io/gwtbootstrap-modern/

## Notes

- Java package names remain `org.gwtbootstrap3.*` for source compatibility.
- GWT module names remain based on the original GwtBootstrap3 modules.
- Bootstrap assets and compatibility details are tracked in `PORTING.md` and `THIRD-PARTY-ASSETS.md`.
