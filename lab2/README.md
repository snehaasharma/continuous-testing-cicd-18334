# Lab 2 — Set up, configure & execute (Module 2)

**Day 2 · Test automation frameworks for CI/CD**  
Primary stack: **Maven + JUnit 5** (no browser required — Page Object pattern is shown with plain Java “pages” so the session stays fast and stable). Slides also reference **TestNG**, **Gradle**, and **Selenium/Playwright**; see “Extensions” below.

## What’s in this folder

| Path | Purpose |
|------|---------|
| [`DEMO_SCRIPT.md`](DEMO_SCRIPT.md) | **2-hour session script** — theory time boxes, live demo steps, and what to say |
| [`maven-junit5/`](maven-junit5/) | CI-ready skeleton: `pages/`, `tests/`, `testdata/`, `config.properties`, Surefire |
| [`deliverables/`](deliverables/) | Templates for screenshots and CLI logs |

## Prerequisites

- **JDK 17+** (Temurin or Oracle).
- Network on first run (Maven Wrapper downloads Maven if needed).

## Hands-on — Task 1: Import & verify

1. Open `maven-junit5/` in your IDE as a **Maven** project.
2. Resolve dependencies (IDE action or terminal):

   ```bash
   cd maven-junit5
   chmod +x mvnw
   ./mvnw dependency:resolve
   ```

3. Map folders to roles from the deck: `src/main/java/.../pages`, `src/test/java/.../tests`, `src/test/resources/testdata`, `config.properties`.

## Hands-on — Task 2: Build tool configuration

The starter `pom.xml` already includes:

- **Surefire** with **`parallel=methods`** and **`threadCount=2`**
- JUnit 5 **parallel** execution via `configurationParameters`

Your tasks:

1. Open `pom.xml` and locate `maven-surefire-plugin`; adjust `threadCount` (e.g. to `4`) and re-run tests.
2. Find tests annotated with **`@Tag("smoke")`** (`SmokeRoutingTest`, parameterized rows in `LoginDataDrivenTest`).
3. **Optional (JUnit 5):** add a `<profile>` in `pom.xml` that sets Surefire `groups` to `smoke` so `mvn test -Psmoke` runs only tagged tests without typing `-Dgroups=smoke`.

**TestNG equivalent (reference only):** `mvn test -Dgroups=smoke` with `groups` on `@Test`.  
**Gradle reference (from slides):** `test { useJUnitPlatform(); maxParallelForks = ... }` — mirror the same tags with `includeTags 'smoke'` in Gradle if you scaffold a Gradle copy.

## Hands-on — Task 3: CLI execution & reports

From `maven-junit5/`:

```bash
./mvnw test
./mvnw test -Dgroups=smoke
./mvnw test -Dtest=SmokeRoutingTest
echo $?   # 0 = success; non-zero after a failing build = CI gate
```

**HTML report (Maven):**

```bash
./mvnw surefire-report:report
```

Open **`target/reports/surefire.html`** in a browser (path may vary slightly by plugin version).

**JUnit XML (for Jenkins / GitHub Actions):** Surefire writes XML under **`target/surefire-reports/`**.

### Deliberate failure (exit code / logs)

Temporarily change an assertion in `RegressionExtrasTest` so a test fails, run `./mvnw test`, capture the console output, and note **non-zero exit code** — that is how CI fails the build.

## Extensions (optional homework)

- Add **Selenium** or **Playwright** to `LoginPage` and drive a real browser; keep URLs and credentials in **env / properties** only.
- Scaffold the same tests in **Gradle** with `useJUnitPlatform()` and compare reports under `build/reports/tests/`.
- Add **Allure** for richer HTML history (slide mention).

## Day 4 (CI pipelines)

`IntegrationFlowSampleTest` is tagged **`integration`** so CI can run **unit** (`-DexcludedGroups=integration`) then **integration** (`-Dgroups=integration`). See **[`../lab4/README.md`](../lab4/README.md)** and **`.github/workflows/day4-pipeline.yml`** at the repo root.

## Deliverables

Complete [`deliverables/LAB2_SUBMISSION_TEMPLATE.md`](deliverables/LAB2_SUBMISSION_TEMPLATE.md) (report screenshot + CLI log snippet).
