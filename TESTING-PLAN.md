# GWT and TeaVM Widget Testing Plan

## Status

This document records the agreed direction for testing the Bootstrap 3 and
Bootstrap 5 widget tracks under both GWT and TeaVM.

The selected GWT execution strategy is generated `GWTTestCase` tests. A
TeaVM-hosted runner that executes GWT test code inside a frame is deliberately
deferred. Frames are used for black-box testing of already rendered
applications, not as the initial GWT Java test runner.

## Decisions

1. Gherkin feature files describe shared widget behaviour.
2. Cucumber Tea generates the executable test cases.
3. GWT Java contracts run through generated `GWTTestCase` classes.
4. TeaVM Java contracts run through `TeaVMTestRunner` in Chromium.
5. `mockatcha-dom` drives widgets mounted by TeaVM tests and applications
   rendered inside same-origin frames.
6. Rendered-interface tests remain independent of the technology that produced
   the page.
7. `teavm-gwt-compat` receives a separate conformance suite that is also used
   by the Bootstrap widgets as a representative consumer.
8. A TeaVM-hosted GWT test runner is out of scope until the generated
   `GWTTestCase` approach has demonstrated a concrete limitation.
9. Playwright is not required for the primary test suites. It remains an
   optional deployment-level tool if the frame host cannot cover a future
   browser or hosting requirement.

## Goals

- Execute the same behavioural specifications against GWT and TeaVM widgets.
- Detect compile-time, Java API, event, lifecycle, DOM and Bootstrap JavaScript
  regressions.
- Exercise every exported widget and important optional code path under both
  compilers.
- Compare `teavm-gwt-compat` behaviour with real `gwt-user` behaviour.
- Test GWT, TeaVM and third-party rendered interfaces through one DOM-facing
  test API.
- Fail CI on browser exceptions, missing assets, incomplete service bindings
  and inaccessible markup.
- Produce a readable feature-to-target coverage report.

## Non-goals

- Pixel-identical Bootstrap 3 and Bootstrap 5 output.
- Inspecting arbitrary cross-origin websites from a test frame.
- Reimplementing Playwright, Selenium or a general browser automation system.
- Making `mockatcha-dom` compile under GWT.
- Replacing `GWTTestCase` before the initial contract suite is operational.

## Test Families

| Family | Executor | Subject | Assertions | GWTTestCase |
| --- | --- | --- | --- | --- |
| GWT Java widget contracts | Cucumber Tea GWT target | GWT widgets compiled with `gwt-user` | Java API and GWT DOM | Generated implementation detail |
| TeaVM Java widget contracts | Cucumber Tea TeaVM target | Shared widgets compiled with `teavm-gwt-compat` | Java API, compatibility API and DOM | No |
| Direct TeaVM rendering | `TeaVMTestRunner` in Chromium | Widget mounted in the test document | `mockatcha-dom` | No |
| Framed rendered-interface tests | `TeaVMTestRunner` in Chromium | GWT, TeaVM or third-party application | Frame-scoped `mockatcha-dom` | No |
| Compatibility conformance | GWT reference and TeaVM compatibility runners | `gwt-user` versus `teavm-gwt-compat` | Shared Java contracts | Reference side only |
| Deployment smoke | Chromium frame host | Assembled Pages artifact | Startup, routes, assets and console | No |

## Proposed Modules

### Cucumber Tea repository

| Module or area | Responsibility |
| --- | --- |
| `cucumber-tea` | Portable annotations, hooks and scenario execution |
| `cucumber-tea-codegen` | Add generated `GWTTestCase` support while retaining the existing TeaVM target |
| `cucumber-tea-browser-testkit` | Same-origin application hosting, frame lifecycle and browser diagnostics |
| `cucumber-tea-examples` | Demonstrate TeaVM and generated GWT suites from the same feature |

The browser testkit name is provisional. It should be a separate module if it
would otherwise add DOM or server concerns to the small Cucumber Tea runtime.

### Mockatcha repository

