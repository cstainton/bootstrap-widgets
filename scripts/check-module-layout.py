#!/usr/bin/env python3
"""Validate the path-sensitive GWT/TeaVM shared-source seams."""

from pathlib import Path
import re
import sys
import xml.etree.ElementTree as ET


ROOT = Path(__file__).resolve().parent.parent
NS = {"m": "http://maven.apache.org/POM/4.0.0"}

TRACKS = {
    "Bootstrap 3": {
        "pom": ROOT / "teavm/teavm-bootstrap3/pom.xml",
        "sources": {
            "${project.parent.parent.basedir}/gwt/gwt-bootstrap3/src/main/java",
            "${project.parent.parent.basedir}/gwt/gwt-bootstrap3-themes/src/main/java",
            "${project.parent.parent.basedir}/gwt/gwt-bootstrap3-showcase/src/main/java",
        },
        "source_roots": (
            ROOT / "gwt/gwt-bootstrap3/src/main/java",
            ROOT / "gwt/gwt-bootstrap3-themes/src/main/java",
            ROOT / "gwt/gwt-bootstrap3-showcase/src/main/java",
        ),
        "excludes": {
            "org/gwtbootstrap3/client/GwtBootstrap3EntryPoint.java",
            "org/gwtbootstrap3/client/GwtBootstrap3ClientBundle.java",
            "org/gwtbootstrap3/client/shared/js/JQuery.java",
            "org/gwtbootstrap3/client/ui/base/TooltipOptions.java",
            "org/gwtbootstrap3/client/ui/base/CarouselOptions.java",
            "org/gwtbootstrap3/demo/client/ExtrasPages.java",
            "org/gwtbootstrap3/demo/client/GwtBootstrap3DemoClientBundle.java",
            "org/gwtbootstrap3/demo/client/ShowcaseScripts.java",
            "org/gwtbootstrap3/demo/client/application/css/validation/**",
            "org/gwtbootstrap3/demo/client/application/extras/**",
            "org/gwtbootstrap3/demo/client/ui/PrettyPre.java",
        },
        "replacements": {
            "org/gwtbootstrap3/client/TeaVmBootstrap3EntryPoint.java",
            "org/gwtbootstrap3/client/Bootstrap3Resources.java",
            "org/gwtbootstrap3/client/shared/js/JQuery.java",
            "org/gwtbootstrap3/client/ui/base/TooltipOptions.java",
            "org/gwtbootstrap3/client/ui/base/CarouselOptions.java",
            "org/gwtbootstrap3/demo/client/ExtrasPages.java",
            "org/gwtbootstrap3/demo/client/ShowcaseScripts.java",
            "org/gwtbootstrap3/demo/client/ui/PrettyPre.java",
        },
    },
    "Bootstrap 5": {
        "pom": ROOT / "teavm/teavm-bootstrap5/pom.xml",
        "sources": {
            "${project.parent.parent.basedir}/gwt/gwt-bootstrap5/src/main/java",
            "${project.parent.parent.basedir}/gwt/gwt-bootstrap5-themes/src/main/java",
            "${project.parent.parent.basedir}/gwt/gwt-bootstrap5-extras/src/main/java",
            "${project.parent.parent.basedir}/gwt/gwt-bootstrap5-showcase/src/main/java",
            "${project.build.directory}/generated-sources/teavm-modules",
        },
        "source_roots": (
            ROOT / "gwt/gwt-bootstrap5/src/main/java",
            ROOT / "gwt/gwt-bootstrap5-themes/src/main/java",
            ROOT / "gwt/gwt-bootstrap5-extras/src/main/java",
            ROOT / "gwt/gwt-bootstrap5-showcase/src/main/java",
        ),
        "excludes": {
            "io/instanto/bootstrap5/client/GwtBootstrap5EntryPoint.java",
            "io/instanto/bootstrap5/client/ui/base/BootstrapEventBridge.java",
            "io/instanto/bootstrap5/client/ui/base/BootstrapComponent.java",
            "io/instanto/bootstrap5/client/ui/base/InputEvents.java",
            "io/instanto/bootstrap5/extras/datepicker/client/DatePickerClientBundle.java",
            "io/instanto/bootstrap5/extras/datepicker/client/DatePickerEntryPoint.java",
            "io/instanto/bootstrap5/extras/datepicker/client/ui/DatePickerJs.java",
            "io/instanto/bootstrap5/extras/markdown/client/Markdown.java",
            "io/instanto/bootstrap5/extras/markdown/client/MarkdownClientBundle.java",
            "io/instanto/bootstrap5/extras/markdown/client/MarkdownEntryPoint.java",
            "io/instanto/bootstrap5/extras/markdown/client/ui/TextAreaSelection.java",
            "io/instanto/bootstrap5/extras/richtext/client/RichTextClientBundle.java",
            "io/instanto/bootstrap5/extras/richtext/client/RichTextEntryPoint.java",
            "io/instanto/bootstrap5/extras/richtext/client/ui/QuillJs.java",
            "io/instanto/bootstrap5/extras/slider/client/SliderClientBundle.java",
            "io/instanto/bootstrap5/extras/slider/client/SliderEntryPoint.java",
            "io/instanto/bootstrap5/extras/slider/client/ui/SliderJs.java",
        },
        "replacements": {
            "io/instanto/bootstrap5/client/TeaVmBootstrap5EntryPoint.java",
            "io/instanto/bootstrap5/client/ui/base/BootstrapEventBridge.java",
            "io/instanto/bootstrap5/client/ui/base/BootstrapComponent.java",
            "io/instanto/bootstrap5/client/ui/base/InputEvents.java",
            "io/instanto/bootstrap5/extras/datepicker/client/ui/DatePickerJs.java",
            "io/instanto/bootstrap5/extras/markdown/client/Markdown.java",
            "io/instanto/bootstrap5/extras/markdown/client/ui/TextAreaSelection.java",
        },
    },
}


