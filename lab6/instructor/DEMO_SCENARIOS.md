# Day 6 — Instructor scenarios

## A — Healthcheck failure

Temporarily set `pg_isready` to a **wrong user** in `healthcheck` — show **test-runner** never starts or times out — restore.

## B — Stale volume / seed “didn’t apply”

Run compose **twice** without `-v` after changing `01-seed.sql` — show old data; fix with **`docker compose down -v`**.

## C — Missing secret in CI

In workflow, set `DB_PASS:` to empty and remove compose default (not recommended live) — or narrate **wrong secret name** → Postgres auth fail.

## D — H2 vs Postgres (quiz hook)

Run `./mvnw test` on host — only **UnitSmokeTest**; in Compose — **PostgresSeedDataTest** runs — dialect / env differences.
