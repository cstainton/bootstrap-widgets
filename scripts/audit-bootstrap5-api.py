#!/usr/bin/env python3
"""Compare the public Bootstrap 5 widget API with the Bootstrap 3 track."""

from __future__ import annotations

import argparse
import os
import re
import subprocess
import zipfile
from dataclasses import dataclass
from pathlib import Path


B3_PACKAGE = "org.gwtbootstrap3"
B5_PACKAGE = "org.gwtbootstrap5"


@dataclass(frozen=True)
class TypeShape:
    name: str
    parent: str | None
    interfaces: tuple[str, ...]
    members: tuple[str, ...]


def normalize(value: str) -> str:
    return value.replace(B3_PACKAGE, B5_PACKAGE)


def erase_generics(value: str) -> str:
    previous = None
    while previous != value:
        previous = value
        value = re.sub(r"<[^<>]*>", "", value)
    return value


def split_types(value: str) -> tuple[str, ...]:
    return tuple(part.strip() for part in erase_generics(value).split(",") if part.strip())


def javap(classpath: str, class_name: str) -> TypeShape:
    result = subprocess.run(
        ["javap", "-classpath", classpath, "-public", class_name],
        check=True,
        capture_output=True,
        text=True,
    )
    lines = [line.strip() for line in result.stdout.splitlines() if line.strip()]
    declaration = next(
        line for line in lines
        if re.match(r"(?:(?:public|protected) )?(?:(?:abstract|final) )*(?:class|interface|enum) ", line)
    )
    plain_declaration = erase_generics(declaration.rstrip(" {") )

    parent_match = re.search(r"\bextends ([^ ]+)", plain_declaration)
    parent = parent_match.group(1) if parent_match else None
    if " interface " in plain_declaration:
        parent = None

    interfaces_match = re.search(r"\bimplements (.+)$", plain_declaration)
    interfaces = split_types(interfaces_match.group(1)) if interfaces_match else ()
    if " interface " in plain_declaration:
        extends_match = re.search(r"\bextends (.+)$", plain_declaration)
        interfaces = split_types(extends_match.group(1)) if extends_match else ()

    members = tuple(
        normalize(line)
        for line in lines
        if line.startswith("public ") and line.endswith(";")
    )
    return TypeShape(normalize(class_name), normalize(parent) if parent else None,
                     tuple(normalize(item) for item in interfaces), members)


def top_level_widgets(jar: Path, package: str) -> list[str]:
    prefix = package.replace(".", "/") + "/client/ui/"
    with zipfile.ZipFile(jar) as archive:
        classes = []
        for name in archive.namelist():
            if not name.startswith(prefix) or not name.endswith(".class"):
                continue
            relative = name[len(prefix):-6]
            if "/" in relative or "$" in relative or relative == "package-info":
                continue
            classes.append(package + ".client.ui." + relative)
    return sorted(classes)


class ApiGraph:
    def __init__(self, jar: Path, source_package: str, dependency_classpath: str = ""):
        self.jar = jar
        self.source_package = source_package
        self.classpath = str(jar)
        if dependency_classpath:
            self.classpath += os.pathsep + dependency_classpath
        self.cache: dict[str, TypeShape | None] = {}

    def shape(self, normalized_name: str) -> TypeShape | None:
        source_name = normalized_name.replace(B5_PACKAGE, self.source_package)
        if normalized_name not in self.cache:
            try:
                self.cache[normalized_name] = javap(self.classpath, source_name)
            except (subprocess.CalledProcessError, StopIteration):
                self.cache[normalized_name] = None
        return self.cache[normalized_name]

    def contracts(self, normalized_name: str, seen: set[str] | None = None) -> set[str]:
        seen = set() if seen is None else seen
        if normalized_name in seen:
            return set()
        seen.add(normalized_name)
        shape = self.shape(normalized_name)
        if shape is None:
            return set()
        result = set(shape.interfaces)
        for interface in shape.interfaces:
            result.update(self.contracts(interface, seen))
        if shape.parent:
            result.update(self.contracts(shape.parent, seen))
        return result

    def public_api(self, normalized_name: str, root: bool = True,
                   seen: set[str] | None = None) -> set[str]:
        seen = set() if seen is None else seen
        if normalized_name in seen:
            return set()
        seen.add(normalized_name)
        shape = self.shape(normalized_name)
        if shape is None:
            return set()

        constructors_prefix = "public " + normalized_name + "("
        members = {
            member for member in shape.members
            if root or not member.startswith(constructors_prefix)
        }
        if shape.parent:
            members.update(self.public_api(shape.parent, False, seen))
        for interface in shape.interfaces:
            members.update(self.public_api(interface, False, seen))
        return members


