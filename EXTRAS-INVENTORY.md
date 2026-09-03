# Extras Inventory

The extras module is now based on latest upstream `gwtbootstrap3-extras` source, with browser assets refreshed where a drop-in or near drop-in path exists. npm is only a maintainer-side source for fetching pinned release assets; consumers build from checked-in resources with Maven only.

| Extra | Fork asset version | State | Notes |
| --- | --- | --- | --- |
| Bootbox | `6.0.4` | Updated | Uses `bootbox.all.min.js` so locales remain available. |
| DatePicker | `1.10.1` | Updated | Preserves existing Java API and locale injection model. |
| DateTimePicker | `2.4.4` | Updated to latest upstream extras baseline | Plugin remains legacy, but source/API now match upstream latest. |
| Select | `1.14.0-beta3` | Updated | Latest Bootstrap 5-capable jQuery line. Locale aliases preserve existing bundle method names. |
| ToggleSwitch | `3.4.0` | Updated | Still a Bootstrap 3-style plugin; compatibility CSS may need visual QA. |
| Animate | `4.1.1` | Updated | Uses `animate.compat.css` to preserve legacy class names. |
| FullCalendar | `3.10.5` | Updated | Last jQuery-compatible line. Newer major versions are not drop-in. |
| jQuery UI | `1.13.3` | Updated | Required by FullCalendar 3 drag/resize support. |
| Moment | `2.30.1` | Updated | Required by FullCalendar 3. |
| Summernote | `0.9.1` | Updated | Uses Bootstrap 5 distribution. Needs browser/editor QA before production reliance. |
| Typeahead | `0.11.1` | Updated | Last upstream release; includes compatibility CSS. |
| TagsInput | `0.8.0` | Retained | No clearly newer compatible upstream release. Consider replacement later. |
| Notify | `3.1.3` | Retained | Latest available line. Consider Bootstrap 5 toast-backed implementation later. |
| Slider | `11.0.2` | Updated | Drop-in plugin update. |
| Card | `1.0.1` | Retained | Project-local CSS helper, not an external runtime plugin. |
| Gallery | Blueimp `3.4.0`, Bootstrap image gallery `3.4.2` | Partially updated | Blueimp core updated; legacy Bootstrap image gallery adapter retained. |
| CacheManifest | upstream latest | Retained | Source-only helper from upstream extras. |

## Validation Gates

- `mvn -DskipTests package`
- `mvn -f gwt-bootstrap3-showcase/pom.xml -DskipTests -Dgwt.forceCompilation=true gwt:compile`
- `mvn -Pdependency-audit -Dmaven.test.skip=true -DskipTests verify`
- Browser smoke test of the showcase, especially modal, dropdown, datepicker, select, summernote, and gallery pages.
