/*
 * #%L
 * GWT Bootstrap
 * %%
 * Copyright (C) 2026 Carl Stainton
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.instanto.teavm.modules;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Generates the TeaVM counterpart of a GWT module's resource declarations.
 *
 * <p>A {@code .gwt.xml} says which stylesheets a module needs, and its ClientBundle says
 * which scripts; GWT reads both and injects them. TeaVM has no module system to read
 * them, so this reads the same two declarations at build time and writes a class that
 * loads the same files by URL -- and, unlike GWT, reports when they are usable.</p>
 *
 * <p>The assets are copied out alongside, both to where the development server serves
 * them and into the jar, so a downstream application gets them by depending on it.</p>
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES, threadSafe = true)
public class GenerateModulesMojo extends AbstractMojo {

    /** Where the GWT modules are, normally the shared widget sources. */
    @Parameter(required = true)
    private java.io.File sourceRoot;

    /** Where the generated Java goes; add it as a source root. */
    @Parameter(required = true)
    private java.io.File outputRoot;

    /** Where the assets go for serving during development. */
    @Parameter(required = true)
    private java.io.File assetRoot;

    /** Where the assets go to be packaged into the jar. Optional. */
    @Parameter
    private java.io.File packagedRoot;

    /** The class every generated module delegates its loading to. */
    @Parameter(defaultValue = "io.instanto.bootstrap5.client.Bootstrap5Resources")
    private String resourcesClass;

    /** Prefix for the element ids the generated classes use. */
    @Parameter(defaultValue = "bootstrap5-")
    private String idPrefix;

    private static final Pattern BUNDLE_SCRIPT = Pattern.compile("@Source\\(\"([^\"]+\\.js)\"\\)");

    @Override
    public void execute() throws MojoExecutionException {
        if (!sourceRoot.isDirectory()) {
            throw new MojoExecutionException("sourceRoot is not a directory: " + sourceRoot);
        }
        int generated = 0;
        try (Stream<Path> paths = Files.walk(sourceRoot.toPath())) {
            final List<Path> modules = new ArrayList<>();
            paths.filter(p -> p.getFileName().toString().endsWith(".gwt.xml")).forEach(modules::add);
            modules.sort(Path::compareTo);
            for (final Path module : modules) {
                if (generate(module)) {
                    generated++;
                }
            }
        } catch (final IOException problem) {
            throw new MojoExecutionException("could not read " + sourceRoot, problem);
        }
        getLog().info("generated " + generated + " TeaVM module"
                + (generated == 1 ? "" : "s"));
    }

    private boolean generate(final Path modulePath) throws MojoExecutionException {
        final Path dir = modulePath.getParent();
        final String fileName = modulePath.getFileName().toString();
        final Document document = parse(modulePath);

        final List<String> stylesheets = new ArrayList<>();
        final NodeList sheets = document.getElementsByTagName("stylesheet");
        for (int i = 0; i < sheets.getLength(); i++) {
            final String src = ((Element) sheets.item(i)).getAttribute("src");
            if (!src.isEmpty()) {
                stylesheets.add(fileNameOf(src));
            }
        }
        final List<String> scripts = bundleScripts(dir);
        if (stylesheets.isEmpty() && scripts.isEmpty()) {
            return false;
        }

        final String module = fileName.substring(0, fileName.length() - ".gwt.xml".length());
        final String pkg = sourceRoot.toPath().relativize(dir).toString()
                .replace(java.io.File.separatorChar, '.') + ".client";
        final String klass = module + "Resources";
        final String prefix = idPrefix + module.toLowerCase(Locale.ROOT) + "-";

        write(outputRoot.toPath().resolve(pkg.replace('.', java.io.File.separatorChar))
                        .resolve(klass + ".java"),
                render(fileName, pkg, module, klass, prefix, stylesheets, scripts));

        copyAssets(document, dir, scripts);

        getLog().info("  " + fileName + " -> " + klass + " ("
                + stylesheets.size() + " stylesheet" + (stylesheets.size() == 1 ? "" : "s")
                + ", " + scripts.size() + " script" + (scripts.size() == 1 ? "" : "s") + ")");
        return true;
    }

    /**
     * The scripts a module's ClientBundle inlines on GWT.
     *
     * <p>They are declared in Java rather than in the module file, because on GWT they
     * are compiled into the output rather than served. The same list is what has to be
     * fetched here instead.</p>
     */
    private List<String> bundleScripts(final Path dir) throws MojoExecutionException {
        final Set<String> found = new LinkedHashSet<>();
        try (Stream<Path> paths = Files.walk(dir)) {
            final List<Path> bundles = new ArrayList<>();
            paths.filter(p -> p.getFileName().toString().endsWith("ClientBundle.java"))
                 .forEach(bundles::add);
            bundles.sort(Path::compareTo);
            for (final Path bundle : bundles) {
                final Matcher matcher = BUNDLE_SCRIPT.matcher(
                        Files.readString(bundle, StandardCharsets.UTF_8));
                while (matcher.find()) {
                    found.add(fileNameOf(matcher.group(1)));
                }
            }
        } catch (final IOException problem) {
            throw new MojoExecutionException("could not scan " + dir, problem);
        }
        return new ArrayList<>(found);
    }

    private String render(final String moduleFile, final String pkg, final String module,
            final String klass, final String prefix, final List<String> stylesheets,
            final List<String> scripts) {
        final StringBuilder body = new StringBuilder();
        for (final String sheet : stylesheets) {
            body.append("            .stylesheet(").append(shortName(resourcesClass))
                .append(".cssBase() + \"").append(sheet).append("\")\n");
        }
        for (final String script : scripts) {
            body.append("            .script(").append(shortName(resourcesClass))
                .append(".jsBase() + \"").append(script).append("\")\n");
        }
        // Trailing newline is trimmed so the chained call ends cleanly.
        if (body.length() > 0) {
            body.setLength(body.length() - 1);
        }

        return "// Generated from " + moduleFile + ". Do not edit.\n"
             + "package " + pkg + ";\n\n"
             + "import " + resourcesClass + ";\n"
             + "import io.instanto.teavm.module.ScriptModule;\n\n"
             + "/**\n"
             + " * Loads what the " + module + " module declares, on the TeaVM backend.\n"
             + " *\n"
             + " * <p>GWT injects these from " + moduleFile + " and the module's entry point.\n"
             + " * This is the TeaVM equivalent, fetching them by URL from wherever the module\n"
             + " * was deployed. An application does not call it; the widgets do, when they are\n"
             + " * constructed.</p>\n"
             + " *\n"
             + " * <p>Unlike the GWT side it also says when the module is <em>usable</em>. A\n"
             + " * script element reports when it has run and when it has failed, so a widget can\n"
             + " * wait for its library rather than poll for it, and a module that cannot load\n"
             + " * says so once instead of timing out into silence.</p>\n"
             + " */\n"
             + "public final class " + klass + " {\n\n"
             + "    private static final ScriptModule MODULE = ScriptModule.named(\""
             + module + "\")\n" + body + ";\n\n"
             + "    private " + klass + "() {\n"
             + "    }\n\n"
             + "    /** Injects this module's resources once; further calls do nothing. */\n"
             + "    public static void ensureInjected() {\n"
             + "        MODULE.ensureLoaded();\n"
             + "    }\n\n"
             + "    /**\n"
             + "     * Runs an action once this module's library is usable, now if it already is.\n"
             + "     *\n"
             + "     * <p>The presence test lets a page that loaded the library itself be\n"
             + "     * recognised without fetching a second copy, which is what keeps this\n"
             + "     * module system optional rather than compulsory.</p>\n"
             + "     */\n"
             + "    public static void whenReady(final ScriptModule.Presence presence,\n"
             + "            final Runnable action) {\n"
             + "        MODULE.presence(presence).whenReady(action);\n"
             + "    }\n\n"
             + "    /** Whether the library is usable now. */\n"
             + "    public static boolean isReady(final ScriptModule.Presence presence) {\n"
             + "        return MODULE.presence(presence).isReady();\n"
             + "    }\n"
             + "}\n";
    }

    /** Copies the module's own css and js out to where they will be served. */
    private void copyAssets(final Document document, final Path dir, final List<String> scripts)
            throws MojoExecutionException {
        final NodeList publics = document.getElementsByTagName("public");
        for (int i = 0; i < publics.getLength(); i++) {
            final Path base = dir.resolve(((Element) publics.item(i)).getAttribute("path"));
            for (final String kind : new String[] {"css", "js"}) {
                final Path from = base.resolve(kind);
                if (!Files.isDirectory(from)) {
                    continue;
                }
                for (final Path target : targets()) {
                    copyMatching(from, target.resolve(kind), "." + kind);
                }
            }
        }
        // A module may inline its scripts through a ClientBundle without declaring a
        // public path, in which case they still have to be served from somewhere.
        try (Stream<Path> paths = Files.walk(dir)) {
            final List<Path> loose = new ArrayList<>();
            paths.filter(p -> scripts.contains(p.getFileName().toString())).forEach(loose::add);
            for (final Path asset : loose) {
                for (final Path target : targets()) {
                    copyInto(asset, target.resolve("js"));
                }
            }
        } catch (final IOException problem) {
            throw new MojoExecutionException("could not collect assets under " + dir, problem);
        }
    }

    private List<Path> targets() {
        final List<Path> targets = new ArrayList<>();
        targets.add(assetRoot.toPath());
        if (packagedRoot != null) {
            targets.add(packagedRoot.toPath());
        }
        return targets;
    }

    private void copyMatching(final Path from, final Path to, final String suffix)
            throws MojoExecutionException {
        try (Stream<Path> paths = Files.list(from)) {
            final List<Path> assets = new ArrayList<>();
            paths.filter(p -> p.getFileName().toString().endsWith(suffix)).forEach(assets::add);
            for (final Path asset : assets) {
                copyInto(asset, to);
            }
        } catch (final IOException problem) {
            throw new MojoExecutionException("could not copy assets from " + from, problem);
        }
    }

    private void copyInto(final Path asset, final Path directory) throws MojoExecutionException {
        try {
            Files.createDirectories(directory);
            Files.copy(asset, directory.resolve(asset.getFileName()),
                    StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        } catch (final IOException problem) {
            throw new MojoExecutionException("could not copy " + asset, problem);
        }
    }

    private void write(final Path file, final String content) throws MojoExecutionException {
        try {
            Files.createDirectories(file.getParent());
            Files.writeString(file, content, StandardCharsets.UTF_8);
        } catch (final IOException problem) {
            throw new MojoExecutionException("could not write " + file, problem);
        }
    }

    private Document parse(final Path path) throws MojoExecutionException {
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // A GWT module names a DTD that is not worth a network round trip, and that a
            // build has no business depending on being reachable.
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",
                    false);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            final DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setEntityResolver((publicId, systemId) ->
                    new org.xml.sax.InputSource(new java.io.StringReader("")));
            return builder.parse(path.toFile());
        } catch (final Exception problem) {
            throw new MojoExecutionException("could not parse " + path, problem);
        }
    }

    private static String fileNameOf(final String path) {
        final int slash = path.lastIndexOf('/');
        return slash < 0 ? path : path.substring(slash + 1);
    }

    private static String shortName(final String className) {
        return className.substring(className.lastIndexOf('.') + 1);
    }
}