`mockatcha-dom` remains TeaVM/browser based. It gains support for scoping
queries and interactions to a same-origin child document. No
`mockatcha-gwt-dom` module is planned initially.

### GWT Bootstrap repository

| Module | Responsibility |
| --- | --- |
| `bootstrap-widget-specifications` | Shared `.feature` resources and coverage metadata |
| `bootstrap-widget-contracts` | Portable Java contracts used by compiler-specific runners |
| `gwt-bootstrap-widget-tests` | Generated GWT reference tests for Bootstrap 3 and Bootstrap 5 |
| `teavm-bootstrap-widget-tests` | Generated TeaVM tests for Bootstrap 3 and Bootstrap 5 |
| `bootstrap-showcase-browser-tests` | Framed tests against all compiled showcases and the assembled site |
| `teavm-gwt-compat-contracts` | GWT API conformance corpus shared by reference and compatibility runs |

These can begin as fewer integration-test modules and be split only when build
ordering or dependency isolation requires it. In particular, GWT and TeaVM
must never see both `gwt-user` and `teavm-gwt-compat` implementations of the
same `com.google.gwt.*` classes on one compiler classpath.

## Shared Feature Corpus

Feature files live in a small resource artifact so every runner reads the same
text. They describe behaviour rather than implementation classes or generated
markup.

Example:

```gherkin
Feature: Toggle buttons

  Scenario: activating a toggle button
    Given a toggle button labelled "Notifications" is mounted
    When the user activates the button
    Then the button value is true
    And the button is visibly active
    And the button has aria-pressed "true"
    And one value change is reported
```

Initial tags:

| Tag | Meaning |
| --- | --- |
| `@api` | Requires access to the Java widget API |
| `@rendered` | Can run against a completed rendered interface |
| `@javascript` | Requires Bootstrap JavaScript behaviour |
| `@layout` | Uses computed style or geometry |
| `@accessibility` | Checks roles, labels, focus or ARIA |
| `@compat` | Exercises a `teavm-gwt-compat` contract |
| `@bootstrap3` | Applies only to the Bootstrap 3 track |
| `@bootstrap5` | Applies only to the Bootstrap 5 track |
| `@browser` | Requires a real browser rather than a JVM-only pass |

The same feature may have compiler-specific glue where construction differs.
The assertion-bearing contract classes should remain shared wherever both
compilers expose the same API.

## Generated GWT Test Support

### Processor target

Add `gwt` to the accepted `cucumber.tea.runner` values. The generated class
uses JUnit 3 conventions required by GWT:

- Extend a Cucumber Tea GWT base derived from `GWTTestCase`.
- Generate public `test...()` methods instead of JUnit 4 `@Test` methods.
- Generate `getModuleName()` from suite configuration.
- Execute Cucumber Tea before hooks, steps and after hooks inside each test.
- Preserve undefined, ambiguous and duplicate-step failures at generation
  time.
- Keep TeaVM-only script annotations out of GWT output.

Proposed suite declaration:

```java
@CucumberSuite(
    value = "features/buttons.feature",
    gwtModule = "io.instanto.bootstrap5.tests.Bootstrap5Tests")
public final class Bootstrap5ButtonSteps {
    // Step definitions
}
```

Proposed generated shape:

```java
public final class Bootstrap5ButtonStepsCucumberTeaTest
        extends CucumberTeaGwtTestCase {

    @Override
    public String getModuleName() {
        return "io.instanto.bootstrap5.tests.Bootstrap5Tests";
    }

    public void testActivatingAToggleButton() throws Throwable {
        // Generated scenario orchestration
    }
}
```

### GWT module

Create one test module per Bootstrap generation. Each test module inherits the
corresponding widget and resource modules and includes generated test source.
Entry points are not assumed to run in `GWTTestCase`, so test setup must load
any required Bootstrap resources explicitly.

### Execution

Use the existing `gwt-maven-plugin:test` goal. Start with GWT's default
HtmlUnit run style for API, lifecycle and basic DOM contracts. Do not use that
run for Bootstrap plugin behaviour whose correctness depends on a modern
browser. Those scenarios belong in the Chromium frame suite.

