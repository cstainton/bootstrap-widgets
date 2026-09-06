# TeaVM module assets

The plugin packages browser dependencies from the shared GWT sources into a TeaVM
library JAR and generates a loader for each selected module.

- `<script>` and `<stylesheet>` declarations supply the files to load.
- `<public>` directories supply assets using their include/exclude patterns, with
  paths preserved for relative links to images, fonts and source maps.
- ClientBundle interfaces supply resources through literal `@Source` strings or
  arrays. Their Java filenames do not have to end in `ClientBundle`.
- JavaScript bundle resources join the generated script list. Other bundle
  resources are packaged without being executed.

The generated loader delegates to `ScriptModule`, which loads scripts in order and
notifies waiting callers when they are usable. This mechanism has no Bootstrap or
jQuery dependency. The optional `scriptPresence` configuration supplies a Java
predicate for recognising a particular script already provided by the host.

`assetRoot` receives the files for development; `packagedRoot` receives the same
files inside the JAR. A consuming application publishes that tree and configures
the module's asset base URL. TeaVMTestRunner can serve it directly from the JAR
through its `/resources/` endpoint.

`inlineTextBundles` names ClientBundle interfaces whose TextResource methods must
remain synchronously readable. For these bundles the plugin generates Java
providers and service descriptors, using the declared files verbatim. Every
TextResource method must have a literal, single-file `@Source` declaration.
Their scripts are packaged but do not join the startup script list. This allows
an editor or select widget to inject just its chosen locale, instead of executing
all locale scripts and overwriting global defaults. Large resources are split
into Java string chunks to avoid the class-file constant-size limit.

Resource packaging does not replace GWT's resource generators: CSS rewriting,
image sprites and deferred binding remain separate concerns. Script-version
conflict resolution is also separate from discovering and packaging the files.
