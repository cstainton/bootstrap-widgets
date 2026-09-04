#!/usr/bin/env python3
"""Validate Gherkin metadata and generate its reviewable target matrix."""

from __future__ import annotations

import argparse
from dataclasses import dataclass
from pathlib import Path
import re
import sys


ROOT = Path(__file__).resolve().parent.parent
SPEC_ROOT = ROOT / "testing/bootstrap-widget-specifications/src/main/resources/features"
INVENTORY = (
    ROOT
    / "testing/bootstrap-widget-specifications/src/main/resources"
    / "io/instanto/bootstrap/testing/showcase-behaviour-inventory.tsv"
)
REQUIRED_FEATURES = {
    "bootstrap-select.feature",
    "button-groups.feature",
    "buttons.feature",
    "collapse.feature",
    "dropdowns.feature",
    "forms.feature",
    "input-groups.feature",
    "overlays.feature",
    "resources.feature",
    "suggest-box.feature",
    "tabs.feature",
    "themes.feature",
    "widget-lifecycle.feature",
}
TARGET_TAGS = ("gwt3", "teavm3", "gwt5", "teavm5")
UNSUPPORTED_TARGET_TAGS = {target: f"unsupported-{target}" for target in TARGET_TAGS}
CONTRACT_TAGS = (
    "functional",
    "api-contract",
    "dom-contract",
    "style-contract",
    "artifact-contract",
)
DEPRECATED_TAGS = ("api", "rendered")
SCENARIO_RE = re.compile(r"^\s*Scenario:\s+([A-Z]+-[0-9]{3})\s+(.+?)\s*$")
FIXTURE_RE = re.compile(r'^\s*Given fixture "([^"]+)" is (constructed|mounted|selected)\s*$')
BASELINE_RE = re.compile(
    r'^\s*Given Bootstrap 3 showcase route "([^"]+)" section "([^"]+)" defines the baseline\s*$'
)


@dataclass(frozen=True)
class Scenario:
    spec_id: str
    feature: str
    name: str
    fixture: str
    fixture_state: str
    route: str
    section: str
    tags: tuple[str, ...]


def tags(line: str) -> tuple[str, ...]:
    return tuple(part[1:] for part in line.strip().split() if part.startswith("@"))


def parse_feature(path: Path) -> list[Scenario]:
    lines = path.read_text(encoding="utf-8").splitlines()
    pending_tags: tuple[str, ...] = ()
    feature_tags: tuple[str, ...] = ()
    records: list[Scenario] = []
    current: tuple[str, str, tuple[str, ...], list[str]] | None = None

    def finish() -> None:
        nonlocal current
        if current is None:
            return
        spec_id, name, scenario_tags, body = current
        fixture_matches = [FIXTURE_RE.match(line) for line in body]
        fixture_values = [match.group(1) for match in fixture_matches if match]
        fixture_modes = [match.group(2) for match in fixture_matches if match]
        baseline_matches = [BASELINE_RE.match(line) for line in body]
        baseline_values = [match.groups() for match in baseline_matches if match]
        if len(fixture_values) != 1:
            raise ValueError(f"{path.name}:{spec_id}: expected exactly one fixture declaration")
        if len(baseline_values) != 1:
            raise ValueError(f"{path.name}:{spec_id}: expected exactly one baseline declaration")
        combined_tags = tuple(dict.fromkeys(feature_tags + scenario_tags))
        missing = [
            target
            for target in TARGET_TAGS
            if target not in combined_tags
            and UNSUPPORTED_TARGET_TAGS[target] not in combined_tags
        ]
        if missing:
            raise ValueError(
                f"{path.name}:{spec_id}: targets must be required or explicitly unsupported: "
                + ", ".join(missing)
            )
        contradictory = [
            target
            for target in TARGET_TAGS
            if target in combined_tags
            and UNSUPPORTED_TARGET_TAGS[target] in combined_tags
        ]
        if contradictory:
            raise ValueError(
                f"{path.name}:{spec_id}: targets cannot be both required and unsupported: "
                + ", ".join(contradictory)
            )
        deprecated = [tag for tag in DEPRECATED_TAGS if tag in combined_tags]
        if deprecated:
            raise ValueError(
                f"{path.name}:{spec_id}: deprecated tags {', '.join('@' + tag for tag in deprecated)}"
            )
        if not any(contract in combined_tags for contract in CONTRACT_TAGS):
            raise ValueError(
                f"{path.name}:{spec_id}: expected at least one contract tag: "
                + ", ".join("@" + tag for tag in CONTRACT_TAGS)
            )
        if (
            "widget" in combined_tags
            and "api-contract" in combined_tags
            and fixture_modes[0] != "constructed"
        ):
            raise ValueError(
                f"{path.name}:{spec_id}: widget @api-contract fixtures must be constructed, not "
                f"{fixture_modes[0]}"
            )
        if "functional" in combined_tags and fixture_modes[0] != "mounted":
            raise ValueError(
                f"{path.name}:{spec_id}: @functional fixtures must be mounted, not "
                f"{fixture_modes[0]}"
            )
        for browser_contract in ("javascript", "layout"):
            if browser_contract in combined_tags and "browser" not in combined_tags:
                raise ValueError(
                    f"{path.name}:{spec_id}: @{browser_contract} requires explicit @browser"
                )
        route, section = baseline_values[0]
        records.append(
            Scenario(
                spec_id,
                path.name,
                name,
                fixture_values[0],
                fixture_modes[0],
                route,
                section,
                combined_tags,
            )
        )
        current = None

    for line in lines:
        stripped = line.strip()
        if stripped.startswith("@"):
            pending_tags = tags(stripped)
            continue
        if stripped.startswith("Feature:"):
            feature_tags = pending_tags
            pending_tags = ()
            continue
        match = SCENARIO_RE.match(line)
        if match:
            finish()
            current = (match.group(1), match.group(2), pending_tags, [])
            pending_tags = ()
            continue
        if current is not None:
            current[3].append(line)
    finish()
    return records


