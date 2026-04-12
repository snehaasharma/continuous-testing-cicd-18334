# Day 2 — Live demo & 2-hour session script

**Module 2 · Test automation frameworks for CI/CD**  
Use this alongside `Day2_ContinuousTesting_CICD.pdf` and the `maven-junit5/` project. **Target mix:** ~45% theory / ~55% hands-on (adjust if the group is slower).

**Pre-flight (before participants arrive):**

- Clone/open `lab2/maven-junit5` in your IDE; run `./mvnw test` once (warms `.m2` / Wrapper).
- Open `target/reports/surefire.html` after `surefire-report:report` so the path is fresh in your history.
- Duplicate IDE tab: `pom.xml`, one test class, `LoginPage.java`, `login-scenarios.csv`.

---

## Timeline overview (120 minutes)

| Block | Minutes | Focus |
|-------|---------|--------|
| A | 0–8 | Welcome, outcomes, how this ties to Day 1 pipelines |
| B | 8–20 | Framework architectures (linear → hybrid) |
| C | 20–32 | TestNG vs JUnit 5 vs PyTest (decision guide, not a deep dive) |
| D | 32–42 | Data-driven & keyword-driven (slide examples) |
| E | 42–52 | CI-friendly design principles |
| F | 52–65 | Maven Surefire / Gradle test (slide content + `pom.xml` pointer) |
| **G** | **65–82** | **Live demo 1–3 (framework → CLI → reports)** |
| H | 82–112 | Hands-on lab (Tasks 1–3) + roam |
| I | 112–118 | Deliverables recap + key takeaways |
| J | 118–120 | Recap quiz (optional quick verbal) |

*If running short:* trim blocks B/C by 2–3 minutes each; keep **G** and **H** intact.

---

## Block A — Welcome & outcomes (0–8 min)

**Say:**

- “Yesterday we mapped *where* tests live in CI. Today we build the *engine*: frameworks, build tools, and reports — what actually runs when the pipeline hits `mvn test` or `pytest`.”
- Read the three learning outcomes from the deck (design for CI, modular tests, Maven/Gradle wiring).

**Show:** Title slide + learning outcomes slide.

---

## Block B — Architectures (8–20 min)

**Say:**

- Linear / record–playback: fine for a demo, poison for CI maintenance.
- Modular + data-driven + POM → hybrid is what most CI-first teams evolve toward.
- “We’re not picking one religion today; we’re picking *patterns* that survive parallel runs and fast feedback.”

**Show:** Architecture comparison slide; draw **hybrid = POM + data + tags** on the whiteboard.

---

## Block C — TestNG vs JUnit 5 vs PyTest (20–32 min)

**Say:**

- **TestNG:** suites (`testng.xml`), groups, parallel, HTML reports — common with large Selenium jobs.
- **JUnit 5:** default for modern Java/Spring; extensions & parameterized tests; pair with Allure or Surefire report for HTML.
- **PyTest:** fixtures, plugins, `pytest --junitxml` — same *contract* with CI: exit code + XML.

**Show:** Comparison slide. **Bridge:** “Our lab uses **JUnit 5 + Maven** so everyone hits the same commands; your stack might differ but the *integration points* are the same.”

---

## Block D — DDT & KDT (32–42 min)

**Say:**

- DDT: one test method, many data rows — fewer copies of logic, CI runs all variants automatically.
- KDT: keywords map to functions — great for mixed skill sets; cost is building the keyword library.

**Show:** Slide with `@ParameterizedTest` / keyword table. **Tease lab:** “We’ll drive tests from a **CSV** in the repo — same idea as Excel/DB in real projects.”

---

## Block E — CI-friendly design (42–52 min)

**Say:**

- Independent, idempotent tests → safe **parallel** Surefire/Gradle.
- Fast feedback → keep unit-tier tests out of the network unless that’s the point.
- Flaky tests → worse than no tests in CI; quarantine and fix.
- Config via **env/properties** — point at `FrameworkConfig` + `BASE_URL` in the lab.

