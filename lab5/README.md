# Lab 5 — Create, integrate, validate (Module 5)

**Day 5 · API & service-level continuous testing**  
**Mix (deck):** ~35% theory / ~65% hands-on.

## Contents

| Path | Purpose |
|------|---------|
| [`maven-api-tests/`](maven-api-tests/) | **REST Assured 6.0** + **JSON Schema** + **WireMock** (no external API required in CI). Failsafe: `*IT.java`, `@Tag("api")`. |
| [`postman/`](postman/) | **Postman collection** + **env-ci.json** for **Newman** (default `jsonplaceholder.typicode.com` — needs outbound network in CI). |
| [`DEMO_SCRIPT.md`](DEMO_SCRIPT.md) | **2-hour** instructor script + live demo blocks. |
| [`deliverables/`](deliverables/) | Submission templates. |

**Live CI:** [`.github/workflows/day5-api-pipeline.yml`](../.github/workflows/day5-api-pipeline.yml) — **unit-smoke (Lab2)** → **REST Assured** → **Newman**; Failsafe + Newman JUnit XML uploaded with **`if: always()`**.

## Task 1 — REST Assured suite (`mvn verify -Dgroups=api`)

1. Open `lab5/maven-api-tests` in your IDE as a Maven project.
2. Study `UserApiIT.java`: **given/when/then**, JSONPath, **schema** (`matchesJsonSchemaInClasspath`), **404** and **POST 201** patterns.
3. Run locally:

   ```bash
   cd lab5/maven-api-tests
   chmod +x mvnw
   ./mvnw verify -Dgroups=api
   ```

4. **Optional:** set **`API_BASE_URL`** to a real staging base (no trailing path) — stubs are skipped; tests must match real API behaviour.

## Task 2 — Wire into CI

1. Read `day5-api-pipeline.yml` and note **`needs:`**, **`vars.API_BASE_URL`**, and artefact steps.
2. In GitHub: **Settings → Secrets and variables → Actions → Variables** — add **`API_BASE_URL`** only if you use a real service (optional for class; WireMock is default).
3. Push a branch / open a PR and confirm all three jobs; download **Failsafe** XML from artefacts.

## Task 3 — Negative tests, failure analysis, Newman

1. **REST Assured:** extend `UserApiIT` (e.g. another **CSV** row, header assertion, or schema tweak).
2. **Break on purpose:** change a schema or status expectation, push, capture **Failsafe** XML `<failure>` node — then fix and re-green.
3. **Newman:** run locally:

   ```bash
   npm install -g newman
   mkdir -p lab5/postman/results
   newman run lab5/postman/collection.json -e lab5/postman/env-ci.json \
     -r cli,junit --reporter-junit-export lab5/postman/results/newman-junit.xml
   ```

4. Relate Newman **JUnit** output to the slide checklist (status vs contract failures).

## Deliverables

[`deliverables/LAB5_SUBMISSION_TEMPLATE.md`](deliverables/LAB5_SUBMISSION_TEMPLATE.md)

## Versions

- **REST Assured 6.0** requires **Java 17+** (slide: use 5.5.x for Java 11).
- Postman/Newman need **Node** for CI (see workflow).