def read_scenarios() -> list[Scenario]:
    paths = sorted(SPEC_ROOT.glob("*.feature"))
    names = {path.name for path in paths}
    missing = sorted(REQUIRED_FEATURES - names)
    if missing:
        raise ValueError("missing required behaviour features: " + ", ".join(missing))
    scenarios = [scenario for path in paths for scenario in parse_feature(path)]
    ids = [scenario.spec_id for scenario in scenarios]
    duplicates = sorted({spec_id for spec_id in ids if ids.count(spec_id) > 1})
    if duplicates:
        raise ValueError("duplicate specification IDs: " + ", ".join(duplicates))
    return sorted(scenarios, key=lambda item: item.spec_id)


def render(scenarios: list[Scenario]) -> str:
    header = (
        "# spec_id\tfeature\tscenario\tfixture_id\tfixture_state\tbootstrap3_route\t"
        "bootstrap3_section\tgwt3\tteavm3\tgwt5\tteavm5\ttags"
    )
    rows = [header]
    for scenario in scenarios:
        values = (
            scenario.spec_id,
            scenario.feature,
            scenario.name,
            scenario.fixture,
            scenario.fixture_state,
            scenario.route,
            scenario.section,
            *("required" if target in scenario.tags else "unsupported" for target in TARGET_TAGS),
            ",".join(scenario.tags),
        )
        rows.append("\t".join(values))
    return "\n".join(rows) + "\n"


def main() -> int:
    parser = argparse.ArgumentParser()
    action = parser.add_mutually_exclusive_group(required=True)
    action.add_argument("--check", action="store_true")
    action.add_argument("--write", action="store_true")
    args = parser.parse_args()

    try:
        scenarios = read_scenarios()
        expected = render(scenarios)
        if args.write:
            INVENTORY.parent.mkdir(parents=True, exist_ok=True)
            INVENTORY.write_text(expected, encoding="utf-8")
        elif not INVENTORY.exists() or INVENTORY.read_text(encoding="utf-8") != expected:
            raise ValueError(
                "behaviour inventory is stale; run scripts/generate-behaviour-inventory.py --write"
            )
        print(f"Behaviour inventory is current: {len(scenarios)} scenarios")
        return 0
    except ValueError as error:
        print(error, file=sys.stderr)
        return 1


if __name__ == "__main__":
    raise SystemExit(main())
