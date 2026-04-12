# Day 4 — Live demo & 2-hour session script

**Module 4 · CI pipeline design with automated testing**  
Pair with `Day4_ContinuousTesting_CICD.pdf` and [`lab4/README.md`](README.md). **Target mix:** ~40% theory / ~60% hands-on and demo.

**Pre-flight**

- GitHub → **Actions** enabled; open [`.github/workflows/day4-pipeline.yml`](../.github/workflows/day4-pipeline.yml).
- Local: `lab2/maven-junit5` runs `./mvnw test` clean.
- Optional second tab: **Jenkinsfile** and **azure-pipelines.yml** in `lab4/reference-workflows/`.

---

## Timeline (120 minutes)

| Block | Minutes | Focus |
|-------|---------|--------|
| A | 0–6 | Welcome, outcomes, bridge from Day 3 (Git) → “what runs on the commit” |
| B | 6–20 | Stages, triggers, parallel vs sequential, matrix, fail-fast |
| C | 20–32 | GitHub Actions: checkout, Java, cache, secrets, `if: always()` |
| D | 32–42 | Parallel/matrix slide — shard story; when `fail-fast: false` |
| E | 42–52 | Jenkins declarative: `post { always { junit … } }` |
| F | 52–60 | Azure DevOps: stages/jobs/steps, publish results, `condition: always()` |
| G | 60–75 | Failure handling & flaky tests — logs, quarantine, retries discipline |
| **H** | **75–95** | **Live demo 01–03** (create/run pipeline, logs, artefact, red/green) |
| I | 95–115 | Hands-on Tasks 1–3 + support |
| J | 115–120 | Deliverables, takeaways, recap quiz |

*If behind:* shorten E or F by 4–5 min; keep **H** and **I**.*

---

## Block A — Outcomes (0–6 min)

**Say:** “Branches and PRs are useless if **CI doesn’t run the right tests at the right time**. Today we **design** that: stages, ordering, artefacts, and how to read a red build without panic.”

---

## Block B — Stages & triggers (6–20 min)

**Walk the slide diagram:** push/PR/schedule → checkout → build → unit → integration → quality gate → artefact.

**Say:**

- **Push:** fast unit loop; **PR:** broader gate; **merge to main:** regression + publish; **nightly:** slow E2E/perf.
- **Sequential** when order matters (integration **after** unit); **parallel** when jobs are independent.
- **Matrix:** OS × JDK; **fail-fast true** saves minutes but **hides** other shard failures — **`false`** when you want the full picture.

---

## Block C — GitHub Actions (20–32 min)

**Show:** Slide snippet vs **real** `day4-pipeline.yml`.

**Hit:**

- **Pin versions** (major tag or SHA for high security).
- **Cache** `~/.m2` via `setup-java` `cache: maven`.
- **`${{ secrets.* }}`** — never hardcode tokens.
- **`if: always()`** on **upload-artifact** so XML exists when tests fail — “otherwise you debug blind.”

---

## Block D — Matrix (32–42 min)

**Say:** “40 min → 10 min with four shards **only** if tests split cleanly and runners are available.”

**Show:** [`reference-workflows/github-actions-matrix-snippet.yml`](reference-workflows/github-actions-matrix-snippet.yml) — explain `fail-fast: false` vs `true` (quiz foreshadow).

---

## Block E — Jenkins (42–52 min)

**Open:** [`reference-workflows/Jenkinsfile`](reference-workflows/Jenkinsfile).

**Say:** Declarative vs scripted; **`agent`** (any vs Docker); **`junit`** in **`post { always {} }`** — same idea as GitHub’s always-upload.

**Say:** Jenkins still wins many on-prem/compliance shops; Actions wins GitHub-native SaaS teams — **context**, not religion.

---

## Block F — Azure DevOps (52–60 min)

**Open:** [`reference-workflows/azure-pipelines.yml`](reference-workflows/azure-pipelines.yml).

**Say:** stages → jobs → steps; **PublishTestResults** with **`condition: always()`**; Boards/Test Plans vs run summary.

---

## Block G — Failures & flaky tests (60–75 min)

**Say:**

- Read **exit code**, **summary line**, then **deepest assertion** — not the whole noise stack first.
- **Flaky:** detect, **quarantine** with owner+SLA, fix root cause — retries are **surgery**, not policy.
- Mention slide stat (Jira flakiness) as “why this is a career skill.”

---

## Block H — Live demo 01–03 (75–95 min)

**Use:** This repository (or your training fork).

### Demo 01 — Pipeline with test stages (~8 min)

1. **Actions** → **Day 4 — Lab2 multi-stage tests** → show **triggers** (push / PR / manual).
2. Walk **unit-tests** then **integration-tests** — point at **`needs: unit-tests`**.
3. Show **setup-java** + **cache** step timing in the log.

### Demo 02 — Logs & artefacts (~7 min)

1. Open a **green** run → expand **Run unit tests** → **Tests run: … BUILD SUCCESS**.
2. Download **`surefire-unit-…`** artefact; open one **`TEST-…xml`** — “same XML Jenkins/Azure ingest.”
3. **Say:** “PR checks use the same pass/fail as **exit code** from Maven.”

### Demo 03 — Failure → fix (~8 min)

1. On a **demo branch**, break a test (e.g. `assertThat(1).isEqualTo(2)` in `SmokeRoutingTest`).
2. Push → show **red** job; **integration** job **skipped** because **`needs:`** failed — “ordering is a gate.”
3. In log: **FAILURE**, **Failures: 1**, **AssertionError** line.
4. Revert/fix → push → **green**; optional: show artefact still uploaded from failed run thanks to **`if: always()`**.

**Optional 3 min:** Uncomment or show matrix snippet in reference file — “four runners, `fail-fast: false`, full visibility.”

---

## Block I — Hands-on (95–115 min)

Participants follow [`lab4/README.md`](README.md).

**Common issues:**

- Wrong directory — must be `lab2/maven-junit5` for Maven commands.
- **Integration job “skipped”** — explain **`needs:`** after unit failure.
- **No artefact** — failed before Surefire wrote XML; check **if: always()** and path `target/surefire-reports/`.

---

## Block J — Close (115–120 min)

Deliverables slide; **quiz:** `needs:` purpose; `always()`; fail-fast matrix; two causes of CI-only flakes.

---

## Sound bites

- “**Tests are the product** of the pipeline — everything else is scaffolding.”
- “**Green that lies** (flakes) is worse than **red that explains** (good logs + XML).”
