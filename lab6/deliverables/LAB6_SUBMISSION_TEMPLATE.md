# Lab 6 — Submission template

**Name:** ______________________ **Date:** ______________________

## Task 1 — Docker image

- [ ] Screenshot or log: **`docker build -f Dockerfile.test …`** success
- [ ] Screenshot or log: **`docker run --rm … mvn test`** with tests listed

## Task 2 — Compose

- [ ] Screenshot or pasted log showing **Postgres healthy** then **test-runner** **BUILD SUCCESS**
- [ ] Command used for teardown: ______________________

## Task 3 — Documentation & CI

- [ ] Attached: completed **`ENVIRONMENT_CONFIG_TEMPLATE.md`**
- [ ] Link or screenshot: **Day 6 — Docker test environment** workflow green
- [ ] Optional: path to downloaded **Surefire** artefact from CI

## Reflection

One reason **ephemeral Compose** beats a **long-lived shared** DB for CI:

-
