#!/usr/bin/env python3
"""Generate static TeaVM widget reachability tests and verify fixture coverage."""

from __future__ import annotations

import argparse
from dataclasses import dataclass
from pathlib import Path
import re
import sys


ROOT = Path(__file__).resolve().parent.parent
CATALOGUE = (
    ROOT
    / "testing/bootstrap-widget-fixtures/src/main/resources"
    / "io/instanto/bootstrap/testing/widget-fixtures.tsv"
)


@dataclass(frozen=True)
class Target:
    name: str
    class_prefix: str
    source_roots: tuple[Path, ...]
    candidate_roots: tuple[tuple[str, Path], ...]
    output_root: Path
    package_name: str
    class_stem: str


TARGETS = {
    "bootstrap3": Target(
        name="bootstrap3",
        class_prefix="org.gwtbootstrap3.client.ui",
        source_roots=(ROOT / "gwt/gwt-bootstrap3/src/main/java",),
        candidate_roots=(
            ("core", ROOT / "gwt/gwt-bootstrap3/src/main/java/org/gwtbootstrap3/client/ui"),
        ),
        output_root=(
            ROOT
            / "teavm/teavm-bootstrap-widget-tests/bootstrap3/src/test/java"
            / "io/instanto/bootstrap/testing/bootstrap3"
        ),
        package_name="io.instanto.bootstrap.testing.bootstrap3",
        class_stem="Bootstrap3",
    ),
    "bootstrap5": Target(
        name="bootstrap5",
        class_prefix="io.instanto.bootstrap5.client.ui",
        source_roots=(
            ROOT / "gwt/gwt-bootstrap5/src/main/java",
            ROOT / "gwt/gwt-bootstrap5-extras/src/main/java",
        ),
        candidate_roots=(
            ("core", ROOT / "gwt/gwt-bootstrap5/src/main/java/io/instanto/bootstrap5/client/ui"),
            (
                "markdown",
                ROOT
                / "gwt/gwt-bootstrap5-extras/src/main/java"
                / "io/instanto/bootstrap5/extras/markdown/client/ui",
            ),
        ),
        output_root=(
            ROOT
            / "teavm/teavm-bootstrap-widget-tests/bootstrap5/src/test/java"
            / "io/instanto/bootstrap/testing/bootstrap5"
        ),
        package_name="io.instanto.bootstrap.testing.bootstrap5",
        class_stem="Bootstrap5",
    ),
}


@dataclass(frozen=True)
class JavaClass:
    fqcn: str
    name: str
    package_name: str
    parent: str
    interfaces: tuple[str, ...]
    public: bool
    abstract: bool
    path: Path
    imports: dict[str, str]


@dataclass(frozen=True)
class Fixture:
    target: str
    area: str
    fqcn: str
    fixture_id: str
    expression: str


PACKAGE_RE = re.compile(r"\bpackage\s+([\w.]+)\s*;")
IMPORT_RE = re.compile(r"\bimport\s+([\w.$]+)\s*;")
CLASS_RE = re.compile(
    r"\b(?P<visibility>public\s+)?(?P<mods>(?:(?:abstract|final)\s+)*)class\s+"
    r"(?P<name>\w+)(?:\s*<[^>{]+>)?"
    r"(?:\s+extends\s+(?P<parent>[\w.$]+(?:\s*<[^>{]+>)?))?"
    r"(?P<tail>[^{}]*)\{",
    re.DOTALL,
)


