# Branching & versioning strategy — test automation repository

**Team / product:** ______________________ **Author:** ______________________ **Date:** ______________________

## 1. Chosen workflow

Pick one primary model (you may note a hybrid):

- [ ] Feature branch + PR (longer-lived branches acceptable for our context)
- [ ] GitHub Flow (short-lived branches, frequent merge to `main`)
- [ ] Trunk-based (very short branches or direct to `main` + feature flags for application code)

**Short rationale (2–3 sentences):**

-

## 2. Branch naming

**Pattern we use:** ______________________ (e.g. `feat/<ticket>-<slug>`)

**Allowed types:** feat / fix / chore / refactor / test / docs — adjust as needed.

## 3. CI integration

| Trigger | What runs (smoke / full / nightly) |
|---------|-------------------------------------|
| PR opened / updated | |
| Merge to `main` | |
| Scheduled (if any) | |

## 4. Review & ownership

**Minimum approvals before merge:** ______________________

**CODEOWNERS areas** (high level): e.g. payments tests, pipeline files, shared fixtures:

-

## 5. Versioning test artefacts

How we align test repo releases with application releases (tags, branches, changelog):

-

## 6. Secrets & config

Where credentials live (GitHub Secrets, vault, etc.) and how CI injects env-specific values:

-

## 7. Conflict prevention (team habits)

E.g. rebase/sync from `main` daily, split large suite files, ownership of hot files:

-
