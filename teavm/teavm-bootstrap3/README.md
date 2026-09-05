# TeaVM Bootstrap 3

Bootstrap 3 widgets for a Java web application compiled with TeaVM.

You write ordinary GWT widget code. TeaVM compiles it to JavaScript instead of the GWT
compiler. Nothing about your code has to know the difference.

## 1. Add the dependency

```xml
<dependency>
  <groupId>io.instanto</groupId>
  <artifactId>teavm-bootstrap3</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

Do not also add `gwt-user`. This library brings its own implementation of the GWT
classes it needs, and having both gives you two of everything.

## 2. Write your main

```java
public final class MyApp {
    public static void main(String[] args) {
        Bootstrap3.initialise();

        Button save = new Button("Save");
        save.setType(ButtonType.PRIMARY);
        save.addClickHandler(event -> Window.alert("Saved"));

        RootPanel.get().add(save);
    }
}
```

Two lines matter.

`Bootstrap3.initialise()` puts the library's stylesheets on the page. Call it once,
before your first widget. Calling it again does nothing, so it is safe anywhere.

`RootPanel.get().add(...)` puts a widget on the page. `RootPanel.get()` is the
document body. If your widgets belong inside an element that is already there, name
it instead:

```java
RootPanel.get("editor").add(new Panel());
```

## 3. Add Bootstrap's own JavaScript to your page

Bootstrap 3 needs jQuery, and both belong to the page rather than to this library:

```html
<script src="jquery-3.7.1.min.cache.js"></script>
<script src="bootstrap-3.4.1.min.cache.js"></script>
<script src="your-app.js"></script>
```

If either is missing, `initialise()` says so on the console — otherwise your modals
and dropdowns would simply do nothing, with no clue why.

That is the whole setup. Everything below is optional.

## Choosing a theme

A default theme is applied for you, so widgets look right without any of this. To
offer alternatives:

```java
Themes.register(StandardThemes.all());
Themes.register(BootswatchThemes.all());
Themes.restore();
```

Sixteen Bootswatch themes ship in this artifact. If your page already declares a
`<link id="gwtbootstrap3-theme">`, that link is used and switching replaces its
`href`, so a server-rendered starting theme survives startup without a flash.

## One thing that will catch you out

Add widgets through `RootPanel`. Do not append their elements yourself:

```java
someElement.appendChild(widget.getElement());   // don't
```

That puts the markup on the page but never tells the widget it was attached. Its
`onLoad` never runs, and that is where a tooltip binds to its element and a dropdown
registers its handlers. The widget looks right and does nothing.

## Try it

The [TeaVM showcase](https://cstainton.github.io/bootstrap-widgets/teavm.html) is this
library running in a browser, beside the
[GWT showcase](https://cstainton.github.io/bootstrap-widgets/) built from the same
source. Comparing the two is the point: where they differ, the compatibility layer is
wrong.

If you want to read a working application:

- [`SharedShowcaseApp`](src/main/java/org/gwtbootstrap3/teavm/demo/SharedShowcaseApp.java)
  is the entry point, and it is four lines.
- [`GwtBootstrap3DemoEntryPoint`](../../gwt/gwt-bootstrap3-showcase/src/main/java/org/gwtbootstrap3/demo/client/GwtBootstrap3DemoEntryPoint.java)
  is the showcase itself — ordinary widget code, compiled unchanged by both compilers.

## What is missing

The extras — Bootstrap Select, Summernote, FullCalendar, the date pickers — are not
available on this backend. They are jQuery plugins reached through 353 hand-written
JavaScript methods that only the GWT compiler understands. The showcase is short the
14 pages that use them; the other 41 are here.

## How it works, if you are curious

This module compiles the same source as
[`gwt-bootstrap3`](../../gwt/gwt-bootstrap3) against
[`teavm-gwt-compat`](../teavm-gwt-compat), which reimplements the parts of
`com.google.gwt.*` the widgets use.

`initialise()` exists because GWT has a module system and TeaVM does not. In a GWT
application, a `.gwt.xml` file declares the stylesheets a module needs and the
generated bootstrap injects them before your code runs. Nothing does that here, so one
call stands in for it.
