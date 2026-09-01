# Third-party browser assets

This fork vendors browser JS/CSS resources inside the GWT modules so downstream consumers can continue to build with Maven only. npm is used only as a maintainer convenience for fetching pinned upstream release artifacts; it is not part of the consumer build path and is not required when TMS builds against the fork.

## Policy

- Keep vendored assets checked in with the Java/GWT sources.
- Pin exact upstream versions in this file and in `pom.xml` properties.
- Do not add npm, node, or frontend package-manager execution to the Maven lifecycle.
- Prefer upstream release artifacts from npm only where the original project publishes its browser distribution there.
- When assets are refreshed, rebuild the fork and run the dependency audit profile before publishing.

## Current Assets

| Asset | Version | Source package | Notes |
| --- | --- | --- | --- |
| Bootstrap | 5.3.8 | `bootstrap` | Core framework runtime, bundled as `bootstrap.bundle` to include Popper. |
| Bootstrap Icons | 1.13.1 | `bootstrap-icons` | Added alongside Font Awesome for Bootstrap 5-era icons. |
| jQuery | 3.7.1 | `jquery` | Compatibility dependency for legacy GwtBootstrap3 widgets/plugins. |
| Font Awesome | 4.7.0 | `font-awesome` | Retained for the existing `IconType` API. |
| Bootbox | 6.0.4 | `bootbox` | Bootstrap 5-compatible dialog layer. |
| bootstrap-datepicker | 1.10.1 | `bootstrap-datepicker` | Kept because public widgets are tied to this plugin. |
| bootstrap-datetimepicker | 2.4.4 | upstream gwtbootstrap3-extras | Newest version in upstream extras baseline. |
| bootstrap-select | 1.14.0-beta3 | `bootstrap-select` | Latest Bootstrap 5-capable jQuery line. |
| bootstrap-slider | 11.0.2 | `bootstrap-slider` | Drop-in jQuery plugin update. |
| bootstrap-switch | 3.4.0 | `bootstrap-switch` | Last compatible line; CSS remains Bootstrap 3-flavoured and is shimmed by compatibility CSS. |
| typeahead.js | 0.11.1 | `typeahead.js` | Last upstream release. |
| animate.css | 4.1.1 | `animate.css` | Uses `animate.compat.css` to preserve legacy class names. |
| Summernote | 0.9.1 | `summernote` | Uses Bootstrap 5 distribution. |
| FullCalendar | 3.10.5 | `fullcalendar` | Last jQuery-compatible line; newer major versions are not drop-in. |
| jQuery UI | 1.13.3 | `jquery-ui-dist` | Used by FullCalendar drag/resize support. |
| Moment | 2.30.1 | `moment` | Used by FullCalendar 3.x. |
| Blueimp Gallery | 3.4.0 | `blueimp-gallery` | Core gallery plugin update; legacy Bootstrap image gallery adapter retained. |
| bootstrap-notify | 3.1.3 | `bootstrap-notify` | Latest available line, retained. |
| bootstrap-tagsinput | 0.8.0 | upstream gwtbootstrap3-extras | Newer npm package is older than the bundled upstream asset, retained. |