### Asynchronous behaviour

The first GWT tranche remains synchronous. Later support can adapt
`delayTestFinish()` and `finishTest()` behind a Cucumber Tea asynchronous step
contract. Do not add polling or blocking emulation to ordinary generated
steps.

## Direct TeaVM Widget Tests

Cucumber Tea already generates JUnit 4 tests using `TeaVMTestRunner`. These
tests compile the reached application and test code to JavaScript and execute
it in Chromium.

Direct widget scenarios must:

1. Create a fresh `mockatcha-dom` container before each scenario.
2. Construct the widget through a statically referenced fixture catalogue.
3. Mount it using the shared GWT-compatible widget API.
4. Drive the rendered control with `mockatcha-dom`.
5. Assert Java values, event counts and rendered state.
6. Detach the widget and reset the container after each scenario.

The fixture catalogue is important because TeaVM reachability analysis only
compiles code reached by a test. Every exported widget needs at least a
construction, mount and detach scenario, and optional paths need explicit
references. Examples include continuous range input, modal events, validators,
resource lookup and `GWT.create()` implementations.

Bootstrap JavaScript and styles should be attached through Cucumber Tea test
configuration. Add a `CucumberStyle` facility parallel to `CucumberScript` if
styles cannot be loaded cleanly through the browser testkit.

## Framed Rendered-interface Tests

### Purpose

The frame suite treats the subject as a web application. Its implementation
may be GWT, TeaVM, React or another renderer. Test code stays in the parent
TeaVM test document and communicates with the child through the DOM.

```text
TeaVMTestRunner page
`-- same-origin iframe
    |-- GWT Bootstrap 3 showcase
    |-- GWT Bootstrap 5 showcase
    |-- TeaVM Bootstrap 3 showcase
    |-- TeaVM Bootstrap 5 showcase
    `-- another locally packaged interface
```

### Static application hosting

The test host must expose complete compiled application directories from the
same origin as the TeaVM test page. A single-file `ServeJS` mechanism is not
enough for a GWT application because its bootstrap script loads generated
permutations and resources by relative path.

Add configuration equivalent to:

```java
@CucumberWebApplication(
    name = "gwt-bootstrap5",
    root = "${gwt.bootstrap5.showcase.output}",
    startPage = "index.html")
```

Maven configuration may be preferable for build-directory paths that are not
compile-time constants. The annotation or generated metadata should refer to a
logical application name, not hard-code a workstation path.

### Frame lifecycle

The browser testkit provides:

```java
FramedApplication app = applications.open("gwt-bootstrap5", "#buttons");
DomScope page = app.awaitReady().page();
```

It must support:

- Frame creation and removal per scenario.
- Configurable route, query string, width and height.
- Load and readiness timeouts.
- A standard readiness marker or predicate.
- Frame-scoped roles, labels, text, values and test IDs.
- Computed style and geometry queries.
- Child `error` and `unhandledrejection` capture.
- Useful DOM, URL and console diagnostics on failure.
- Failure when a required asset returns an error.

### Same-origin boundary

The browser prevents a parent test from inspecting a cross-origin frame.
Third-party rendered interfaces are testable when their built output is copied
under the local test host or when an approved same-origin test proxy is used.
Remote production websites are not made inspectable by CORS and are outside
this mechanism.

## Mockatcha DOM Changes

`mockatcha-dom` remains the browser-facing API for direct TeaVM and frame
tests. Add document-rooted scoping without changing its existing default
document behaviour.

Candidate API:

```java
DomScope currentPage = Dom.page();
FrameScope frame = Dom.frame("#subject");
DomScope subjectPage = frame.awaitLoaded().page();
```

Required work:

1. Allow `DomScope` query operations to use either an element or document root.
2. Add safe access to a same-origin iframe document.
3. Add frame readiness and cleanup support.
4. Keep all interactions scoped so selectors cannot accidentally match the
   test runner page.
