# Bootstrap Widgets

Bootstrap Widgets provides Bootstrap 3 and Bootstrap 5 widget libraries for GWT and TeaVM.

The Bootstrap 3 build is a maintained replacement for
[GwtBootstrap3](https://github.com/gwtbootstrap3/gwtbootstrap3). It keeps the original Java packages,
GWT module names, markup, and behaviour while updating the toolchain and browser dependencies. The
Bootstrap 5 build provides a migration path with native Bootstrap 5 markup and JavaScript behaviour.

| Track | Purpose | Java packages |
|---|---|---|
| GWT Bootstrap 3 | Drop-in maintenance build for existing applications | `org.gwtbootstrap3.*` |
| GWT Bootstrap 5 | Bootstrap 5-native migration target | `io.instanto.bootstrap5.*` |
| TeaVM Bootstrap 3 | Bootstrap 3 widgets compiled from the GWT sources | `org.gwtbootstrap3.*` |
| TeaVM Bootstrap 5 | Bootstrap 5 widgets compiled from the GWT sources | `io.instanto.bootstrap5.*` |

Current core versions are GWT 2.13.1, TeaVM 0.15.0, Bootstrap 3.4.1, Bootstrap 5.3.8, and jQuery
3.7.1. Bootstrap 5 does not use jQuery.

## Showcases

- [GWT Bootstrap 3](https://cstainton.github.io/bootstrap-widgets/)
- [GWT Bootstrap 5](https://cstainton.github.io/bootstrap-widgets/bootstrap5/)
- [TeaVM Bootstrap 3](https://cstainton.github.io/bootstrap-widgets/teavm.html)
- [TeaVM Bootstrap 5](https://cstainton.github.io/bootstrap-widgets/teavm-bootstrap5.html)

The GWT and TeaVM showcases use the same widget and showcase sources where possible. This makes
differences between the compilers visible instead of hiding them behind separate demos.

## Choosing An Artifact

All artifacts use the `io.instanto` group ID and currently publish as `1.0-SNAPSHOT`.

| Runtime | Bootstrap 3 | Bootstrap 5 |
|---|---|---|
| GWT | `gwt-bootstrap3` | `gwt-bootstrap5` |
| GWT extras | `gwt-bootstrap3-extras` | `gwt-bootstrap5-extras` |
| GWT themes | `gwt-bootstrap3-themes` | `gwt-bootstrap5-themes` |
| TeaVM | `teavm-bootstrap3` | `teavm-bootstrap5` |

Use `gwt-bootstrap3` when updating an existing GwtBootstrap3 application. Only the Maven group ID
and version need to change. Do not put the original GwtBootstrap3 artifact and this replacement on
the same classpath because both contain `org.gwtbootstrap3.*` classes.

Use `gwt-bootstrap5` for new Bootstrap 5 code or when migrating an application. Its API follows the
same widget composition and event-handling model where that still fits Bootstrap 5, but it is not a
drop-in replacement. Templates, styles, and removed Bootstrap 3 concepts may need changes. See
[BOOTSTRAP5-PORTING.md](BOOTSTRAP5-PORTING.md) for current coverage.

TeaVM artifacts have separate names because they use TeaVM libraries and `teavm-gwt-compat` instead
of `gwt-user`. The build rejects `gwt-user` and `gwt-dev` on TeaVM module classpaths.

## TeaVM Support

The TeaVM builds compile the corresponding GWT widget sources. They do not maintain a second copy of
the widget API. `teavm-gwt-compat` implements the part of the GWT client API used by those sources and
provides TeaVM-backed DOM, events, widgets, history, scheduling, and resource support.

UiBinder templates are supported through the `widget-processor` annotation processor. It generates
ordinary Java during compilation, including widget construction, fields, handlers, constructors,
enum attributes, and the template features used by the shared showcases.

The `teavm-module-maven-plugin` reads GWT module and ClientBundle declarations and generates TeaVM
resource loaders. Scripts load in declaration order, expose a readiness result, report failures, and
are not loaded again when the host application already provides them.

This compatibility layer covers what these widget libraries currently use; it is not a complete
replacement for all of GWT. In particular, the TeaVM Bootstrap 3 showcase currently runs 41 of the
original 55 pages. The remaining 14 pages depend on old extras with extensive JSNI code. Bootstrap 5
extras are being moved to explicit JavaScript seams that both compilers can implement.

## Using GitHub Packages

Add the package repository to the consuming build:

```xml
<repository>
  <id>github</id>
  <url>https://maven.pkg.github.com/cstainton/bootstrap-widgets</url>
  <snapshots>
    <enabled>true</enabled>
  </snapshots>
</repository>
```

GitHub Packages requires GitHub credentials in Maven `settings.xml`, including for public packages.
Then add the artifact for the track you want, for example:

```xml
<dependency>
  <groupId>io.instanto</groupId>
  <artifactId>gwt-bootstrap3</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

## Build

CI uses Java 21; the libraries target Java 17 bytecode.

Build and install the complete reactor:

```bash
mvn -DskipTests install
```

Compile either GWT showcase directly:

```bash
mvn -f gwt/gwt-bootstrap3-showcase/pom.xml -DskipTests -Dgwt.forceCompilation=true gwt:compile
mvn -f gwt/gwt-bootstrap5-showcase/pom.xml -DskipTests -Dgwt.forceCompilation=true gwt:compile
```

Compile either TeaVM library and showcase:

```bash
mvn -f teavm/teavm-bootstrap3/pom.xml -DskipTests package
mvn -f teavm/teavm-bootstrap5/pom.xml -DskipTests package
```

Published library artifacts include source JARs. GWT core and extras artifacts also include
Javadoc JARs, which are published with the showcases. The GWT and TeaVM showcase builds publish
JavaScript source maps and the corresponding Java source trees.

## Tests

The test suite includes:

- shared Gherkin behaviour specifications based on the original GwtBootstrap3 showcase;
- API contracts run against both `gwt-user` and `teavm-gwt-compat`;
- compiled GWT and TeaVM widget fixtures;
- real-browser showcase smoke tests and mobile touch tests.

[TESTING-PLAN.md](TESTING-PLAN.md) describes the test families, tags, coverage rules, and remaining
work.

## Repository Layout

| Path | Contents |
|---|---|
| `gwt/` | Bootstrap 3 and 5 libraries, extras, themes, showcases, fixtures, and GWT tests |
| `teavm/` | GWT compatibility layer, Bootstrap 3 and 5 TeaVM builds, and TeaVM tests |
| `widget-processor/` | UiBinder annotation processor used by TeaVM builds |
| `teavm-module-plugin/` | Maven plugin that converts GWT module resources for TeaVM |
| `testing/` | Shared behaviour specifications, fixture identities, and API contracts |
| `showcase-site/` | GitHub Pages assembly for all four showcases |

Third-party browser assets and their versions are listed in
[THIRD-PARTY-ASSETS.md](THIRD-PARTY-ASSETS.md).
