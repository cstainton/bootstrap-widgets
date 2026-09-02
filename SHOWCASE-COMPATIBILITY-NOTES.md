# Showcase Compatibility Notes

This tracks differences found while comparing the modern showcase with the original GwtBootstrap3 demo. The target is Bootstrap 5 visual styling with Bootstrap 3 widget API and markup compatibility, not exact Bootstrap 3 pixel parity.

Original reference: https://gwtbootstrap3.github.io/gwtbootstrap3-demo/

## Fixed in the compatibility layer

| Area | Discrepancy | Common cause | Resolution |
| --- | --- | --- | --- |
| Navbar | Mobile hamburger rendered, but legacy collapse did not always respond to `data-toggle`. | Bootstrap 5 listens for `data-bs-toggle`; GwtBootstrap3 emits Bootstrap 3 `data-toggle`. | Added delegated compatibility handling for legacy collapse triggers. |
| Dropdowns | Duplicate chevrons appeared beside menu labels. | Bootstrap 5 adds `::after`; GwtBootstrap3 already renders `.caret`. | Hide Bootstrap 5 generated dropdown pseudo-caret and keep the legacy caret. |
| Dropdowns | Button groups stretched or legacy menu items did not lay out correctly. | Bootstrap 5 expects `.dropdown-item`/`data-bs-*`; GwtBootstrap3 emits Bootstrap 3-era markup. | Scope `.show` to collapse/dropdown menus and provide structural styles for legacy dropdown menus/items. |
| Dropups | Menus opened downward instead of upward. | Bootstrap 5 placement uses Popper/data-bs behaviour; legacy markup expects CSS `.dropup .dropdown-menu`. | Added legacy dropup positioning for `.dropup .dropdown-menu` while retaining Bootstrap 5 button styling. |
| Pagination | Legacy pagination rendered as an unstyled or stretched list. | Bootstrap 5 expects `.page-item`/`.page-link`; GwtBootstrap3 emits `ul.pagination > li > a`. | Added structural styles for the legacy pagination markup. |
| Buttons | `data-toggle="button"` did not toggle active state. | Bootstrap 5 uses `data-bs-toggle="button"`. | Added delegated legacy toggle handling. |
| Buttons | `button('loading')` did not show loading text. | Bootstrap 5 removed the Bootstrap 3 jQuery button state API. | Added a legacy jQuery `button('loading'/'reset'/'toggle')` shim. |

## Current comparison checkpoints

| Check | Original | Modern local build |
| --- | --- | --- |
| Pagination list | `display:inline-block`, `133x34` | structurally correct list using Bootstrap 5 typography, currently `136x37` |
| First pagination link | `34x34`, `6px 12px` padding | structurally correct link using Bootstrap 5 typography, currently `35x37` |
| Dropdown menu | `160px` wide, below button | `160px` wide, below Bootstrap 5-styled button |
| Dropup menu | menu above button, `bottom:34px` | menu above Bootstrap 5-styled button, currently `bottom:38px` |
| Toggle button | click adds `active` | click adds `active` and `aria-pressed=true` |
| Loading button | click shows `Loading...`, disables, then resets | click shows `Loading...`, disables, then resets |

## Pattern

Visual compatibility should be limited to places where Bootstrap 5 cannot style legacy Bootstrap 3 markup correctly. Pure visual choices should generally remain Bootstrap 5 defaults so users see the modern framework rather than a pixel clone of Bootstrap 3.

Most interaction issues are caused by Bootstrap 5 replacing Bootstrap 3 `data-*` attributes and removing old jQuery plugin behaviours. These are best fixed in `bootstrap5-jquery-compat.cache.js`.
