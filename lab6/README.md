# Lab 6 — Containerise, seed & configure (Module 6)

**Day 6 · Test environment, containers & test data**  
**Mix (deck):** ~40% theory / ~60% hands-on.

## Layout

| Path | Purpose |
|------|---------|
| [`maven-docker-demo/`](maven-docker-demo/) | Minimal **Maven** module: unit tests + **JDBC** tests gated on `DB_URL` containing `postgresql`. |
| [`maven-docker-demo/Dockerfile.test`](maven-docker-demo/Dockerfile.test) | **Temurin Alpine** + **Maven Wrapper** + `dependency:go-offline` cache layer. |
| [`docker-compose.test.yml`](docker-compose.test.yml) | **postgres:16-alpine** + **healthcheck** + **test-runner** (`depends_on: service_healthy`). |
| [`maven-docker-demo/src/test/resources/data/01-seed.sql`](maven-docker-demo/src/test/resources/data/01-seed.sql) | Mounted into **`/docker-entrypoint-initdb.d`** — reproducible seed. |
| [`reference/TestcontainersNotes.md`](reference/TestcontainersNotes.md) | Optional **Testcontainers** extension (host Docker; not inside Compose runner). |
| [`DEMO_SCRIPT.md`](DEMO_SCRIPT.md) | **2-hour** instructor script + demo blocks. |
| [`deliverables/`](deliverables/) | Submission + environment doc template. |

**CI:** [`.github/workflows/day6-docker-pipeline.yml`](../.github/workflows/day6-docker-pipeline.yml)

## Prerequisites

- **Docker Desktop** / Docker Engine + Compose v2 locally.
- **JDK 17** for `mvnw test` on the host (Postgres tests **skip** unless `DB_URL` points at PostgreSQL).

## Task 1 — `Dockerfile.test`

From **`lab6/maven-docker-demo/`**:

```bash
docker build -f Dockerfile.test -t lab6-test:local .
docker run --rm lab6-test:local ./mvnw test -B
```

Expect **2** tests (Postgres tests skipped without `DB_URL`).

## Task 2 — Compose + database

From **`lab6/`** (repository-relative paths in compose):

```bash
export DB_PASS=testpass   # optional; default is testpass in compose
docker compose -f docker-compose.test.yml up --build --abort-on-container-exit --exit-code-from test-runner
docker compose -f docker-compose.test.yml down -v
```

**Observe:** Postgres **healthy** before `test-runner` starts; **no published host ports** — only service name `postgres`.

## Task 3 — Config & CI evidence

1. Read [`maven-docker-demo/src/test/resources/application-test.properties`](maven-docker-demo/src/test/resources/application-test.properties) — env contract (Spring teams map to `spring.datasource.*`).
2. Complete **[`deliverables/ENVIRONMENT_CONFIG_TEMPLATE.md`](deliverables/ENVIRONMENT_CONFIG_TEMPLATE.md)**.
3. Open **Actions** → **Day 6 — Docker test environment**; download **Surefire** artefact after a green run.

### Copy reports locally (slide pattern)

After a compose run (before `down`):

```bash
mkdir -p surefire-out
docker compose -f docker-compose.test.yml cp test-runner:/app/target/surefire-reports ./surefire-out/
```

## Secrets

- Do **not** commit real passwords. For training, compose uses **`${DB_PASS:-testpass}`**. In production CI, set **`secrets.TEST_DB_PASS`** and pass `DB_PASS: ${{ secrets.TEST_DB_PASS }}` in the workflow `env` block.

## JDK note

Slides use **JDK 21** in the Dockerfile example; this repo uses **JDK 17** to match earlier labs — bump the base image tag when you adopt 21 everywhere.