def values(root, expression):
    return {node.text.strip() for node in root.findall(expression, NS) if node.text}


def check_track(name, track):
    problems = []
    pom = ET.parse(track["pom"]).getroot()
    sources = values(pom, ".//m:plugin[m:artifactId='build-helper-maven-plugin']//m:source")
    excludes = values(pom, ".//m:plugin[m:artifactId='maven-compiler-plugin']//m:exclude")

    if sources != track["sources"]:
        problems.append(f"{name} add-source entries are {sorted(sources)}, expected {sorted(track['sources'])}")
    if excludes != track["excludes"]:
        problems.append(f"{name} compiler exclusions are {sorted(excludes)}, expected {sorted(track['excludes'])}")

    for relative in sorted(track["excludes"]):
        found = any(
            any(source_root.glob(relative)) if "*" in relative
            else (source_root / relative).is_file()
            for source_root in track["source_roots"]
        )
        if not found:
            problems.append(f"{name} excluded GWT source is missing: {relative}")

    replacement_root = track["pom"].parent / "src/main/java"
    for relative in sorted(track["replacements"]):
        if not (replacement_root / relative).is_file():
            problems.append(f"{name} TeaVM replacement is missing: {relative}")
    return problems


def main():
    problems = []
    for name, track in TRACKS.items():
        problems.extend(check_track(name, track))

    element_panel = (ROOT / "gwt/gwt-bootstrap5/src/main/java/io/instanto/bootstrap5/client/ui/ElementPanel.java").read_text()
    if not re.search(r"implements\s+HasWidgets\s*,\s*HasHTML\b", element_panel):
        problems.append("ElementPanel must declare HasWidgets immediately before HasHTML for UiBinder")

    if problems:
        print("module layout check failed:", file=sys.stderr)
        for problem in problems:
            print(f"- {problem}", file=sys.stderr)
        return 1

    print("module layout and GWT/TeaVM source seams are consistent")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