def short_contract(name: str) -> str:
    return name.rsplit(".", 1)[-1]


def dependency_classpath(jar: Path) -> str:
    classpath_file = jar.parent / "api-audit-classpath.txt"
    if not classpath_file.is_file():
        subprocess.run(
            ["mvn", "-q", "-f", str(jar.parent.parent / "pom.xml"),
             "dependency:build-classpath", "-Dmdep.outputFile=target/api-audit-classpath.txt"],
            check=True,
        )
    return classpath_file.read_text(encoding="utf-8").strip()


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--bootstrap3-jar", type=Path,
                        default=Path("gwt-bootstrap3-modern/target/gwt-bootstrap3-modern-1.0-SNAPSHOT.jar"))
    parser.add_argument("--bootstrap5-jar", type=Path,
                        default=Path("gwt-bootstrap5-modern/target/gwt-bootstrap5-modern-1.0-SNAPSHOT.jar"))
    parser.add_argument("--output", type=Path, default=Path("BOOTSTRAP5-API-CONTRACTS.md"))
    args = parser.parse_args()

    for jar in (args.bootstrap3_jar, args.bootstrap5_jar):
        if not jar.is_file():
            parser.error(f"missing {jar}; run mvn -DskipTests package first")

    b3_classpath = dependency_classpath(args.bootstrap3_jar)
    b5_classpath = dependency_classpath(args.bootstrap5_jar)
    b3_graph = ApiGraph(args.bootstrap3_jar, B3_PACKAGE, b3_classpath)
    b5_graph = ApiGraph(args.bootstrap5_jar, B5_PACKAGE, b5_classpath)
    b3_classes = top_level_widgets(args.bootstrap3_jar, B3_PACKAGE)
    b5_classes = set(top_level_widgets(args.bootstrap5_jar, B5_PACKAGE))

    rows = []
    full_contract = 0
    distinct_missing_contracts: set[str] = set()
    for b3_class in b3_classes:
        normalized_class = normalize(b3_class)
        simple_name = normalized_class.rsplit(".", 1)[-1]
        present = normalized_class in b5_classes
        missing_contracts: set[str] = set()
        missing_members: set[str] = set()
        if present:
            missing_contracts = b3_graph.contracts(normalized_class) - b5_graph.contracts(normalized_class)
            missing_members = b3_graph.public_api(normalized_class) - b5_graph.public_api(normalized_class)
        if present and not missing_contracts and not missing_members:
            full_contract += 1
            status = "Full"
        elif present:
            status = "Reduced"
        else:
            status = "Missing"
        rows.append((simple_name, present, status, missing_contracts, missing_members))
        distinct_missing_contracts.update(missing_contracts)

    widgets_missing_interfaces = sum(1 for row in rows if row[3])
    widgets_missing_members = sum(1 for row in rows if row[4])

    lines = [
        "# Bootstrap 5 API Contract Audit",
        "",
        "Generated from the compiled Bootstrap 3 and Bootstrap 5 public APIs. Bootstrap 3 package names are normalized to `org.gwtbootstrap5` before comparison.",
        "",
        f"- Shared inventory: `{sum(1 for row in rows if row[1])}/{len(rows)}`",
        f"- Full normalized public contract: `{full_contract}/{len(rows)}`",
        f"- Reduced or missing contract: `{len(rows) - full_contract}/{len(rows)}`",
        f"- Widgets missing one or more assignable interfaces: `{widgets_missing_interfaces}`",
        f"- Distinct missing interface contracts: `{len(distinct_missing_contracts)}`",
        f"- Widgets missing one or more public API members: `{widgets_missing_members}`",
        "",
        "| Widget | Present | Contract | Missing interfaces | Missing API members |",
        "|---|---:|---|---:|---:|",
    ]
    for name, present, status, missing_contracts, missing_members in rows:
        contract_names = ", ".join(sorted(short_contract(item) for item in missing_contracts))
        lines.append(
            f"| `{name}` | {'yes' if present else 'no'} | {status} | "
            f"{len(missing_contracts)}{(' (' + contract_names + ')') if contract_names else ''} | "
            f"{len(missing_members)} |"
        )

    lines.extend([
        "",
        "## Interpretation",
        "",
        "`Full` means the Bootstrap 5 class retains every normalized Bootstrap 3 public interface and API member. Additional Bootstrap 5-native APIs are allowed and do not reduce the score. Deliberate Bootstrap 5 deviations should be documented in `BOOTSTRAP5-PORTING.md`.",
        "",
    ])
    args.output.write_text("\n".join(lines), encoding="utf-8")
    print(f"Wrote {args.output}: {full_contract}/{len(rows)} full contracts")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