CONSTRUCTOR_OVERRIDES = {
    ("bootstrap3", "Abbreviation"): '("fixture")',
    ("bootstrap3", "BooleanRadioGroup"): '("fixture-group")',
    ("bootstrap3", "Column"): '("col-md-1")',
    ("bootstrap3", "DoubleRadioGroup"): '("fixture-group")',
    ("bootstrap3", "Heading"): "(org.gwtbootstrap3.client.ui.constants.HeadingSize.H2)",
    ("bootstrap3", "InlineRadio"): '("fixture-group")',
    ("bootstrap3", "IntegerRadioGroup"): '("fixture-group")',
    ("bootstrap3", "Popover"): '(new org.gwtbootstrap3.client.ui.Button("target"))',
    ("bootstrap3", "Radio"): '("fixture-group")',
    ("bootstrap3", "RadioButton"): '("fixture-group")',
    ("bootstrap3", "SimpleRadioButton"): '("fixture-group")',
    ("bootstrap3", "StringRadioGroup"): '("fixture-group")',
    ("bootstrap3", "Tooltip"): '(new org.gwtbootstrap3.client.ui.Button("target"))',
    ("bootstrap3", "ValueListBox"): (
        "<String>(com.google.gwt.text.shared.testing.PassthroughRenderer.instance())"
    ),
    ("bootstrap5", "BooleanRadioGroup"): '("fixture-group")',
    ("bootstrap5", "DoubleRadioGroup"): '("fixture-group")',
    ("bootstrap5", "Heading"): "(2)",
    ("bootstrap5", "IntegerRadioGroup"): '("fixture-group")',
    ("bootstrap5", "ModalComponent"): '("div")',
    ("bootstrap5", "Popover"): '(new io.instanto.bootstrap5.client.ui.Button("target"))',
    ("bootstrap5", "Radio"): '("fixture-group")',
    ("bootstrap5", "RadioButton"): '("fixture-group")',
    ("bootstrap5", "SimpleRadioButton"): '("fixture-group")',
    ("bootstrap5", "StringRadioGroup"): '("fixture-group")',
    ("bootstrap5", "Tooltip"): '(new io.instanto.bootstrap5.client.ui.Button("target"))',
}


def strip_generics(type_name: str) -> str:
    return re.sub(r"<.*>", "", type_name).strip()


def parse_classes(source_roots: tuple[Path, ...]) -> dict[str, JavaClass]:
    classes: dict[str, JavaClass] = {}
    paths: set[Path] = set()
    for source_root in source_roots:
        paths.update(source_root.rglob("*.java"))

    for path in sorted(paths):
        source = path.read_text(encoding="utf-8")
        code = re.sub(r"/\*.*?\*/", "", source, flags=re.DOTALL)
        code = re.sub(r"//.*", "", code)
        package_match = PACKAGE_RE.search(code)
        class_match = CLASS_RE.search(code)
        if package_match is None or class_match is None:
            continue
        package_name = package_match.group(1)
        name = class_match.group("name")
        imports = {item.rsplit(".", 1)[-1]: item for item in IMPORT_RE.findall(code)}
        tail = class_match.group("tail")
        interfaces: tuple[str, ...] = ()
        if "implements" in tail:
            interface_text = tail.split("implements", 1)[1]
            interfaces = tuple(strip_generics(item) for item in interface_text.split(","))
        fqcn = f"{package_name}.{name}"
        classes[fqcn] = JavaClass(
            fqcn=fqcn,
            name=name,
            package_name=package_name,
            parent=strip_generics(class_match.group("parent") or ""),
            interfaces=interfaces,
            public=class_match.group("visibility") is not None,
            abstract="abstract" in class_match.group("mods").split(),
            path=path,
            imports=imports,
        )
    return classes


def resolve_type(type_name: str, owner: JavaClass, classes: dict[str, JavaClass]) -> str:
    clean = strip_generics(type_name).replace("$", ".")
    if clean in owner.imports:
        return owner.imports[clean]
    if "." in clean and clean.split(".", 1)[0][:1].islower():
        return clean
    same_package = f"{owner.package_name}.{clean}"
    if same_package in classes:
        return same_package
    matches = [fqcn for fqcn in classes if fqcn.rsplit(".", 1)[-1] == clean]
    return matches[0] if len(matches) == 1 else clean


def is_widget_class(
    fqcn: str,
    classes: dict[str, JavaClass],
    cache: dict[str, bool],
    visiting: set[str] | None = None,
) -> bool:
    if fqcn in cache:
        return cache[fqcn]
    owner = classes[fqcn]
    visiting = set() if visiting is None else visiting
    if fqcn in visiting:
        return False
    visiting.add(fqcn)

    interfaces = [resolve_type(item, owner, classes) for item in owner.interfaces]
    if "com.google.gwt.user.client.ui.IsWidget" in interfaces:
        cache[fqcn] = True
        return True

    if owner.parent:
        parent = resolve_type(owner.parent, owner, classes)
        if parent.startswith("com.google.gwt.user.client.ui."):
            cache[fqcn] = True
            return True
        if parent in classes and is_widget_class(parent, classes, cache, visiting):
            cache[fqcn] = True
            return True

    cache[fqcn] = False
    return False


