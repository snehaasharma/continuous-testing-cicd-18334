# Day 6 — Live demo & 2-hour session script

**Module 6 · Test environment, containers & test data**  
Use with `Day6_ContinuousTesting_CICD.pdf` and [`lab6/README.md`](README.md). **Target mix:** ~40% theory / ~60% hands-on.

**Pre-flight**

- Docker running locally; `docker compose version`.
- `lab6/maven-docker-demo`: `./mvnw test` (2 tests, Postgres skipped).
- Open `Dockerfile.test`, `docker-compose.test.yml`, `01-seed.sql`, `PostgresSeedDataTest.java`, `day6-docker-pipeline.yml`.

---

## Timeline (120 minutes)

| Block | Minutes | Focus |
|-------|---------|--------|
| A | 0–6 | Outcomes; “red env vs red code” |
| B | 6–18 | Provisioning strategies table (shared vs Compose vs Testcontainers vs cloud) |
| C | 18–32 | Dockerfile.test: Alpine, layers, **no secrets in image**, CMD |
| D | 32–45 | Compose: **healthcheck**, **depends_on**, **service DNS**, avoid DinD |
| E | 45–58 | Testcontainers: Ryuk, random ports, wait strategies; point to `reference/` |
| F | 58–70 | Test data: seed in Git, UUID prefixes, transactional rollback, **no prod PII** |
| G | 70–78 | CI: ubuntu + Docker, `--exit-code-from`, **cp** reports, **`down -v`**, artefacts |
| **H** | **78–98** | **Live demo 01–03** |
| I | 98–115 | Hands-on Tasks 1–3 |
| J | 115–120 | Deliverables, quiz, Day 7 teaser |

*Compress E or F by 4–5 min if behind; protect **H**.*

---

## Block A — Framing (0–6 min)

**Say:** “A flaky environment makes every green build suspicious. Containers give us **disposable truth** — same image in dev and CI.”

---

## Block B — Strategies (6–18 min)

Contrast **shared env** drift vs **Compose** freshness vs **Testcontainers** per-class vs **ephemeral cloud** cost.

---

## Block C — Dockerfile (18–32 min)

**Walk** `Dockerfile.test`: **go-offline** layer, **WORKDIR**, **`CMD mvn test`**.

**Say:** Smaller base = faster pulls; credentials only via **runtime env**.

---

## Block D — Compose (32–45 min)

**Show** `postgres` **healthcheck** + `test-runner` **`condition: service_healthy`**.

**Say:** **`postgres:5432`** on the **compose network**, not `localhost` on the host.

---

## Block E — Testcontainers (45–58 min)

**Narrate** slide code; open [`reference/TestcontainersNotes.md`](reference/TestcontainersNotes.md).

**Say:** **Ryuk** vs **`docker compose down -v`** — same hygiene goal.

---

## Block F — Data (58–70 min)

**Show** `01-seed.sql` + `PostgresSeedDataTest` UUID insert/delete.

**Say:** GDPR / DPDP — **synthetic** seeds only.

---

## Block G — CI (70–78 min)

**Walk** `day6-docker-pipeline.yml`: **`--abort-on-container-exit`**, **`cp`** Surefire, **`if: always()`**, **`down -v`**.

---

## Block H — Live demo 01–03 (78–98 min)

### Demo 01 — Build & run container (~10 min)

```bash
cd lab6/maven-docker-demo
docker build -f Dockerfile.test -t lab6-demo:local .
docker run --rm lab6-demo:local ./mvnw test -B --no-transfer-progress
```

**Say:** Same bytes as CI will run.

### Demo 02 — Compose + env (~12 min)

```bash
cd lab6
docker compose -f docker-compose.test.yml up --build --abort-on-container-exit --exit-code-from test-runner
```

**Show:** logs — **healthy** postgres → **tests run** → exit code 0.

**Unset `DB_PASS`** in shell → show default **`testpass`** via compose substitution.

**Tear down:** `docker compose -f docker-compose.test.yml down -v` (**`-v`** resets volume so **init scripts** re-run next time).

### Demo 03 — Data + failure literacy (~8 min)

- Show **seed** row `test_alice` queried in test.
- **Optional break:** duplicate static primary key insert in a scratch SQL — “data conflict” story.
- **`docker compose cp`** reports to host; mention **lost logs** if you skip copy before **down**.

---

## Block I — Hands-on (98–115 min)

Follow [`lab6/README.md`](README.md).

**Help:** path errors → run compose **from `lab6/`**; **stale DB** → **`down -v`**; **port conflicts** → we publish **no** ports by design.

---

## Block J — Close (115–120 min)

Deliverables; **quiz:** Testcontainers ports vs Compose DNS; Ryuk; H2 vs Postgres in CI; prod data ban.

---

## Lines that land

- “**docker compose down -v** is the reset button for your database story.”
- “If it isn’t in **Git**, it isn’t part of your test contract.”
