# Environment configuration — test repo (Day 6 deliverable)

**Author:** ______________________ **Date:** ______________________

## 1. Runtime variables

| Variable | Required? | Example | Secret? | Purpose |
|----------|------------|---------|---------|---------|
| `DB_URL` | For Postgres path | `jdbc:postgresql://postgres:5432/testdb` | No | JDBC connection |
| `DB_USER` | Yes with Postgres | `testuser` | No | DB user |
| `DB_PASS` | Yes with Postgres | *(set in CI)* | **Yes** in real envs | DB password |

## 2. Where values are set

- **Local Compose:** `docker-compose.test.yml` `environment:` + shell `export DB_PASS=…`
- **GitHub Actions:** Workflow `env:` block; use **`secrets.*`** for passwords
- **Never:** committed in Dockerfile, compose defaults in public repos for real passwords, or logs

## 3. Seed data

- **Script path in repo:** ______________________
- **When it runs:** (e.g. Postgres `docker-entrypoint-initdb.d` on first volume init)
- **How to reset after schema change:** `docker compose … down -v`

## 4. Compliance

- **Production data in test?** Must be **No** — synthetic or masked only.
- **PII in seeds?** Use fictional emails/phones only.

## 5. Optional: staging alignment

How this test stack maps to **staging** URLs / VPN / service accounts:

-
