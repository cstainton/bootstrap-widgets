# Layout and Style Assertion Requirements

Requirements for the style and geometry assertions the Bootstrap widget browser
tests need. This is the consumer contract.

The capability splits in two, and the split is worth making before anything is
implemented.

| | Owns | Knows about |
|---|---|---|
| `mockatcha-layout` | rectangles and the relations between them | nothing |
| `mockatcha-dom` | computed style, obtaining a rectangle from an element | the DOM |

Everything in the first row is arithmetic on four doubles. Nothing in it mentions an
element, a document or a browser. Keeping it separate is not tidiness; it is what
makes the fiddly part testable, and it is what lets a second testkit reuse it.

## Why split it

**The hard part is the predicates, not the measuring.** Reading
`getBoundingClientRect()` is one call. The part that will be wrong is the relation
semantics: whether "below" is inclusive at the tolerance boundary, and that touching
requires the edges to meet *and* the perpendicular ranges to overlap. That wants a
table of cases — touching but not overlapping, overlapping but not touching, negative
gaps, zero-sized boxes, either side of the tolerance. Welded to `HTMLElement` every
one of those costs a browser. As plain rectangles they are ordinary JVM unit tests.

**A second consumer already exists.** `sarto-scene-testkit` has `SceneDriver`,
`NodeQuery` and `SceneLookup`, with `withId`, `withText`, `withStyleClass`,
`visible()` and `enabled()` — the same vocabulary `DomScope` and `Expect` provide for
the DOM, over a different substrate. It has the same gap. A scene node reports
`getLayoutX()`, `getLayoutY()`, `getWidth()` and `getHeight()`, which is a rectangle,
so the relations apply unchanged. Two testkits needing the identical assertions is
the argument for putting them in neither.

**Diagnostics belong with the arithmetic.** "Both rectangles and the tolerance" is
rectangle-level reporting. Only naming the element is DOM-level.

---

# Part 1: `mockatcha-layout`

No dependencies. No DOM, no scene graph, no browser. Runnable on the JVM.

## The box

```java
LayoutBox box = LayoutBox.of(left, top, width, height);

double left = box.left();
double top = box.top();
double right = box.right();
double bottom = box.bottom();
double width = box.width();
double height = box.height();
```

Immutable, a value captured at the time of the call. It must not hold a live
JavaScript object, a scene node, or anything that can change underneath the caller.

Zero-sized boxes are permitted and are not an error: a `display:none` element and an
unlaid-out node both legitimately measure zero. Negative width or height is rejected.

## The relations

`a` is the subject, `b` the reference, `t` the tolerance. All comparisons are
inclusive within the tolerance.

| Assertion | Holds when |
|---|---|
| `hasWidth(a, w, t)` | `abs(a.width - w) <= t` |
| `hasHeight(a, h, t)` | `abs(a.height - h) <= t` |
| `hasSameWidth(a, b, t)` | `abs(a.width - b.width) <= t` |
| `hasSameHeight(a, b, t)` | `abs(a.height - b.height) <= t` |
| `isBelow(a, b, t)` | `a.top + t >= b.bottom` |
| `isAbove(a, b, t)` | `a.bottom - t <= b.top` |
| `isLeftOf(a, b, t)` | `a.right - t <= b.left` |
| `isRightOf(a, b, t)` | `a.left + t >= b.right` |
| `isLeftAlignedWith(a, b, t)` | `abs(a.left - b.left) <= t` |
| `isTopAlignedWith(a, b, t)` | `abs(a.top - b.top) <= t` |
| `touchesVertically(a, b, t)` | edges meet **and** horizontal ranges overlap |
| `touchesHorizontally(a, b, t)` | edges meet **and** vertical ranges overlap |

Touching is the only pair that is not a single comparison, so state it exactly:

- `touchesVertically`: `abs(a.bottom - b.top) <= t` or `abs(b.bottom - a.top) <= t`,
  **and** `min(a.right, b.right) - max(a.left, b.left) > -t`.