5. Add tests for two frames containing identical IDs.
6. Add tests for load failure, readiness timeout and child exceptions.
7. Produce a clear cross-origin error instead of leaking a browser exception.

`mockatcha-dom` is not used inside generated `GWTTestCase` code. GWT Java
contracts use GWT widget and DOM APIs directly. The rendered GWT application is
still driven by `mockatcha-dom` because the driver runs in the parent TeaVM
test page.

## TeaVM GWT Compatibility Contracts

### Purpose

`teavm-gwt-compat` currently exists primarily to compile GWT-oriented widget
source under TeaVM. Its behaviour should be checked against real GWT rather
than inferred from successful compilation.

### Contract arrangement

Write shared contract classes against `com.google.gwt.*` API names. Compile
and execute them in two isolated modules:

```text
Shared GWT API contract source
|-- GWT reference: gwt-user + generated GWTTestCase
`-- TeaVM implementation: teavm-gwt-compat + TeaVMTestRunner
```

The shared contract artifact treats `gwt-user` as a compile-time/provided API.
The TeaVM runner must exclude it and supply `teavm-gwt-compat`. The GWT runner
must not include `teavm-gwt-compat`.

### Initial compatibility areas

| Area | Representative contracts |
| --- | --- |
| Widget lifecycle | parent assignment, attach, detach, removal and handler order |
| Panels | add, insert, remove, clear, child ordering and indexed access |
| Values | `HasValue`, change suppression, explicit event firing and null handling |
| Events | bubbling, handler registration, handler removal and source identity |
| Controls | text, enabled, visible, focus, checkbox, radio and list selection |
| DOM | attributes, classes, properties, text, HTML, IDs and event dispatch |
| Scheduling | deferred commands, finally commands, timers and cancellation |
| Safe HTML | escaping, concatenation and safe URI handling |
| Deferred binding | successful registration and useful missing-binding failures |
| Data views | providers, ranges, sorting, selection and cell updates |
| History and storage | value changes, token behaviour and unavailable storage |

Ordinary unit tests should cover low-level edge cases. Gherkin should cover
behavioural contracts that are useful to a library consumer; it should not be
used merely to restate every getter and setter.

### Bootstrap as a consumer test

The Bootstrap TeaVM suites provide a second level of compatibility evidence.
They exercise composition, event handling, resource lookup and DOM behaviour
through a substantial GWT-oriented library rather than isolated compatibility
classes.

## Bootstrap Behaviour Inventory

The first full inventory should cover:

| Group | Behaviour |
| --- | --- |
| Basic widgets | construction, text/HTML, style, visibility, enabled state |
| Buttons | types, sizes, groups, loading state, toggle and checkbox buttons |
| Forms | labels, values, validation, radio groups, input groups and submission |
| Navigation | links, breadcrumbs, pagination, navs, tabs and navbar collapse |
| Overlays | dropdown, dropup, modal, tooltip, popover and disposal |
| Dynamic widgets | collapse, accordion, carousel, progress and range input |
| Data widgets | suggest box, list box, cell list, cell table, tree and data grid |
| Layout | grid, responsive utilities, media objects, images and containers |
| Accessibility | names, roles, focus, keyboard control and ARIA state |
| Lifecycle | repeated mounting, route changes, detach and event unbinding |
| Resources | Bootstrap assets, icons, themes, service providers and source maps |

Bootstrap 3 and Bootstrap 5 should share semantics where practical. Visual
assertions should allow the frameworks' native styling to differ. GWT and
TeaVM output for the same Bootstrap generation should be held to a closer
structural and behavioural standard.

## Build and CI

### Pull requests

1. Build the reactor without globally skipping tests.
2. Run Cucumber Tea parser and code-generation unit tests.
3. Run the GWT compatibility reference suite.
4. Run the TeaVM compatibility suite in Chromium.
5. Run direct TeaVM core widget scenarios.
6. Build all four showcases.
7. Run a core framed scenario set against GWT 5 and TeaVM 5.

### Main branch

1. Run the full framed matrix for Bootstrap 3 and Bootstrap 5 under both
   compilers.
2. Run Bootstrap JavaScript and accessibility scenarios.
3. Assemble the exact GitHub Pages artifact.
4. Run startup, route, asset and console checks against that artifact before
   deployment.
5. Publish scenario and target coverage as a workflow artifact.

### Scheduled full run

1. Exercise every widget fixture and optional path.
2. Run mobile, tablet and desktop frame sizes.
3. Record screenshots for selected stable pages.
4. Archive page HTML and browser diagnostics for failures.
5. Report untested widgets, contracts and targets.

## Delivery Sequence

### Phase 1: Prove dual Cucumber execution

1. Add the shared feature-resource module.
2. Add the Cucumber Tea `gwt` generator target.
3. Generate and run one `GWTTestCase` toggle-button scenario.
4. Run the same feature through `TeaVMTestRunner`.
5. Verify that both results appear in Maven test reports.

Exit condition: one feature executes through both compilers from one feature
resource, with no manually maintained generated test class.

### Phase 2: Establish direct widget coverage

1. Add the TeaVM fixture catalogue.
2. Cover button, checkbox, radio, value and handler behaviour.
3. Add attach/detach and repeated-mount scenarios.
4. Add service binding and missing-binding failure tests.

Exit condition: the previously observed service descriptor, range and form
failures would all be detected automatically.

### Phase 3: Add the frame host

1. Serve a complete static fixture application directory.
2. Load a minimal fixture in a same-origin iframe.
3. Add document-rooted Mockatcha queries.
4. Add readiness, console and error handling.
5. Run one scenario against both compiled Bootstrap 5 showcases.

Exit condition: one Cucumber Tea scenario drives both a GWT-rendered and a
TeaVM-rendered application through `mockatcha-dom`.

### Phase 4: Add compatibility conformance

1. Create shared lifecycle, value and event contracts.
2. Run them against real `gwt-user` through generated `GWTTestCase` classes.
3. Run them against `teavm-gwt-compat` through `TeaVMTestRunner`.
4. Resolve or document semantic differences.

Exit condition: compatibility claims are supported by an executable reference
comparison rather than class inventory alone.

### Phase 5: Expand the behaviour matrix

1. Cover the Bootstrap widget inventory by functional area.
2. Add Bootstrap JavaScript components.
3. Add accessibility and responsive layout scenarios.
4. Add Bootstrap 3 reference scenarios where behaviour is shared.
5. Record deliberate Bootstrap 5 differences alongside the relevant feature.

Exit condition: every exported widget has compile/mount coverage and every
interactive widget has at least one behavioural scenario.

### Phase 6: Gate publication

1. Replace CI's JavaScript-file existence checks with executable tests.
2. Test the assembled Pages directory before upload.
3. Fail publication on startup, route, asset or browser-console errors.
4. Publish the coverage matrix and diagnostics.

Exit condition: a showcase cannot be published merely because its compiler
produced a JavaScript file.

## Deferred Option: TeaVM-hosted GWT Contract Runner

A future runner could generate a GWT test application, execute contracts in a
frame and report structured results to the TeaVM parent through `postMessage`.
That could remove `GWTTestCase` and run all contracts in Chromium.

It is intentionally deferred because it requires generated GWT entry points,
cross-frame result transport, assertion serialization, timeout handling and
source-location reporting. The generated `GWTTestCase` path is smaller and
provides an established GWT reference implementation now.

## Definition of Done

- One shared feature corpus runs against GWT and TeaVM.
- Generated GWT tests require no handwritten `GWTTestCase` subclasses.
- Direct TeaVM tests use `mockatcha-dom` in Chromium.
- Framed tests use `mockatcha-dom` against GWT, TeaVM and packaged third-party
  interfaces.
- `teavm-gwt-compat` is tested against real GWT behaviour.
- Every exported widget is constructed, mounted and detached by at least one
  compiler test.
- Every interactive widget has an observable behavioural scenario.
- Browser exceptions and missing assets fail the build.
- The assembled showcase site is tested before publication.
- The CI report identifies untested widgets, behaviours and targets.
