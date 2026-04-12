# Day 5 — Live demo & 2-hour session script

**Module 5 · API & service-level continuous testing**  
Use with `Day5_ContinuousTesting_CICD.pdf` and [`lab5/README.md`](README.md). **Target mix:** ~35% theory / ~65% hands-on.

**Pre-flight**

- `lab5/maven-api-tests`: `./mvnw verify -Dgroups=api` green.
- Node 20 + `newman --version` optional local check.
- GitHub: optional variable **`API_BASE_URL`** unset (WireMock path).
- Open `UserApiIT.java`, `schemas/user-schema.json`, `postman/collection.json`, `day5-api-pipeline.yml`.

---

## Timeline (120 minutes)

| Block | Minutes | Focus |
|-------|---------|--------|
| A | 0–6 | Welcome, outcomes, pyramid position for API tests |
| B | 6–18 | What to validate: status, body, headers, schema; RA vs Postman table |
| C | 18–32 | REST Assured DSL, RequestSpecification idea, JSONPath, schema matcher |
| D | 32–42 | Data-driven API tests (`@ParameterizedTest`) |
| E | 42–52 | Newman CLI, JUnit reporter, GitHub snippet from slide |
| F | 52–62 | Failsafe, `*IT`, `groups=api`, env injection (`API_BASE_URL`, secrets) |
| G | 62–72 | Reports, failure checklist (401, schema vs functional), JUnit XML shape |
| **H** | **72–92** | **Live demo 01–03** |
| I | 92–115 | Hands-on Tasks 1–3 + support |
| J | 115–120 | Deliverables, takeaways, recap quiz |

*Trim D or E by 3–4 min if needed; keep **H** and **I**.*

---

## Block A — Outcomes (0–6 min)

**Say:** “UI tests are expensive signal. **API tests** catch integration and contract gaps **fast** — today we wire them like any other CI gate.”

---

## Block B — Fundamentals (6–18 min)

**Walk:** status codes (happy + boundary), body vs volatile fields, headers, **JSON Schema = contract**.

**Contrast:** REST Assured in **Git + Maven** vs Postman GUI + **Newman in CI**.

---

## Block C — REST Assured (18–32 min)

**IDE:** `UserApiIT` — `given().baseUri… when().get… then().statusCode… body(…)`.

**Show:** `matchesJsonSchemaInClasspath("schemas/user-schema.json")`.

**Say:** RA **6.0** = **Java 17+**; **5.5.x** for Java 11 (slide).

---

## Block D — Data-driven (32–42 min)

**Point:** `@ParameterizedTest` + `@CsvSource` in `UserApiIT` — same pattern as Day 2, different layer.

---

## Block E — Newman (42–52 min)

**Show:** `collection.json` + `env-ci.json`; slide commands `-r junit`, `--reporter-junit-export`.

**Say:** **jsonplaceholder** is a **public** demo API — fine for training; production suites hit **your** env + auth.

---

## Block F — CI integration (52–62 min)

**Show:** `pom.xml` **Failsafe** + `**/*IT.java` + `groups=api`; workflow **`mvn verify -Dgroups=api`**.

**Say:** **`vars.API_BASE_URL`** for non-secret base URL; **`secrets.*`** for keys — never commit.

---

## Block G — Analysis (62–72 min)

**Walk** slide checklist: status vs env vs auth vs timeouts; **schema fail = contract drift**.

**Show** example JUnit XML fragment from `target/failsafe-reports/`.

---

## Block H — Live demo 01–03 (72–92 min)

### Demo 01 — Write & run REST Assured (~8 min)

1. Show dependencies in `pom.xml` (RA + schema validator + WireMock).
2. Run `./mvnw verify -Dgroups=api` — **5 tests**, WireMock ports in log (optional).
3. Toggle **schema**: rename a required field in stub body **or** schema — show **failure** — revert.

### Demo 02 — CI pipeline (~8 min)

1. **Actions** → **Day 5 — API tests** — walk **needs:** Lab2 unit → REST Assured → Newman.
2. Show **Variables** screen for optional `API_BASE_URL`.
3. Download **failsafe-api-*** artefact; open `TEST-*.xml`.

### Demo 03 — Newman + failure literacy (~6 min)

1. Run `newman run …` in terminal (or show CI log): iterations, assertions.
2. **Optional:** break collection URL → red; restore.
3. **401 story:** remove or wrong API key in a **hypothetical** env — “check secret **name** and expiry.”

---

## Block I — Hands-on (92–115 min)

Follow [`lab5/README.md`](README.md).

**Tips:** WireMock = **offline**; Newman = **network**; merge conflicts rare in `collection.json` but common in **large Postman exports**.

---

## Block J — Close (115–120 min)

Deliverables; **quiz:** status vs schema; Java 11 vs RA 6; Newman JUnit; two causes of **401 in CI**.

---

## Sound bites

- “**200 with the wrong JSON** is worse than **404** — schema catches the lie.”
- “**Newman and Failsafe both speak JUnit XML** — same reporting story.”