- `touchesHorizontally`: `abs(a.right - b.left) <= t` or `abs(b.right - a.left) <= t`,
  **and** `min(a.bottom, b.bottom) - max(a.top, b.top) > -t`.

Without the overlap condition, two boxes at opposite corners of the screen "touch"
whenever their edges happen to share a coordinate, which is the mistake this
specification exists to prevent.

Default tolerance is `0.5` CSS pixels: tight enough to catch a layout gap, loose
enough for sub-pixel rounding. Every relation takes an explicit non-negative
tolerance as an overload. A negative tolerance is an argument error.

Right-alignment, bottom-alignment and centre-alignment are deliberately absent. Add
them when a test needs one, with the same shape.

## Diagnostics

A failure states the relation, both complete rectangles, and the tolerance. Never
only `true`/`false`, never rounded integers, never an opaque object.

```
expected [left=12.0 top=40.0 right=92.0 bottom=74.0] to touch horizontally
         [left=96.5 top=40.0 right=180.0 bottom=74.0] within 0.5
         horizontal gap was 4.5
```

Reporting the measured gap, not just the failure, is what makes a layout failure
diagnosable without opening a browser.

## Acceptance tests

Plain JUnit on the JVM — no runner, no browser:

1. Each relation passes just inside its tolerance boundary and fails just outside.
2. Boxes that touch but do not overlap in the perpendicular axis are not touching.
3. Boxes that overlap but whose edges do not meet are not touching.
4. Overlapping boxes with a negative gap within tolerance are touching.
5. Zero-sized boxes compare without throwing.
6. Fractional coordinates are preserved and compared without rounding.
7. Negative tolerance, negative extents and null boxes are argument errors.
8. Every failure message contains both rectangles and the tolerance.

---

# Part 2: `mockatcha-dom`

Adds the DOM. Depends on `mockatcha-layout`.

## Computed style

```java
String display = Dom.computedStyle(element, "display");
String marker = Dom.computedStyle(element, "::before", "content");
```

- Read through `getComputedStyle(element, pseudoElement)` then
  `getPropertyValue(property)`.
- Trim surrounding whitespace, matching the existing `toHaveStyle`.
- Accept CSS property names and custom properties exactly as CSS spells them.
- `null` pseudo-element when none is requested.
- Reject a null element, a null or blank property, and a blank pseudo-element.
- Never silently fall back to inline `style` when computed style is unavailable.

The existing assertion stays as it is:

```java
expect(element).toHaveStyle("display", "flex");
```

## Obtaining a rectangle

```java
LayoutBox box = Dom.layout(element);
```

- Use `getBoundingClientRect()`. Not `offsetWidth`, `offsetTop`, or integer
  rounding.
- Viewport-relative CSS-pixel doubles, fractional values and transformed bounds
  preserved as the browser reports them.
- A detached or `display:none` element returns the browser's zero rectangle rather
  than failing.
- Reject a null element.

## Frames

Style and geometry are evaluated in the element's **owning** window, not the test
runner's global one:

```javascript
element.ownerDocument.defaultView.getComputedStyle(element, pseudoElement)
```

This is required for same-origin framed applications, which is how the compiled GWT
and TeaVM showcases will be driven.

Rectangle relations are valid only between elements of the same document. Comparing
across documents must fail with a coordinate-system error naming both document
locations — never silently compare unrelated coordinate spaces.

## The assertions

The relations from Part 1, re-exposed so the call site reads naturally:

```java
expect(element).toHaveWidth(240, 0.5);
expect(element).toHaveHeight(40);
expect(element).toBeBelow(reference);
expect(element).toBeAbove(reference);
expect(element).toBeLeftOf(reference);
expect(element).toBeRightOf(reference);
expect(element).toHaveSameWidthAs(reference);
expect(element).toHaveSameHeightAs(reference);
expect(element).toBeLeftAlignedWith(reference);
expect(element).toBeTopAlignedWith(reference);
expect(element).toTouchHorizontally(reference);
expect(element).toTouchVertically(reference);
```

These take one snapshot per assertion. They do not wait for transitions or layout
stability; a test wraps `Dom.waitFor(...)` around an assertion when the application
changes asynchronously.

