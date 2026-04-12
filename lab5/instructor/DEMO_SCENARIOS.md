# Day 5 — Instructor demo scenarios

## A — Schema / contract failure (REST Assured)

Temporarily change `user-schema.json` to require a fictional field `"phone"` **or** remove `"email"` from the WireMock stub for `GET /users/1`. Run `./mvnw verify -Dgroups=api` — show **schema validation** error in console and in **Failsafe XML**.

## B — 401 discussion (no real secret needed)

Walk through **hypothetical** workflow env:

```yaml
env:
  API_KEY: ${{ secrets.STAGING_API_KEY }}
```

**Typo** in secret name vs `Settings → Secrets` — classic CI-only 401.

## C — Newman red

Change collection URL to `{{baseUrl}}/users/999999` on the **200** test (or expect wrong status) — run Newman; show CLI failure and **JUnit** reporter output.

## D — Optional: real staging

Set repository variable **`API_BASE_URL`** to team staging (read-only tests only). Adjust `UserApiIT` stubs / paths if the real API differs — or run Newman only against staging.
