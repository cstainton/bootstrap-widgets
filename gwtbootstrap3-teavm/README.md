# GwtBootstrap3 TeaVM Sidecar

This module is deliberately experimental. It exists so the fork has a buildable TeaVM-facing home for DOM and Bootstrap 5 interop work without changing the public GWT widget API.

The current module does not make `org.gwtbootstrap3.client.ui.*` widgets run on TeaVM. That will require extracting more of the widget internals behind a shared DOM/plugin abstraction and then supplying TeaVM-backed implementations.