Failure messages add the element description to the rectangle diagnostics from
Part 1.

## Not in scope

`mockatcha-dom` does not serve or inject stylesheets. The test application boots the
widget module the way an ordinary application does, so the module system loads the
stylesheets and scripts it declares. The host only serves that output and waits until
it is ready. **A test step must never inject Bootstrap's stylesheet as a substitute
for module initialization** — that asserts against a fixture the test invented rather
than against the library.

## Acceptance tests

Through `TeaVMTestRunner` in the pinned Chromium:

1. `toHaveStyle` observes inline, stylesheet, inherited and custom-property values
   after cascade resolution.
2. The public computed-style API returns the same value `toHaveStyle` uses.
3. Pseudo-element content and display can be read.
4. A positioned fixture returns fractional left, top, width and height without
   integer truncation.
5. Relation assertions pass and fail at the documented tolerance boundary.
6. A `display:none` element and a detached element give zero rectangles without
   throwing.
7. An asynchronously moved element can be asserted through `Dom.waitFor`.
8. Style and geometry work for an element in a same-origin iframe.
9. Cross-document relations fail with a coordinate-system error.
10. Null, blank-property and negative-tolerance inputs fail with actionable argument
    errors.

Note that only 1, 2, 3, 4, 6, 7, 8 and 9 genuinely need a browser. Number 5 is Part
1's job and is already covered on the JVM; it is repeated here only to confirm the
wiring, not the arithmetic.

---

# Part 3: applying it to `sarto-scene`

`sarto-scene-testkit` has the same gap and can use the same core. A scene node
already reports everything a rectangle needs:

```java
LayoutBox box = LayoutBox.of(node.getLayoutX(), node.getLayoutY(),
                             node.getWidth(), node.getHeight());
```

So the scene testkit needs one adapter and gains the whole vocabulary — the
relations, the tolerance semantics and the diagnostics are shared, and any bug fixed
in one is fixed for both.

Two differences to settle when it is done, neither affecting Part 1:

- **Coordinate space.** The DOM reports viewport-relative boxes. A scene node reports
  layout coordinates relative to its parent. The cross-document rule in Part 2 has a
  scene equivalent: relations are valid only between nodes resolved into the same
  space, and comparing across spaces must fail rather than compare nonsense. Whether
  the adapter resolves to scene-root coordinates or refuses is the scene testkit's
  decision.
- **When bounds are valid.** A DOM box is meaningful once the element is laid out. A
  scene node's is meaningful after a layout pulse, which `ScenePulse` already models.
  The adapter should make it hard to measure a node that has not been laid out.

`sarto-geometry` already has `Rectangle` and `Bounds2D`. Neither carries the relation
vocabulary, so there is no duplication — but if `LayoutBox` is close enough to
`Rectangle` to be a nuisance, the alternative is for `mockatcha-layout` to accept any
four doubles and leave the value type to the caller. That is a decision to take
before the first adapter, not after the second.

---

# What stays with the caller

Anything that names a domain concept. The library says two boxes touch; it does not
say an input group is contiguous.

The Bootstrap input group scenario is a fair sample. Three of its four assertions are
primitives:

```java
expect(prefix).toTouchHorizontally(control);
expect(control).toTouchHorizontally(suffix);
expect(control).toBeTopAlignedWith(prefix);
expect(control).toHaveSameHeightAs(prefix);
```

The fourth — that the text control consumes whatever width the addons leave — is
arithmetic about the group as a whole, and belongs in the widget test.

Orientation is the same. `toBeVertical()` on a button group would immediately raise
"which children count, and how deep", so it is composed at the call site from
`toBeBelow` rather than named in the library:

```java
expect(group).toHaveStyle("flex-direction", "column");
expect(secondButton).toBeBelow(firstButton);
expect(thirdButton).toBeBelow(secondButton);
```

Input groups, dropdown widths, positioned tabs, media objects, overlays and
suggestion popups all use the same primitives. No widget test should carry a private
`@JSBody` helper for computed style or rectangles.
