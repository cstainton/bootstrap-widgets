# Behaviour specifications

This module packages the shared Gherkin specifications used by the GWT and
TeaVM runners. Bootstrap 3 showcase behaviour is the compatibility baseline.

Every scenario declares:

- a stable specification ID;
- the fixture it exercises;
- whether the fixture is constructed, mounted or selected;
- the Bootstrap 3 showcase route and section from which the expectation came;
- subject, contract and execution tags;
- applicability to GWT Bootstrap 3, TeaVM Bootstrap 3, GWT Bootstrap 5 and
  TeaVM Bootstrap 5.

The authoritative tag and fixture-state definitions are in the
[testing plan](../../TESTING-PLAN.md#tag-vocabulary). In particular,
`@dom-contract` and `@style-contract` do not imply `@browser`: detached markup
and static stylesheet rules can be inspected without a browser engine.

`showcase-behaviour-inventory.tsv` is generated from the feature files. Update
it with:

```sh
python3 scripts/generate-behaviour-inventory.py --write
```

Maven validation checks that the committed inventory is current. Feature
files are the source of truth; the TSV is the reviewable coverage report and
four-target matrix.
