# Branching & versioning strategy — test automation repository

**Team / product:** Continuous Testing with CI/CD (course lab) **Author:** Sneha Sharma **Date:** 2026-04-22

## 1. Chosen workflow

Pick one primary model (you may note a hybrid):

- [ ] Feature branch + PR (longer-lived branches acceptable for our context)
- [x] GitHub Flow (short-lived branches, frequent merge to `main`)
- [ ] Trunk-based (very short branches or direct to `main` + feature flags for application code)

**Short rationale (2–3 sentences):**

We use short-lived `feat/<ticket>-<slug>` branches off `main`, open pull requests for every change, and merge after at least one review and green CI. This keeps the automation suite aligned with `main` and avoids long-lived drift. Releases of the test pack can be tagged from `main` when the application under test cuts a release.

## 2. Branch naming

**Pattern we use:** `feat/<ticket>-<short-slug>` (example: `feat/QA-101-login-smoke-extension`)

**Allowed types:** feat / fix / chore / refactor / test / docs — adjust as needed.

## 3. CI integration

| Trigger | What runs (smoke / full / nightly) |
|---------|-------------------------------------|
| PR opened / updated | Root `.github/workflows/ci.yml` — Maven test (`lab2/maven-junit5`), smoke-tagged tests via Surefire |
| Merge to `main` | Same CI workflow on `main` to guard regressions |
| Scheduled (if any) | Optional nightly full regression (not configured in this lab repo) |

## 4. Review & ownership

**Minimum approvals before merge:** 1 (course default; production teams often require 2)

**CODEOWNERS areas** (high level): e.g. payments tests, pipeline files, shared fixtures:

- `.github/workflows/**` — platform / release engineering
- `lab2/maven-junit5/src/test/**` — QA automation owners
- `lab2/maven-junit5/pom.xml` — tech lead or CI maintainer

## 5. Versioning test artefacts

How we align test repo releases with application releases (tags, branches, changelog):

- Tag the test repository (e.g. `tests/v1.4.0`) when the corresponding application `v1.4.0` ships, so we can reproduce historical failures.
- Keep `main` always deployable for CI; hotfixes use `fix/<ticket>-…` branches.
- Maintain a short changelog or release notes for breaking selector or data changes.

## 6. Secrets & config

Where credentials live (GitHub Secrets, vault, etc.) and how CI injects env-specific values:

- No real passwords in Git; use GitHub Actions **secrets** and repository **variables** for URLs and non-secret toggles.
- `FrameworkConfig` already supports env overrides (e.g. `BASE_URL`) so CI can point at a staging stack without code changes.

## 7. Conflict prevention (team habits)

E.g. rebase/sync from `main` daily, split large suite files, ownership of hot files:

- Rebase or merge from `origin/main` before opening a long-running PR; use `git push --force-with-lease` only after a rebase.
- Split large test classes by feature area; avoid “mega” files that everyone edits.
- Communicate in the PR when touching shared fixtures or `config.properties`.