**Show:** CI-friendly slide + quick open `config.properties` in IDE.

---

## Block F — Maven & Gradle wiring (52–65 min)

**Say:**

- **Surefire** = unit test phase (`mvn test`); **Failsafe** = integration (`mvn verify`) — mention lifecycle briefly.
- **Gradle** `test` task + `useJUnitPlatform()` — same story.

**Show:** Slide snippets. **Live in IDE:** open `maven-junit5/pom.xml`, scroll to `maven-surefire-plugin`: `parallel`, `threadCount`, `includes`, JUnit `configurationParameters`.

---

## Block G — Live demo: framework → CLI → reports (65–82 min)

**Environment:** Terminal + IDE visible; `cd` to `lab2/maven-junit5`.

### Demo 1 — Project structure & POM (~7 min)

**Do & narrate:**

1. Tree or IDE project view: `src/main/java/.../pages`, `src/test/java/.../tests`, `src/test/resources/testdata`, `config.properties`.
2. Open `LoginPage.java`: “This is **Page Object style** without a browser — methods hide the ‘how’, tests read like workflows.”
3. Open `LoginDataDrivenTest.java`: “**Parameterized** test + **`@Tag("smoke")`** — we’ll filter this from the CLI like CI would for a PR gate.”
4. Open `login-scenarios.csv`: “Data lives next to code; PRs version data with tests.”

**Key line:** “Selenium would sit *inside* the page class; the **test and CI wiring stay the same**.”

### Demo 2 — CLI execution (~6 min)

**Run aloud:**

```bash
cd lab2/maven-junit5
./mvnw test
```

Point at **Tests run / Failures / BUILD SUCCESS**, then:

```bash
./mvnw test -Dgroups=smoke
```

Contrast **fewer tests** = **selective CI** (smoke on PR, full suite on main/nightly).

Optional:

```bash
./mvnw test -Dtest=SmokeRoutingTest
```

**Say:** “Exit code **0** lets the pipeline continue; **non-zero** stops the merge — that’s the quality gate.”

### Demo 3 — Reports (~4 min)

```bash
./mvnw surefire-report:report
```

Open **`target/reports/surefire.html`**. Mention **`target/surefire-reports/*.xml`** as what Jenkins / GitHub Actions ingest.

**Optional 60s:** Break one assertion, re-run `./mvnw test`, show red output + `echo $?` → `1`.

**Checkpoint:** “That’s the **contract** between your framework and CI: **JUnit XML + exit code**.”

---

## Block H — Hands-on lab (82–112 min)

**Instructions:** Participants follow `lab2/README.md` Tasks 1–3.

**You roam** for:

- IDE import / Maven not found → use **`./mvnw`** from `maven-junit5/`.
- “No tests in smoke run” → typo in `-Dgroups=smoke` or missing `@Tag`.
- Report path → `target/reports/surefire.html` after `surefire-report:report`.

**Stretch:** Add Surefire profile for `-Psmoke` or bump `threadCount`.

---

## Block I — Deliverables & takeaways (112–118 min)

**Show:** Deliverables slide. Point to **LAB2_SUBMISSION_TEMPLATE.md**.

**Rapid-fire takeaways:** hybrid frameworks, TestNG vs JUnit vs PyTest roles, Surefire/Failsafe & Gradle, CI-friendly tests, **reports + exit code = CI contract**.

**Tease Day 3:** Git workflow, branches, PRs.

---

## Block J — Recap quiz (118–120 min)

Verbal or Slido: Surefire vs Failsafe; two TestNG vs JUnit differences; DDT definition; failed suite exit code.

---

## Speaker notes — phrases that land well

- “The pipeline doesn’t care about your IDE — only **CLI + exit code + artifacts**.”
- “Parallel is free until tests **share state** — then CI becomes random.”
- “Tags are how you say: **run this on every PR**, **run that nightly**.”