def discover_exports(target: Target) -> set[tuple[str, str]]:
    classes = parse_classes(target.source_roots)
    cache: dict[str, bool] = {}
    exports: set[tuple[str, str]] = set()
    for area, candidate_root in target.candidate_roots:
        for path in sorted(candidate_root.glob("*.java")):
            candidates = [item for item in classes.values() if item.path == path]
            if not candidates:
                continue
            java_class = candidates[0]
            if java_class.public and not java_class.abstract and is_widget_class(java_class.fqcn, classes, cache):
                exports.add((area, java_class.fqcn))
    return exports


def kebab_case(name: str) -> str:
    first = re.sub(r"([a-z0-9])([A-Z])", r"\1-\2", name)
    return re.sub(r"([A-Z]+)([A-Z][a-z])", r"\1-\2", first).lower()


def method_name(fixture: Fixture) -> str:
    name = fixture.fqcn.rsplit(".", 1)[-1]
    prefix = "" if fixture.area == "core" else fixture.area
    combined = prefix + name
    return combined[:1].lower() + combined[1:] + "Basic"


def default_expression(target: str, fqcn: str) -> str:
    name = fqcn.rsplit(".", 1)[-1]
    suffix = CONSTRUCTOR_OVERRIDES.get((target, name), "()")
    return f"new {fqcn}{suffix}"


def seed_catalogue() -> list[Fixture]:
    fixtures = []
    for target in TARGETS.values():
        for area, fqcn in sorted(discover_exports(target)):
            name = fqcn.rsplit(".", 1)[-1]
            fixtures.append(
                Fixture(
                    target=target.name,
                    area=area,
                    fqcn=fqcn,
                    fixture_id=f"{area}/{kebab_case(name)}/basic",
                    expression=default_expression(target.name, fqcn),
                )
            )
    return fixtures


def read_catalogue() -> list[Fixture]:
    fixtures = []
    for line_number, line in enumerate(CATALOGUE.read_text(encoding="utf-8").splitlines(), 1):
        if not line or line.startswith("#"):
            continue
        parts = line.split("\t", 4)
        if len(parts) != 5:
            raise ValueError(f"{CATALOGUE}:{line_number}: expected five tab-separated fields")
        fixtures.append(Fixture(*parts))
    return fixtures


def render_catalogue(fixtures: list[Fixture]) -> str:
    lines = [
        "# target\tarea\tclass\tfixture_id\tdirect_construction_expression",
        "# Generated once, then maintained as the independent fixture registry.",
    ]
    lines.extend(
        "\t".join((item.target, item.area, item.fqcn, item.fixture_id, item.expression))
        for item in sorted(fixtures, key=lambda item: (item.target, item.area, item.fqcn))
    )
    return "\n".join(lines) + "\n"


def render_fixtures(target: Target, fixtures: list[Fixture]) -> str:
    methods = []
    for fixture in fixtures:
        methods.append(
            "    static IsWidget %s() {\n        return %s;\n    }"
            % (method_name(fixture), fixture.expression)
        )
    return """// Generated by scripts/generate-teavm-widget-tests.py. Do not edit.
package %s;

import com.google.gwt.user.client.ui.IsWidget;

final class %sFixtures {
    private %sFixtures() {
    }

%s
}
""" % (target.package_name, target.class_stem, target.class_stem, "\n\n".join(methods))


def render_tests(target: Target, fixtures: list[Fixture]) -> str:
    tests = []
    for fixture in fixtures:
        tests.append(
            "    @Test\n    public void %sIsReachable() {\n"
            "        WidgetFixtureVerifier.verify(\"%s\", %sFixtures.%s());\n    }"
            % (
                method_name(fixture),
                fixture.fixture_id,
                target.class_stem,
                method_name(fixture),
            )
        )
    return """// Generated by scripts/generate-teavm-widget-tests.py. Do not edit.
package %s;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.teavm.junit.SkipJVM;
import org.teavm.junit.TeaVMTestRunner;

@RunWith(TeaVMTestRunner.class)
@SkipJVM
public class %sWidgetReachabilityTest {
%s
}
""" % (target.package_name, target.class_stem, "\n\n".join(tests))


