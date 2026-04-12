# Lab 4 — Configure, trigger & analyse (Module 4)

**Day 4 · CI pipeline design with automated testing**  
**Mix (deck):** ~40% theory / ~60% hands-on.

## What you use in this repo

- **Automation under test:** `lab2/maven-junit5/` (JUnit 5 + Maven Wrapper).
- **Live GitHub Actions:** [`.github/workflows/day4-pipeline.yml`](../.github/workflows/day4-pipeline.yml) — **unit** job (`-DexcludedGroups=integration`) then **integration** job (`-Dgroups=integration`) via `needs:`; **Surefire XML** uploaded with **`if: always()`** even when tests fail.
- **Integration sample:** `IntegrationFlowSampleTest.java` (`@Tag("integration")`) — slow/service-style checks in real life; here it is a short example for CI filtering.

## Task 1 — Working CI pipeline (green)

1. Read `day4-pipeline.yml` and identify: **triggers**, **jobs**, **`needs:`**, **cache**, **artefact steps**.
2. Push a small change on a **feature branch** (e.g. comment in a test or `README`) and open **Actions** → workflow **Day 4 — Lab2 multi-stage tests**.
3. Confirm both jobs are **green** and that **unit** ran before **integration**.

**Fork learners:** copy [`reference-workflows/`](reference-workflows/) files into your fork as needed (see table below).

## Task 2 — PR trigger, artefacts, merge

1. Open a **pull request** into `main`; confirm the workflow runs on **PR sync**.
2. Download an **artefact** (`surefire-unit-*` or `surefire-integration-*`) from the run summary; open a **`TEST-*.xml`** file in your IDE (or browser) and relate it to the **Tests** tab / summary line in the log.
3. Merge after green CI (per trainer branch protection).

Commands for **local** parity before you push:

```bash
cd lab2/maven-junit5
chmod +x mvnw
./mvnw -B test -DexcludedGroups=integration
./mvnw -B test -Dgroups=integration
```

## Task 3 — Analyse a failed build

1. Introduce a **deliberate failure** (wrong `assertThat` expected value, or broken import) in any test under `lab2/maven-junit5/src/test/java/`.
2. Push; note **time to red** in the Actions UI.
3. In the failed **unit** job log, find: non-zero exit, **Tests run / Failures**, and the **AssertionError** (or first useful stack line).
4. Fix, push, confirm **green**; capture the **annotated log** for your deliverable.

## Cross-tool references (Jenkins / Azure DevOps)

| Your platform | Start from |
|---------------|------------|
| GitHub Actions | This repo’s `day4-pipeline.yml` + [`reference-workflows/github-actions-matrix-snippet.yml`](reference-workflows/github-actions-matrix-snippet.yml) |
| Jenkins | [`reference-workflows/Jenkinsfile`](reference-workflows/Jenkinsfile) |
| Azure Pipelines | [`reference-workflows/azure-pipelines.yml`](reference-workflows/azure-pipelines.yml) |

Slides may show newer **Action** major versions (e.g. checkout v6); this course pins **v4** where stable — upgrade when your org standardises.

## Deliverables

Complete [`deliverables/LAB4_SUBMISSION_TEMPLATE.md`](deliverables/LAB4_SUBMISSION_TEMPLATE.md) (green run evidence + annotated failure + reflection).

## Instructor script

Timed theory, demo 01–03, and talking points: **[`DEMO_SCRIPT.md`](DEMO_SCRIPT.md)**.