def render_verifier(target: Target) -> str:
    return """// Generated by scripts/generate-teavm-widget-tests.py. Do not edit.
package %s;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

final class WidgetFixtureVerifier {
    private WidgetFixtureVerifier() {
    }

    static void verify(String fixtureId, IsWidget fixture) {
        Widget widget = fixture.asWidget();
        assertTrue("fixture must expose a widget", widget != null);
        assertNull("fixture must start without a parent", widget.getParent());
        assertFalse("fixture must start detached", widget.isAttached());
        widget.getElement().setAttribute("data-testid", fixtureId);

        RootPanel root = RootPanel.get();
        root.add(widget);
        try {
            assertSame("root owns mounted fixture", root, widget.getParent());
            assertTrue("mounted fixture is attached", widget.isAttached());
            assertTrue("mounted fixture has a DOM parent", widget.getElement().hasParentElement());
            assertEquals(fixtureId, widget.getElement().getAttribute("data-testid"));
        } finally {
            widget.removeFromParent();
        }

        assertNull("detached fixture has no parent", widget.getParent());
        assertFalse("detached fixture is not attached", widget.isAttached());
        assertFalse("detached fixture has no DOM parent", widget.getElement().hasParentElement());
    }
}
""" % target.package_name


def generated_files(fixtures: list[Fixture]) -> dict[Path, str]:
    outputs: dict[Path, str] = {}
    for target_name, target in TARGETS.items():
        selected = sorted(
            (item for item in fixtures if item.target == target_name),
            key=lambda item: (item.area, item.fqcn),
        )
        outputs[target.output_root / f"{target.class_stem}Fixtures.java"] = render_fixtures(
            target, selected
        )
        outputs[
            target.output_root / f"{target.class_stem}WidgetReachabilityTest.java"
        ] = render_tests(target, selected)
        outputs[target.output_root / "WidgetFixtureVerifier.java"] = render_verifier(target)
    return outputs


def validate_catalogue(fixtures: list[Fixture]) -> list[str]:
    errors = []
    fixture_keys = {(item.target, item.area, item.fqcn) for item in fixtures}
    fixture_ids: set[tuple[str, str]] = set()
    methods: set[tuple[str, str]] = set()
    for fixture in fixtures:
        if fixture.target not in TARGETS:
            errors.append(f"unknown target in catalogue: {fixture.target}")
            continue
        id_key = (fixture.target, fixture.fixture_id)
        if id_key in fixture_ids:
            errors.append(f"duplicate fixture id for {fixture.target}: {fixture.fixture_id}")
        fixture_ids.add(id_key)
        method_key = (fixture.target, method_name(fixture))
        if method_key in methods:
            errors.append(f"duplicate generated method for {fixture.target}: {method_key[1]}")
        methods.add(method_key)
        if not fixture.expression.startswith("new "):
            errors.append(f"fixture is not a direct construction expression: {fixture.fixture_id}")

    for target_name, target in TARGETS.items():
        exports = {(target_name, area, fqcn) for area, fqcn in discover_exports(target)}
        registered = {item for item in fixture_keys if item[0] == target_name}
        for _, area, fqcn in sorted(exports - registered):
            errors.append(f"uncovered {target_name} export: {area} {fqcn}")
        for _, area, fqcn in sorted(registered - exports):
            errors.append(f"stale {target_name} fixture: {area} {fqcn}")
    return errors


def check_outputs(outputs: dict[Path, str]) -> list[str]:
    errors = []
    for path, expected in outputs.items():
        if not path.is_file():
            errors.append(f"missing generated source: {path.relative_to(ROOT)}")
        elif path.read_text(encoding="utf-8") != expected:
            errors.append(f"stale generated source: {path.relative_to(ROOT)}")
    return errors


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--seed-catalogue", action="store_true")
    parser.add_argument("--write", action="store_true")
    parser.add_argument("--check", action="store_true")
    args = parser.parse_args()

    if args.seed_catalogue:
        CATALOGUE.parent.mkdir(parents=True, exist_ok=True)
        CATALOGUE.write_text(render_catalogue(seed_catalogue()), encoding="utf-8")

    if not CATALOGUE.is_file():
        print(f"fixture catalogue does not exist: {CATALOGUE}", file=sys.stderr)
        return 1

    fixtures = read_catalogue()
    errors = validate_catalogue(fixtures)
    outputs = generated_files(fixtures)

    if args.write:
        for path, content in outputs.items():
            path.parent.mkdir(parents=True, exist_ok=True)
            path.write_text(content, encoding="utf-8")

    if args.check:
        errors.extend(check_outputs(outputs))

    if errors:
        print("TeaVM widget fixture check failed:", file=sys.stderr)
        for error in errors:
            print(f"- {error}", file=sys.stderr)
        return 1

    counts = {
        name: sum(1 for fixture in fixtures if fixture.target == name) for name in TARGETS
    }
    print(
        "TeaVM widget fixtures are current: "
        + ", ".join(f"{name}={count}" for name, count in counts.items())
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
